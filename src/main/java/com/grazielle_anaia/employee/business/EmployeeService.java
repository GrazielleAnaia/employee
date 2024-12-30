package com.grazielle_anaia.employee.business;

import com.grazielle_anaia.employee.infrastructure.entity.Employee;
import com.grazielle_anaia.employee.infrastructure.exceptions.ConflictException;
import com.grazielle_anaia.employee.infrastructure.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Employee saveEmployee(Employee employee) {
        try {
            emailExist(employee.getEmail());
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            return employeeRepository.save(employee);

        } catch (ConflictException e) {
            throw new ConflictException("Email already exists.", e.getCause());
        }
    }

    public void emailExist(String email) {
        try {
            boolean exist = verifyEmail(email);
            if (exist) {
                throw new ConflictException("Email already exists." + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email already exists.", e.getCause());
        }
    }

    public boolean verifyEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateInfoEmployee(Long id, Employee updateEmployee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setName(updateEmployee.getName());
            employee.setRole(updateEmployee.getRole());
            employee.setPassword(updateEmployee.getPassword());
            employee.setEmail(updateEmployee.getEmail());
            return employeeRepository.save(employee);
        } else {
            throw new ConflictException("Employee not found.");
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
