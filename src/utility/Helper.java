package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Helper {
	
	private static BufferedReader getReader(){
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader;
	}
	
	public static String readString(String message){
		System.out.print("Enter " + message + ": ");
		String input = null;
		
		try {
			input = getReader().readLine();
		} catch (IOException ioe){
			System.err.println(ioe.getMessage());
		}
		return input;
	}
	
	public static String readMessage(String message){
		System.out.print(message);
		String input = null;
		
		try {
			input = getReader().readLine();
		} catch (IOException ioe){
			System.err.println(ioe.getMessage());
		}
		return input;
	}
	
	public static int readInt(String message){
		System.out.print("Enter " + message + ": ");
		int input = 0;
		
		try {
			input = Integer.parseInt(getReader().readLine());
		} catch (IOException ioe){
			System.err.println(ioe.getMessage());
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid input: " + nfe.getMessage());
		}
		return input;
	}
	
	public static double readDouble(String message){
		System.out.print("Enter " + message + ": ");
		double input = 0;
		
		try {
			input = Double.parseDouble(getReader().readLine());
		} catch (IOException ioe){
			System.err.println(ioe.getMessage());
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid input: " + nfe.getMessage());
		}
		return input;
	}
}

