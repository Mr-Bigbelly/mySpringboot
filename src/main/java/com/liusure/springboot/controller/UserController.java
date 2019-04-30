package com.liusure.springboot.controller;


import com.liusure.springboot.entity.Employee;
import com.liusure.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/my")
public class UserController {
    private  static final Logger log=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getuserByid")
    @ResponseBody
    public void getUserInfo(){
        Employee employee=new Employee();
        employee=userService.getUserById("02f83dc3-8d95-4bd7-b5f0-14c1117ec622");
            log.error("这是error日志");
            log.info("这是info日志");

    }
}
