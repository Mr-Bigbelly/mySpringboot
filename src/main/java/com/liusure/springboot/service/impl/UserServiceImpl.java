package com.liusure.springboot.service.impl;

import com.liusure.springboot.dao.EmployeeMapper;
import com.liusure.springboot.entity.Employee;
import com.liusure.springboot.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private  EmployeeMapper employeeMapper;

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    public Employee getUserById(String userId){
        Employee employee=new Employee();
        employee=employeeMapper.selectByPrimaryKey(userId);
        System.out.println("查询成功");
        logger.error("这就是error日志");
        return employee;

    }
}
