package org.activiti.explorer.controller;

import org.activiti.engine.com.mytest.JdbcUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

@Controller
public class UserControl {
    
    @RequestMapping("service/cong")
    @ResponseBody
    public Map getname(){
        System.out.println("11111");
        return null;
    }
    
    @RequestMapping("WorkflowUserServlet/cong")
    @ResponseBody
    public String getnamet(){
        System.out.println("22222");
        
        return null;
    }
    
    @RequestMapping("WorkflowUserServlet/pic")
    public String WorkflowUserServlet1(HttpServletResponse response,
                                       String id){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String bytesT = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "SELECT bytes FROM cnpc_bytes WHERE id=7";
//            String sql = "SELECT BYTES_ AS bytes FROM act_ge_bytearray WHERE ID_=2503";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // 遍历结果集
            while (resultSet.next()) {
                String bytes = resultSet.getString(1);
                bytesT = bytes;
                System.out.println(bytes);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            JdbcUtils.releaseDB(connection, preparedStatement, resultSet);
        }
        byte[] bytes = hexStringToBytes(bytesT);
        try {
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

}
