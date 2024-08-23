package me.francis.employeemgtdemo.service;

import jakarta.persistence.EntityNotFoundException;
import me.francis.employeemgtdemo.model.Employee;
import me.francis.employeemgtdemo.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Employee not found with id: " + id)
        );
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Employee not found with id: " + id)
        );

        try {
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setDepartment(employeeDetails.getDepartment());
            employee.setSalary(employeeDetails.getSalary());

            return employeeRepository.save(employee);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update employee with id: " + id);
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Employee not found with id: " + id)
        );

        try {
            employeeRepository.delete(employee);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete employee with id: " + id);
        }
    }
}
