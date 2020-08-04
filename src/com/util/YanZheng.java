package com.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class YanZheng {
    /**
     * 判断用户输入的username是否在6-14位之间
     * @param username
     * @return 如果是返回true 否则返回false
     */
    public static boolean yanUser(String username) {
        return (username.trim().length() >= 6 && username.trim().length() <= 14);
    }

    /**
     * 验证密码和确认密码是否一致
     * @param password1
     * @param password2
     * @return 如果是返回true，否则返回false
     */
    public static boolean yanPassword(String password1,String password2){
        return (password1.equals(password2));
    }

    /**
     * 验证输入的邮箱是否符合格式，这里使用正则表达式来判断
     * @param email
     * @return 如果是返回true，否则返回false
     */
    public static boolean yanEmail(String email){
        String s="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";//邮箱格式的正则表达式
        return email.matches(s);
    }



    /**
     * 用户名查重的方法，控制注册是用户名唯一，不能有两个一样的用户名
     * @param username
     * @return 如果true，则代表没有重复，false代表有重复
     */
    public static boolean chaChong(String username){
        Connection con=null;
        Statement sta=null;
        ResultSet res =null;
        boolean ok=true;
        try {
            con=JdbcUtil.getConnection();
            sta=con.createStatement();
            String sql="SELECT username FROM user WHERE username='"+username+"'";
            res=sta.executeQuery(sql);
            if (res.next()){
                ok=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(con,sta,res);
        }
        return ok;
    }

    /**
     * 判断密码是否为空方法 如果为空返回false，不为空返回true
     * @param password
     * @return
     */
    public static boolean yanMiMa(String password){
        return !("".equals(password));
    }
}

