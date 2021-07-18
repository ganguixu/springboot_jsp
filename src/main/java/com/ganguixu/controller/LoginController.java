package com.ganguixu.controller;

import com.ganguixu.model.User;
import com.ganguixu.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String login(){
        return "index";
    }

    @RequestMapping("login")
    public String login(HttpServletRequest request, @Param("name") String name, @Param("pass")String pass){
        log.info("name:{},pass:{}",name,pass);
        request.setAttribute("message","登录成功");
        return "firstPage";
    }

    @RequestMapping("findUserAll")
    @ResponseBody
    public List<User> findUserAll(HttpServletRequest request,@Param("type") String type,@Param("beginDate") String beginDate,@Param("endDate") String endDate){
        log.info("type:"+type+";beginDate:"+beginDate+";endDate:"+endDate);
        List<User> userAll = userService.findUserAll(type, beginDate, endDate);
        return userAll;
    }

    @RequestMapping("findUserAll2")
//    @ResponseBody
    public String findUserAll2(HttpServletRequest request,@Param("type") String type,@Param("beginDate") String beginDate,@Param("endDate") String endDate){
        log.info("type:"+type+";beginDate:"+beginDate+";endDate:"+endDate);
        List<User> userAll = userService.findUserAll(type, beginDate, endDate);
        if (beginDate != null && beginDate.trim().length()>0 && endDate != null && endDate.trim().length()>0 && userAll.size()>0) {
            log.info("满足条件允许导出");
            request.setAttribute("download","1");
        }
        request.setAttribute("type",type);
        request.setAttribute("beginDate",beginDate);
        request.setAttribute("endDate",endDate);
        request.setAttribute("userAll",userAll);
        return "twoPage";
    }

    @RequestMapping("query")
    public String query(HttpServletRequest request) {
        request.setAttribute("type","1");
        request.setAttribute("beginDate","2021-07-18");
        request.setAttribute("endDate","2021-07-31");
        return "twoPage";
    }
}
