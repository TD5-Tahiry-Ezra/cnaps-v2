package com.example.prog4.controller;

import com.example.prog4.repository.employeeCnaps.entity.Employee;
import com.example.prog4.service.EmployeeCnapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeCnapsController {
    private final EmployeeCnapsService employeeCnapsService;

    @Autowired
    public EmployeeCnapsController(EmployeeCnapsService employeeCnapsService) {
        this.employeeCnapsService = employeeCnapsService;
    }

    @GetMapping("/cnaps/{employeeId}")
    public Employee getEmployeeDetails(@PathVariable String employeeId) {
        return employeeCnapsService.getEmployeeDetails(employeeId);
    }
}
