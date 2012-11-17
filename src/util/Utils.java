package util;

import java.util.Scanner;

public class Utils {
	/*
	 * Get Windows's current username
	 */
	public static String getWindowsCurrentUser() {
		return System.getProperty("user.name");
	}

	/*
	 * Print a string on the screen and then wait for an input
	 * 
	 * @param s the string u want to print
	 */
	public static String consolePrintAndReadLine(String printStr) {
		System.out.print(printStr);
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
}
