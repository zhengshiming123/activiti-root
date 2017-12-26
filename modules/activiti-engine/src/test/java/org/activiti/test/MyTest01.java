package org.activiti.test;

import org.junit.Test;

import java.sql.*;

public class MyTest01 {

    @Test
    public void test01() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 加载JDBC驱动  
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int num = 0;// 返回影响到的行数
        try {
            connection = getConnection();
            // 准备sql语句
            String sql = "INSERT INTO cnpc_bytes(name,bytes,deployment) VALUES(?,?,?)";
            // 获取PrepareStatement对象
            preparedStatement = connection.prepareStatement(sql);
            // 填充占位符
            preparedStatement.setString(1, "zhangmoumou");
            preparedStatement.setString(2, "zhang123");
            preparedStatement.setString(3, "zhang1231");
//            preparedStatement.setDate(3, new java.sql.Date(1990, 12, 10));
            // 执行sql
            num = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            releaseDB(connection, preparedStatement, null);
        }

        System.out.println("一共影响到" + num + "行");
    }

    /**
     * 获取数据库连接
     *
     * @throws SQLException
     */
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/workflowdb?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=10&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull&connectTimeout=3000";

        String username = "root";
        String password = "root";
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放资源
     *
     * @param connection
     * @param preparedStatement
     * @param resultSet
     */
    public static void releaseDB(Connection connection,
                                 PreparedStatement preparedStatement, ResultSet resultSet) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
