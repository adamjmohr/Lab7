/*
 * Name: Adam Mohr
 * Student ID: 040669681
 * Course & Section: CST8132 301
 * Assignment: Lab 7/8
 * Date: Nov 19, 2018
 */

/**
 * Banking chequing account, subclass of Account and has a unique monthly fee.
 * 
 * @author Adam Mohr
 * @version 1.0
 * @since 1.8
 */
public class ChequingAccount extends Account {
	
	// Money amount deducted when monthly process is run.
	private double monthlyFee;

	/**
	 * Parameterized constructor to initialize a chequing account.
	 * Randomly chosen monthly fee of $7.50 per month.
	 * 
	 * @param client
	 * @param balance to start
	 */
	public ChequingAccount(Client client, double balance) {
		super(client, balance);
		this.monthlyFee = 7.50;

	}

	public void monthlyProcess() throws Exception {
		// Tries to deduct the monthly fee.
		if (!withdraw(monthlyFee)) {
			String errorMessage = "Error! Insufficient funds. Account: " + accountNum + ", Balance: $" + balance
					+ ", Monthly Fee: $" + monthlyFee;
			throw new Exception(errorMessage);
		}
	}

	/**
	 * Getter to return this account's monthly fee.
	 * 
	 * @return monthlyFee of this account
	 */
	public double getMonthlyFee() {
		return this.monthlyFee;
	}

	@Override
	public String toString() {
		// Append monthly fee to parent class method.
		return super.toString() + ", Monthly Fee: $" + getMonthlyFee();
	}

}
