package org.activiti.engine.com.mytest;

import java.io.IOException;
import java.sql.*;

public class JdbcUtils {

    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;
    // 加载驱动，获取数据库连接信息
    static {
        try {
            // 加载配置文件
//            InputStream in = JdbcUtils.class.getClassLoader()
//                    .getResourceAsStream("DB.properties");
//            Properties properties = new Properties();
//            properties.load(in);
            driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/workflowdb?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=10&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull&connectTimeout=3000";
            username = "root";
            password = "root";

            // 加载驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 获取数据库连接
     *
     * @throws SQLException
     */
    public static Connection getConnection() {
        /*String url = "jdbc:mysql://localhost:3306/workflowdb?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=10&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull&connectTimeout=3000";

        String username = "root";
        String password = "root";*/
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
