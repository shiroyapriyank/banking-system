package com.bank.operation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bank.operation.model.Account;
import com.bank.operation.model.Customer;
import com.bank.operation.service.AccountService;
import com.bank.operation.service.CustomerService;

import io.swagger.annotations.*;

@Api(value = "Banking System", description = "RESTful API to simulate simple banking operations.")
@RestController
@RequestMapping("/")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private AccountService accountService;

	@ApiOperation(value = "View a list of available Customers", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/admin/customers/customerList")
	public List<Customer> getCustomer() {
		return customerService.listOfCustomers();
	}

	@ApiOperation(value = "Get an Customer by Id")
	@GetMapping("/customers/viewCustomer/{id}")
	public Optional<Customer> getCustomerById(@PathVariable Long id) {
		return customerService.viewCustomerById(id);
	}

	@ApiOperation(value = "Add Customer")
	@PostMapping("/admin/customers/addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@ApiOperation(value = "Update an Customer")
	@PutMapping("/customers/editCustomer/{customerID}")
	public ResponseEntity<?> editCustomer(@PathVariable Long customerID, @RequestBody Customer customer) {
		return customerService.editCustomer(customerID, customer);
	}

	@ApiOperation(value = "Deposite to Account")
	@PutMapping("/customers/depositeMoney/{depositeAmount}")
	public ResponseEntity<?> depositeMony(@PathVariable Long depositeAmount, @RequestBody Account account) {
		return accountService.addDepositeAmount(depositeAmount, account);
	}

	@ApiOperation(value = "Withdraw from Account")
	@PutMapping("/customers/withdrawMoney/{withdrawAmount}")
	public ResponseEntity<?> withdrawMony(@PathVariable Long withdrawAmount, @RequestBody Account account) {
		return accountService.withdrawAmount(withdrawAmount, account);
	}

	@ApiOperation(value = "Transfer one Account to Another Account")
	@PutMapping("/customers/transfer/toAnotherAccount/{fromAccountID}/{toAccountID}/{transferAmount}")
	public ResponseEntity<?> transferToAnotherAccount(@PathVariable Long fromAccountID, @PathVariable Long toAccountID,
			@PathVariable Long transferAmount) {
		return accountService.transferFund(fromAccountID, toAccountID, transferAmount);
	}

}