package com.bank.operation.model;

import javax.persistence.*;

@Entity
public class AccountType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String accountTypeName;

	AccountType() {
	}

	public AccountType(String accountName) {
		this.accountTypeName = accountName;
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

}
