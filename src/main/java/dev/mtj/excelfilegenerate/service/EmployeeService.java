package dev.mtj.excelfilegenerate.service;

import dev.mtj.excelfilegenerate.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    void addEmployee(Employee employee);
}
