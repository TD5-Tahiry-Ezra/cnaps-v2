package com.example.prog4;

import com.example.prog4.repository.employee.EmployeeRepository;
import com.example.prog4.repository.employee.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DatabaseIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private com.example.prog4.repository.employeeCnaps.EmployeeRepository employeeCnapsRepository;

    @Test
    @Transactional("employeeTransactionManager")
    public void whenCreatingEmployee_thenCreated() {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setChildrenNumber(1);
        employee = employeeRepository.save(employee);

        assertNotNull(employeeRepository.findById(employee.getId()).orElse(null));
    }

    @Test
    @Transactional("EmployeeCnapsTransactionManager")
    public void whenCreatingEmployeeCnaps_thenCreated() {
        com.example.prog4.repository.employeeCnaps.entity.Employee employeeCnaps = new com.example.prog4.repository.employeeCnaps.entity.Employee();
        employeeCnaps.setFirstName("Jane");
        employeeCnaps.setLastName("Doe");
        employeeCnaps = employeeCnapsRepository.save(employeeCnaps);

        assertNotNull(employeeCnapsRepository.findById(employeeCnaps.getId()).orElse(null));
    }

}