package com.sx.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private static String url = "jdbc:mysql://127.0.0.1:3306/db_score?useUnicode=true&characterEncoding=utf8";
    private static String name = "root";
    private static String password = "root";
    private static Connection connnection = null;
    private static JDBCUtil jdbcUtil = null;

    public static JDBCUtil getInitJDBCUtil() {
        if (jdbcUtil == null) {
            synchronized (JDBCUtil.class) {
                if (jdbcUtil == null) {
                    jdbcUtil = new JDBCUtil();
                }
            }
        }
        return jdbcUtil;
    }

    private JDBCUtil() {
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            connnection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connnection;
    }

    public void closeConnection() {
        if (connnection != null) {
            try {
                connnection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
