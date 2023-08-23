package com.example.prog4.repository.employeeCnaps;

import com.example.prog4.repository.employeeCnaps.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value="employeeCnapsRepository")
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
