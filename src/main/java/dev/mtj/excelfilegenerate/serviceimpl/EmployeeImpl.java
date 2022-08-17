package dev.mtj.excelfilegenerate.serviceimpl;

import dev.mtj.excelfilegenerate.entity.Employee;
import dev.mtj.excelfilegenerate.repository.EmployeeRepository;
import dev.mtj.excelfilegenerate.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class EmployeeImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public void addEmployee(Employee employee) {
    employeeRepository.save(employee);
    }
}
