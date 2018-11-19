/*
 * Name: Adam Mohr
 * Student ID: 040669681
 * Course & Section: CST8132 301
 * Assignment: Lab 7/8
 * Date: Nov 19, 2018
 */

package cst8132.sarray;

/**
 * Define custom capacity out of bounds exception class. Subclass of
 * RuntimeException.
 * 
 * @author Adam Mohr
 * @version 1.0
 * @since 1.8
 */
public class CapacityOutOfBoundsException extends RuntimeException {

	/**
	 * Default Exception Constructor for this class. Call super constructor.
	 */
	CapacityOutOfBoundsException() {
		super();
	}

	/**
	 * Initial Constructor for this class. Passes the specified String message to
	 * the super class constructor.
	 * 
	 * @param s this error message.
	 */
	CapacityOutOfBoundsException(String s) {
		super(s);
	}

}
