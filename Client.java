/*
 * Name: Adam Mohr
 * Student ID: 040669681
 * Course & Section: CST8132 301
 * Assignment: Lab 7/8
 * Date: Nov 19, 2018
 */

/**
 * Concrete class represents a Client from a bank.
 * 
 * @author Adam Mohr
 * @version 1.1
 * @since 1.8
 */
public class Client {

	private String firstName;
	private String lastName;
	private long phoneNum;
	private String email;

	/**
	 * Parameterized constructor to initialize client with all required fields.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param phoneNum
	 * @param email
	 */
	public Client(String firstName, String lastName, long phoneNum, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNum = phoneNum;
		this.email = email;
	}

	/**
	 * Not a true getter, returns client's first and last names concatenated.
	 * 
	 * @return this client's first and last name
	 */
	public String getName() {
		return firstName + " " + lastName;
	}

	/**
	 * Getter to return this client's phone number.
	 * 
	 * @return phoneNum
	 */
	public long getPhoneNum() {
		return phoneNum;
	}

	/**
	 * Getter to return this client's email.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

}
