package me.francis.employeemgtdemo.controller;

import jakarta.transaction.Transactional;
import me.francis.employeemgtdemo.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Transactional
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Francis");
        employee.setLastName("Kikulwe");
        employee.setEmail("francis@developer.com");
        employee.setDepartment("IT");
        employee.setSalary(50000.0);

        ResponseEntity<Employee> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/employees",
                employee,
                Employee.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getId());
        assertEquals("Francis", responseEntity.getBody().getFirstName());
    }

    @Test
    @Transactional
    public void testGetAllEmployees() {
        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/employees",
                Employee[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @Transactional
    public void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setFirstName("Francis");
        employee.setLastName("Kikulwe");
        employee.setEmail("francis@developer.com");
        employee.setDepartment("IT");
        employee.setSalary(50000.0);

        restTemplate.postForEntity(
                "http://localhost:" + port + "/api/employees",
                employee,
                Employee.class);

        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/employees/1",
                Employee.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(1L, responseEntity.getBody().getId());
    }

    @Test
    @Transactional
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Francis");
        employee.setLastName("Kikulwe");
        employee.setEmail("francis@developer.com");
        employee.setDepartment("IT");
        employee.setSalary(50000.0);

        restTemplate.postForEntity(
                "http://localhost:" + port + "/api/employees",
                employee,
                Employee.class);

        Employee updatedEmployee = new Employee();
        updatedEmployee.setFirstName("Francis");
        updatedEmployee.setLastName("Kikulwe");
        updatedEmployee.setEmail("francis@developer.com");
        updatedEmployee.setDepartment("IT");
        updatedEmployee.setSalary(80000.0);

        restTemplate.put(
                "http://localhost:" + port + "/api/employees/1",
                updatedEmployee);

        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/employees/1",
                Employee.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(1L, responseEntity.getBody().getId());
        assertEquals(80000.0, responseEntity.getBody().getSalary());

    }

    @Test
    @Transactional
    public void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Francis");
        employee.setLastName("Kikulwe");
        employee.setEmail("francis@developer.com");
        employee.setDepartment("IT");
        employee.setSalary(50000.0);

        restTemplate.postForEntity(
                "http://localhost:" + port + "/api/employees",
                employee,
                Employee.class);

        restTemplate.delete("http://localhost:" + port + "/api/employees/1");

        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/employees/1",
                Employee.class);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
