package me.francis.employeemgtdemo.service;

import me.francis.employeemgtdemo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Page<Employee> getAllEmployees(Pageable pageable);
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Long id, Employee employeeDetails);
    void deleteEmployee(Long id);
}
