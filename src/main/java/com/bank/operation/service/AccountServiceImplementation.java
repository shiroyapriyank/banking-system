package com.bank.operation.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.operation.model.Account;
import com.bank.operation.repository.AccountRepository;

@Service
public class AccountServiceImplementation implements AccountService{

	@Autowired
	AccountRepository accountRepo;
	
	@Override
	public ResponseEntity<?> addDepositeAmount(Long depositeAmount, Account account) {
		// find the account
		Optional<Account> checkAccountExist = accountRepo.findByaccountNumber(account.getAccountNumber());
		// if present we will desposite amount
		if (checkAccountExist.isPresent()) {
			Account depositeIn = checkAccountExist.get();
			depositeIn.setAccountBalance(depositeIn.getAccountBalance() + depositeAmount);
			accountRepo.save(depositeIn);
			return ResponseEntity.status(HttpStatus.OK).body("$" + depositeAmount + " Deposited Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Account exist");
		}
	}

	@Override
	public ResponseEntity<?> withdrawAmount(Long withdrawAmount, Account account) {
		// find the account
		Optional<Account> checkAccountExist = accountRepo.findByaccountNumber(account.getAccountNumber());
		// if present we will withdraw
		if (checkAccountExist.isPresent()) {
			Account depositeIn = checkAccountExist.get();
			System.out.println(depositeIn.getAccountBalance());
			// we will check if there is sufficient balance to withdraw
			if (depositeIn.getAccountBalance() < withdrawAmount+250) {
				return ResponseEntity.status(HttpStatus.OK).body("Insufficient Balance in your Account");
			} else {
				Long newAccountBalance = depositeIn.getAccountBalance() - withdrawAmount;
				depositeIn.setAccountBalance(newAccountBalance);
				accountRepo.save(depositeIn);
				return ResponseEntity.status(HttpStatus.OK).body("$" + withdrawAmount + " withdrawed Successfully");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Account exist");
		}
	}

	@Override
	public ResponseEntity<?> transferFund(Long fromAccountID, Long toAccountID, Long transferAmount) {
		//getting information of sender and receiver.
		Optional<Account> senderAccount = accountRepo.findById(fromAccountID);
		Optional<Account> receiverAccount = accountRepo.findById(toAccountID);
		//checking if any information exist
		if (senderAccount.isPresent() && receiverAccount.isPresent()) {
			Account sendAcc = senderAccount.get();
			Account recAcc = receiverAccount.get();
			try {
				//Checking is sender is having sufficient balance
				if (sendAcc.getAccountBalance() < transferAmount+250 ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient Balance.");
				} else {
					sendAcc.setAccountBalance(sendAcc.getAccountBalance() - transferAmount);
					recAcc.setAccountBalance(recAcc.getAccountBalance() + transferAmount);
					List<Account> list = Arrays.asList(sendAcc, recAcc);
					accountRepo.saveAll(list);
				}
			} catch (Exception e) {
				System.out.println("Error while Transfering Money " + e);
			}
			return ResponseEntity.status(HttpStatus.OK).body("Transfer Successful.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Check the account Information.");
		}
	}
}
