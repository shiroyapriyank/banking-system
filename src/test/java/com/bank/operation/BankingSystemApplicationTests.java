package com.bank.operation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.operation.model.Customer;
import com.bank.operation.repository.AccountRepository;
import com.bank.operation.repository.CustomerRepository;
import com.bank.operation.service.AccountService;
import com.bank.operation.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankingSystemApplicationTests {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CustomerService customerService;

	@MockBean
	private AccountRepository accountRepository;

	@MockBean
	private CustomerRepository customerRepository;

	@Test
	public void addCustomerTest() {

		Customer customer = new Customer("Priyank", "Shiroya", 232510949L, "1619 Deergreen Ln", "Charlotte", "NC",
				"28262", 7048913489L, "08/29/1995", "Male", "priyanks.29895@gmail.com");
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(ResponseEntity.status(HttpStatus.OK).body(customer), customerService.addCustomer(customer));
	}

	@Test
	public void getCustomersTest() {
		when(customerRepository.findAll())
				.thenReturn(Stream
						.of(new Customer("Priyank", "Shiroya", 232510949L, "1619 Deergreen Ln", "Charlotte", "NC",
								"28262", 7048913489L, "08/29/1995", "Male", "priyanks.29895@gmail.com"),
								new Customer("Anirudh", "Jadeja", 232450949L, "1619 Deergreen Ln", "Charlotte", "NC",
										"28262", 7042189586L, "08/29/1995", "Male", "aepsjadeja@gmail.com"))
						.collect(Collectors.toList()));
		assertEquals(2, customerService.listOfCustomers().size());
	}


}
