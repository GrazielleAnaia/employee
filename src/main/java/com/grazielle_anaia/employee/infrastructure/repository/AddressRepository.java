package com.grazielle_anaia.employee.infrastructure.repository;

import com.grazielle_anaia.employee.infrastructure.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
