package com.util;

import java.sql.*;

/**
 * jdbc工具类
 */
public class JdbcUtil {
    private JdbcUtil() {}

    /**
     * 类加载时注册驱动
     */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接方法
     * @return返回连接对象
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/自助售票系统","root","zyh26258");
    }

    /**
     * 关闭资源
     * @param con
     * @param sta
     * @param re
     */
    public static void close(Connection con, Statement sta, ResultSet re){
        if (re != null) {
            try {
                re.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (sta != null) {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 重载    关闭资源
     * @param con
     * @param sta
     */
    public static void close(Connection con, Statement sta){
        if (sta != null) {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
