/*
 * Name: Adam Mohr
 * Student ID: 040669681
 * Course & Section: CST8132 301
 * Assignment: Lab 7/8
 * Date: Nov 19, 2018
 */

import cst8132.sarray.GenericArray;

import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.text.DecimalFormat;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Paths;

/**
 * Concrete class represents a Bank to store client information.
 * 
 * @author Adam Mohr
 * @version 1.1
 * @since 1.8
 */
public class Bank {

	String bankName;

	GenericArray<Account> accounts;

	// Scanner to receive input for bankName.
	private static Scanner input;

	// Scanner and Formatter to conduct file I/O.
	private static Scanner fileInput;
	private static Formatter fileOutput;

	/**
	 * Default constructor to initialize and store four account instances, two
	 * chequing and two savings.
	 */
	public Bank() {

		input = new Scanner(System.in);

		System.out.print("What is the name of this bank? ");
		bankName = input.next();

		accounts = new GenericArray<>();

		Client client = new Client("Adam", "Mohr", 4132962, "adam@gmail.com");

		accounts.add(new ChequingAccount(client, 420));

		client = new Client("Ryan", "Spooner", 4132351, "ryan@gmail.com");

		accounts.add(new SavingsAccount(client, 4000000));

		client = new Client("Alex", "Mohr", 4134281, "alex@gmail.com");

		accounts.add(new SavingsAccount(client, 33));

		client = new Client("Jim", "Mohr", 4131956, "james@gmail.com");

		accounts.add(new ChequingAccount(client, 5000));

	}

	/**
	 * Use toString method to print details of each account. Output the bank records
	 * to the text file.
	 */
	public void printAccounts() {
		DecimalFormat df = new DecimalFormat("0.00");

		int numAccounts = accounts.size();

		for (int i = 0; i < numAccounts; i++) {
			System.out.println(accounts.get(i).toString());
		}

		openOutputFile();

		for (int i = 0; i < numAccounts; i++) {
			Account outputAccount = accounts.get(i);
			String accountType = null;

			// Check to see if account is chequing or savings.
			if (outputAccount instanceof ChequingAccount) {
				accountType = "C";
			} else {
				accountType = "S";
			}

			// Output files with contents.
			fileOutput.format("%s %s %d %s %s %n", accountType, outputAccount.getName(),
					outputAccount.getClient().getPhoneNum(), outputAccount.getClient().getEmail(),
					df.format(outputAccount.getBalance()));
		}

		closeOutputFile();
	}

	void openInputFile() {
		try {
			fileInput = new Scanner(Paths.get("bankinput.txt"));

		} catch (IOException e) {
			System.err.println("Error opening file, terminating.");
			System.exit(1); // terminate program
		}
	}

	void readRecords() {
		try {
			System.out.println("Reading records from input file.");

			while (fileInput.hasNext()) {
				// Input contents from .txt file in proper order.
				String accountType = fileInput.next();

				String firstName = fileInput.next();

				String lastName = fileInput.next();

				long phoneNum = fileInput.nextLong();

				String email = fileInput.next();

				double balance = fileInput.nextDouble();

				Client inputClient = new Client(firstName, lastName, phoneNum, email);

				// Determine what account type it is.
				switch (accountType) {
				case "C":
					Account chequingAccount = new ChequingAccount(inputClient, balance);
					accounts.add(chequingAccount);
					break;
				case "S":
					Account savingsAccount = new SavingsAccount(inputClient, balance);
					accounts.add(savingsAccount);
					break;
				default:
					System.err.println("Error reading from file, terminating.");
					System.exit(1); // terminate program
					break;
				}
			}
		} catch (NoSuchElementException e) {
			System.err.println("File improperly formed, terminating.");
			System.exit(1); // terminate program

		} catch (IllegalStateException e) {
			System.err.println("Error reading from file, terminating.");
			System.exit(1); // terminate program
		}
	}

	void closeInputFile() {
		if (fileInput != null) {
			fileInput.close();
		}
	}

	void openOutputFile() {
		try {
			fileOutput = new Formatter("bankoutput.txt");

		} catch (SecurityException e) {
			System.err.println("Write permission denied, terminating.");
			System.exit(1); // terminate program

		} catch (FileNotFoundException e) {
			System.err.println("Error opening file, terminating.");
			System.exit(1); // terminate program
		}
	}

	void closeOutputFile() {
		if (fileOutput != null) {
			fileOutput.close();
		}
	}

	/**
	 * Main method containing switch statement for program flow.
	 * 
	 * @param args optional command line
	 */
	public static void main(String[] args) {

		Bank myBank = new Bank();

		boolean quitProgram = false;

		do {

			System.out.println("Please enter one of the following options:");
			System.out.println("P: print all accounts");
			System.out.println("D: deposit");
			System.out.println("W: withdraw");
			System.out.println("M: monthly process");
			System.out.println("R: read records from input file");
			System.out.println("Q: quit");

			char opt = input.next().toUpperCase().charAt(0);
			int acc;
			double amt;

			switch (opt) {
			case 'Q':
				quitProgram = true;
				break;
			case 'D':
				System.out.print("Enter the index of the account: ");
				acc = input.nextInt();
				System.out.print("Enter the amount to deposit: ");
				amt = input.nextDouble();

				myBank.accounts.get(acc).deposit(amt);
				break;
			case 'W':
				System.out.print("Enter the index of the account: ");
				acc = input.nextInt();
				System.out.print("Enter the amount to withdraw: ");
				amt = input.nextDouble();

				if (!myBank.accounts.get(acc).withdraw(amt)) {
					DecimalFormat df = new DecimalFormat("#,###.##");
					System.out.println(
							"Insufficient funds! Balance is $" + df.format(myBank.accounts.get(acc).getBalance()));
				}
				break;
			case 'P':
				myBank.printAccounts();
				break;
			case 'M':
				int numAccounts = myBank.accounts.size();

				for (int i = 0; i < numAccounts; i++) {
					try {
						// Eclipse told me to surround with try/catch block here.
						myBank.accounts.get(i).monthlyProcess();
					} catch (Exception e) {
						System.err.println(e.toString());
					}
				}
				System.out.println("Accounts updated by monthly process.");
				break;
			case 'R':
				myBank.openInputFile();
				myBank.readRecords();
				myBank.closeInputFile();
				break;
			default:
				System.out.println("I'm sorry, I didn't understand you.");
			}

		} while (quitProgram == false);

		System.out.println();
		System.out.println("Bye! Have a nice day!");

	}

}
