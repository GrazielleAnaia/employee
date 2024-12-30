package com.grazielle_anaia.employee.infrastructure.repository;

import com.grazielle_anaia.employee.infrastructure.entity.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    boolean existsByEmail(String email);

    Optional<Employee> findByEmail(String email);
}
