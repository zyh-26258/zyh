package com.dao;

import com.enetiy.Student;
import com.util.JdbcUtil;
import com.util.YanZheng;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    /**
     * 查询全部学生信息的方法
     *
     * @return 返回一个list集合，集合当中存放的是Student对象
     */
    public List<Student> selectStudents() {
        Connection con = null;
        List<Student> list = new ArrayList<>();
        Student student = null;
        PreparedStatement pre = null;
        String sql = null;
        ResultSet res = null;
        try {
            con = JdbcUtil.getConnection();
            sql = "select * from student";
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                Integer id = res.getInt("id");
                String name = res.getString("name");
                String sex = res.getString("sex");
                String minZu = res.getString("minZu");
                String suoZaiXi = res.getString("suoZaiXi");
                String zhuanYeMing = res.getString("zhuanYeMing");
                String banJi=res.getString("banJi");
                student = new Student(id, name, sex, minZu, suoZaiXi, zhuanYeMing,banJi);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(con, pre, res);
        }
        return list;
    }

    /**
     * 查询学生信息的方法，存放在一个Student类当中
     *
     * @param id 学生的id
     * @return 返回的学生对象
     */
    public Student selectStudent(Integer id) {
        Connection con = null;
        Student student = null;
        PreparedStatement pre = null;
        String sql = null;
        ResultSet res = null;
        try {
            con = JdbcUtil.getConnection();
            sql = "select * from student where id=?";
            pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            res = pre.executeQuery();
            while (res.next()) {
                String name = res.getString("name");
                String sex = res.getString("sex");
                String minZu = res.getString("minZu");
                String suoZaiXi = res.getString("suoZaiXi");
                String zhuanYeMing = res.getString("zhuanYeMing");
                String banJi=res.getString("banJi");
                student = new Student(id, name, sex, minZu, suoZaiXi, zhuanYeMing,banJi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(con, pre, res);
        }
        return student;
    }

    /**
     * 添加学生信息方法
     *
     * @param student 学生对象，用来存储学生的信息
     * @return 返回处理结果
     */
    public int addStudent(Student student) {
        int result = 0;
        Connection con = null;
        PreparedStatement pre = null;
        String sql = "insert into student (id,name,sex,minZu,suoZaiXi,zhuanYeMing,banJi) values (?,?,?,?,?,?,?)";
        try {
            con = JdbcUtil.getConnection();
            pre = con.prepareStatement(sql);
            pre.setInt(1, student.getId());
            pre.setString(2, student.getName());
            pre.setString(3, student.getSex());
            pre.setString(4, student.getMinZu());
            pre.setString(5, student.getSuoZaiXi());
            pre.setString(6, student.getZhuanYeMing());
            pre.setString(7,student.getBanJi());
            result = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(con, pre);
        }
        return result;
    }

    /**
     * 用户登录方法，如果传进来的username和password可以和数据库当中的匹配，return true，否则，返回false
     *
     * @param username
     * @param password
     * @return
     */
    public static boolean login(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        //布尔标记
        boolean ok = false;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "SELECT username,password FROM user WHERE username=? AND password=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ok = true;
            }
        } catch (SQLException d) {
            d.printStackTrace();
        } finally {
            JdbcUtil.close(connection, statement, resultSet);
        }
        return ok;
    }

    /**
     * 注册方法，当用户名，密码，确认密码，邮箱都符合规则之后才会注册成功
     *
     * @param username
     * @param password1
     * @param password2
     * @param email
     * @return
     */
    public static boolean zhuCe(String username, String password1, String password2, String email) {
        boolean ok1 = YanZheng.yanUser(username);
        boolean ok2 = YanZheng.yanEmail(email);
        boolean ok3 = YanZheng.yanPassword(password1, password2);
        boolean ok4 = YanZheng.chaChong(username);
        boolean ok5 = YanZheng.yanMiMa(password1);
        if (ok1 && ok2 && ok3 && ok4 && ok5) {
            Connection con = null;
            PreparedStatement sta = null;
            try {
                con = JdbcUtil.getConnection();
                String sql = "insert into user (username,password,email) values (?,?,?)";
                sta = con.prepareStatement(sql);
                sta.setString(1, username);
                sta.setString(2, password1);
                sta.setString(3, email);
                sta.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JdbcUtil.close(con, sta);
            }
        }
        return ok1 && ok2 && ok3 && ok4 && ok5;
    }
}
