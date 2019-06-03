package com.bank.operation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import com.bank.operation.model.Customer;

public interface CustomerService {

	ResponseEntity<?> addCustomer(Customer customer);
	List<Customer> listOfCustomers();
	ResponseEntity<?> editCustomer(Long customerID, Customer customer);
	Optional<Customer> viewCustomerById(Long id);
	
}
