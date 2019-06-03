package com.bank.operation.service;

import org.springframework.http.ResponseEntity;

import com.bank.operation.model.Account;

public interface AccountService {

	ResponseEntity<?> addDepositeAmount(Long depositeAmount, Account account);
	ResponseEntity<?> withdrawAmount(Long withdrawAmount, Account account);
	ResponseEntity<?> transferFund(Long fromAccountID, Long toAccountID, Long transferAmount);
}
