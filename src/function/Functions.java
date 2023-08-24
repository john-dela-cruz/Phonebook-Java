package function;

import exception.PNumberException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exception.PNumberException;

public class Functions {
	public static void checkTextFile(String textFile){
		try{
			BufferedReader br = new BufferedReader(new FileReader(textFile));
			if(br.readLine() == null){
				System.out.println("The file is Empty \n");
			}
			br.close();
		}
		catch(IOException ioe) {
			System.err.println("Error");
		}
	}
	
	public static int getNumberLength(String number){
		int numberLength = number.length();
		return numberLength;
	}
	
	public static boolean checkPhoneNumber(int number) throws PNumberException{
		if(PNumberException.checkNumberLength(number)){
			System.out.println("Added new contact \n");
			return true;
		}
		else{
			throw new PNumberException();
		}
	}
}
