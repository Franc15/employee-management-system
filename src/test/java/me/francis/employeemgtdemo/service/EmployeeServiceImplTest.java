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

    @Test
    @DisplayName("Get Employee By Id")
    void getEmployeeById() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Francis");
        employee.setLastName("Kikulwe");
        employee.setEmail("francis@developer.com");
        employee.setDepartment("IT");
        employee.setSalary(50000.0);

        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));

        Employee foundEmployee = employeeService.getEmployeeById(1L);

        assertNotNull(foundEmployee);
        assertEquals(1L, foundEmployee.getId());
        assertEquals("Francis", foundEmployee.getFirstName());
        assertEquals("Kikulwe", foundEmployee.getLastName());
        assertEquals("francis@developer.com", foundEmployee.getEmail());
        assertEquals("IT", foundEmployee.getDepartment());
        assertEquals(50000.0, foundEmployee.getSalary());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Update Employee")
    void updateEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Francis");
        employee.setLastName("Kikulwe");
        employee.setEmail("francis@developer.com");
        employee.setDepartment("IT");
        employee.setSalary(50000.0);

        Employee employeeDetails = new Employee();
        employeeDetails.setFirstName("Francis");
        employeeDetails.setLastName("Kikulwe");
        employeeDetails.setEmail("francis@developer.com");
        employeeDetails.setDepartment("IT");
        employeeDetails.setSalary(80000.0);

        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee updatedEmployee = employeeService.updateEmployee(1L, employeeDetails);

        assertNotNull(updatedEmployee);
        assertEquals(1L, updatedEmployee.getId());
        assertEquals("Francis", updatedEmployee.getFirstName());
        assertEquals("Kikulwe", updatedEmployee.getLastName());
        assertEquals("francis@developer.com", updatedEmployee.getEmail());
        assertEquals("IT", updatedEmployee.getDepartment());
        assertEquals(80000.0, updatedEmployee.getSalary());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    @DisplayName("Delete Employee")
    void deleteEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Francis");
        employee.setLastName("Kikulwe");
        employee.setEmail("francis@developer.com");
        employee.setDepartment("IT");
        employee.setSalary(50000.0);

        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).delete(employee);
    }
}
