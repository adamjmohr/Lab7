/*
 * Name: Adam Mohr
 * Student ID: 040669681
 * Course & Section: CST8132 301
 * Assignment: Lab 7/8
 * Date: Nov 19, 2018
 */

import java.text.NumberFormat;

/**
 * Banking savings account, subclass of Account and has a unique interest rate.
 * 
 * @author Adam Mohr
 * @version 1.0
 * @since 1.8
 */
public class SavingsAccount extends Account {
	
	// Percentage % value annual interest rate to increase balance when monthly
	// process is run.
	private double interestRate;

	/**
	 * Parameterized constructor to initialize a savings account.
	 * Randomly chosen 4%  annual interest rate.
	 * 
	 * @param client
	 * @param balance to start
	 */
	public SavingsAccount(Client client, double balance) {
		super(client, balance);
		this.interestRate = 0.04;

	}

	public void monthlyProcess() {
		// Increase balance by the annual interest rate. Divide by 12 since it is an
		// annual interest rate calculated monthly.
		balance += (balance * interestRate / 12);
	}

	/**
	 * Getter to return this account's interest rate.
	 * 
	 * @return this interest rate
	 */
	public double getInterestRate() {
		return this.interestRate;
	}

	@Override
	public String toString() {
		// Append annual interest rate to parent class method.
		NumberFormat fmt = NumberFormat.getPercentInstance();
		return super.toString() + ", Annual Interest Rate: " + fmt.format(getInterestRate());
	}

}
