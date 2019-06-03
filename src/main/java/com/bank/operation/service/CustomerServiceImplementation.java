package com.bank.operation.service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.operation.model.Account;
import com.bank.operation.model.AccountType;
import com.bank.operation.model.Customer;
import com.bank.operation.repository.AccountRepository;
import com.bank.operation.repository.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private AccountRepository accountRepo;

	@Override
	public ResponseEntity<?> addCustomer(Customer customer) {
		// checking if customer has already account.
		Optional<Customer> checkSSN = customerRepo.findBysocialSecurityNumber(customer.getSocialSecurityNumber());
		if (checkSSN.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You already have an account");
		} else {
			// If Customer not exist, we will add customer
			try {
				// create a 9 digit account number.
				// by default minimum balance should be $250.
				// by default accountType is Checking
				Account account = new Account(new Random().longs(100000000, 999999999).findAny().orElse(0), 250L,
						new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime()), customer,
						Arrays.asList(new AccountType("Checkings")));
				customerRepo.save(customer);
				accountRepo.save(account);
			} catch (Exception e) {
				System.out.println("Error while adding customer " + e);
			}
			return ResponseEntity.status(HttpStatus.OK).body(customer);
		}
	}

	@Override
	public List<Customer> listOfCustomers() {
		return (List<Customer>) customerRepo.findAll();
	}

	@Override
	public ResponseEntity<?> editCustomer(Long customerID, Customer customer) {
		// checking if customer Exist
		Optional<Customer> customerExist = customerRepo.findById(customerID);
		// if yes will do update
		if (customerExist.isPresent()) {
			Customer updateCustomer = customerExist.get();
			updateCustomer.setAddress(customer.getAddress());
			updateCustomer.setCity(customer.getCity());
			updateCustomer.setContactNumber(customer.getContactNumber());
			updateCustomer.setState(customer.getState());
			updateCustomer.setZipCode(customer.getZipCode());
			updateCustomer.setEmailAddress(customer.getEmailAddress());
			updateCustomer.setAccount(customer.getAccount());
			customerRepo.save(updateCustomer);
			return ResponseEntity.status(HttpStatus.OK).body("Customer Updated Successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Such Customer Exist.");
		}
	}

	@Override
	public Optional<Customer> viewCustomerById(Long id) {
		return customerRepo.findById(id);
	}

	

}
