package me.francis.employeemgtdemo.service;

import me.francis.employeemgtdemo.model.Employee;
import me.francis.employeemgtdemo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Create Employee")
    void createEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Francis");
        employee.setLastName("Kikulwe");
        employee.setEmail("francis@developer.com");
        employee.setDepartment("IT");
        employee.setSalary(50000.0);

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = employeeService.createEmployee(employee);

        assertNotNull(savedEmployee);
        assertEquals("Francis", savedEmployee.getFirstName());
        assertEquals("Kikulwe", savedEmployee.getLastName());
        assertEquals("francis@developer.com", savedEmployee.getEmail());
        assertEquals("IT", savedEmployee.getDepartment());
        assertEquals(50000.0, savedEmployee.getSalary());
        verify(employeeRepository, times(1)).save(employee);
    }
}
