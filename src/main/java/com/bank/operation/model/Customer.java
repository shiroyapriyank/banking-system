package com.bank.operation.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.*;



@Entity
@Table(name="Customer", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "emailAddress"
        })
})
@ApiModel(description = "All details about the Customer. ")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated Customer ID")
	private Long customerID;
	
	@ApiModelProperty(notes = "The Customer first name")
	private String customerFirstName;
	
	@ApiModelProperty(notes = "The Customer last name")
	private String customerLastName;
	
	@ApiModelProperty(notes = "The Customer Social Security Number")
	private Long socialSecurityNumber;
	
	@ApiModelProperty(notes = "The Customer Address")
	private String address;
	
	@ApiModelProperty(notes = "The Customer City")
	private String city;
	
	@ApiModelProperty(notes = "The Customer State")
	private String state;
	
	@ApiModelProperty(notes = "The Customer Zip Code")
	private String zipCode;
	
	@ApiModelProperty(notes = "The Customer Contact Number")
	private Long contactNumber;
	
	@ApiModelProperty(notes = "The Customer Date of Birth")
	private String dateOfBirth;
	
	@ApiModelProperty(notes = "The Customer Gender")
	private String gender;
	
	@ApiModelProperty(notes = "The Customer Email Address")
	private String emailAddress;

	@JsonManagedReference
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private Account account;  // one to one relation with account and using customer property from account table.

	
	public Customer(String customerFirstName, String customerLastName, Long socialSecurityNumber, String address,
			String city, String state, String zipCode, Long contactNumber, String dateOfBirth, String gender,
			String emailAddress) {
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.socialSecurityNumber = socialSecurityNumber;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.emailAddress = emailAddress;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Long getCustomerID() {
		return customerID;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public Long getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(Long socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerFirstName=" + customerFirstName + ", customerLastName="
				+ customerLastName + ", socialSecurityNumber=" + socialSecurityNumber + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", contactNumber=" + contactNumber
				+ ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", emailAddress=" + emailAddress + "]";
	}

}
