package me.francis.employeemgtdemo.repository;

import me.francis.employeemgtdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
