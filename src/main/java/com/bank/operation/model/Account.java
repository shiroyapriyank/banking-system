package com.bank.operation.model;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "All details about the Customer Account. ")
public class Account {

	@Id
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "customer"))
	@ApiModelProperty(notes = "The Foreign key from Customer table Customer ID")
	private Long accountCustomerID; //This is foreign key for the Customer table.
	@ApiModelProperty(notes = "The Account number of customer")
	private Long accountNumber;
	@ApiModelProperty(notes = "The Customer Account Balance")
	private Long accountBalance;
	@ApiModelProperty(notes = "The Customer Account Opening Date")
	private String accountOpenDate;

	@JsonBackReference
	@OneToOne
	@PrimaryKeyJoinColumn
	private Customer customer; // creating one to one relation with customer 

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<AccountType> accountType; // making account type as one to many because account can be saving, checkings, student. by default it is checkings

    public Account() {
		// TODO Auto-generated constructor stub
	}
	
	public Account(Long accountNumber, Long accountBalance, String accountOpenDate,
			Customer customer, List<AccountType> accountType) {
		super();
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.accountOpenDate = accountOpenDate;
		this.customer = customer;
		this.accountType = accountType;
	}

	public Long getAccountCustomerID() {
		return accountCustomerID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<AccountType> getAccountType() {
		return accountType;
	}

	public void setAccountType(List<AccountType> accountType) {
		this.accountType = accountType;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setAccountCustomerID(Long accountID) {
		this.accountCustomerID = accountID;
	}

	public Long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Long accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountOpenDate() {
		return accountOpenDate;
	}

	public void setAccountOpenDate(String accountOpenDate) {
		this.accountOpenDate = accountOpenDate;
	}

	@Override
	public String toString() {
		return "Account [accountCustomerID=" + accountCustomerID + ", accountType=" + accountType + ", accountBalance=" + accountBalance
				+ ", accountOpenDate=" + accountOpenDate + "]";
	}

}
