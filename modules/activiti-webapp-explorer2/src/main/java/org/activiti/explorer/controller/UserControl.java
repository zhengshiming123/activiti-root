package org.activiti.explorer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
