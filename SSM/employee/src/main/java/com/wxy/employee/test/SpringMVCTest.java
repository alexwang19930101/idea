package com.wxy.employee.test;

import com.wxy.employee.crud.dao.EmployeeDao;
import com.wxy.employee.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringMVCTest {

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/testConversionService")
    public String testConverter(@RequestParam("employee")Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}
