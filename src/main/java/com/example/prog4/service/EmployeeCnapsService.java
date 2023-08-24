package com.example.prog4.service;

import com.example.prog4.repository.employeeCnaps.EmployeeRepository;
import com.example.prog4.repository.employeeCnaps.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeCnapsService {
    private EmployeeRepository employeeRepository;
    @Autowired
    public void EmployeeCnapsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeDetails(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        String cnapsNumber = employeeRepository.getCnapsNumberByEmployeeId(Long.valueOf(employeeId));

        if (cnapsNumber != null && !cnapsNumber.equals(employee.getNumber())) {
            employee.setNumber(cnapsNumber);
        }

        return employee;
    }
}