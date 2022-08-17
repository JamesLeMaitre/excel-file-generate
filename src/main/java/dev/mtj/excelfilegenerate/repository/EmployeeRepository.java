package dev.mtj.excelfilegenerate.repository;

import dev.mtj.excelfilegenerate.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
