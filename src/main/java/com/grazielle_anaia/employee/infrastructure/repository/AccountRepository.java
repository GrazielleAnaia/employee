package com.grazielle_anaia.employee.infrastructure.repository;

import com.grazielle_anaia.employee.infrastructure.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
