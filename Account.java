/*
 * Name: Adam Mohr
 * Student ID: 040669681
 * Course & Section: CST8132 301
 * Assignment: Lab 7/8
 * Date: Nov 19, 2018
 */

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Abstract class represents a Banking account.
 * 
 * @author Adam Mohr
 * @version 1.1
 * @since 1.8
 */
public abstract class Account {

	protected long accountNum;
	protected Client client;
	protected double balance;

	/**
	 * Parameterized constructor to initialize Account. Generates random accountNum.
	 * 
	 * @param client
	 * @param balance money in account
	 */
	public Account(Client client, double balance) {
		this.client = client;
		this.balance = balance;

		this.accountNum = new Random().nextLong();
	}

	abstract void monthlyProcess() throws Exception;

	/**
	 * Deposit money into this account.
	 * 
	 * @param amt of money to deposit
	 */
	public void deposit(double amt) {
		balance += amt;
	}

	/**
	 * Try to withdraw money from this account.
	 * 
	 * @param amt to try and withdraw.
	 * @return false if withdraw is not possible or true if it is
	 */
	public boolean withdraw(double amt) {
		if (amt > balance) {
			return false;
		} else {
			balance -= amt;
			return true;
		}
	}

	/**
	 * Getter for this account number.
	 * 
	 * @return this accountNum
	 */
	public long getAccountNum() {
		return accountNum;
	}

	/**
	 * Getter for this client.
	 * 
	 * @return this client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Getter for this account balance.
	 * 
	 * @return this balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Getter for this client's name.
	 * 
	 * @return this client's name
	 */
	public String getName() {
		return client.getName();
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");

		return "Name: " + getName() + ", Account: " + getAccountNum() + ", Balance: $" + df.format(getBalance())
				+ ", Client Email: " + getClient().getEmail() + ", Client Phone Num: " + getClient().getPhoneNum();
	}
}
