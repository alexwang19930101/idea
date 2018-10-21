package com.wxy.employee.test;

import com.wxy.employee.crud.dao.EmployeeDao;
import com.wxy.employee.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class SpringMVCTest {

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/testConversionService")
    public String testConverter(@RequestParam("employee")Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam(value = "upfile") MultipartFile file,String desc) throws IOException {
        System.out.println("desc:"+desc);
        System.out.println("originalFileName："+file.getOriginalFilename());
        System.out.println("InputStream:"+file.getInputStream());
        System.out.println("文件上传中");

        return "success";
    }
}
