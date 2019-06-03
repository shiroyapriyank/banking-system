package com.bank.operation.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bank.operation.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	Optional<Customer> findBysocialSecurityNumber(Long ssn);
}
