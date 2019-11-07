package com.example.comtroller;

import com.example.dao.EmployeeDao;
import com.example.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName EmployeeController
 * @Description
 * @Author ykb
 * @Date 2019/9/11
 **/
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;


    @GetMapping("/emps")
    public String listEmployees(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("employees",employees);
        return "/emp/list";
    }
}
