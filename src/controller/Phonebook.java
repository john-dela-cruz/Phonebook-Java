package controller;

import utility.Helper;
import function.Functions;
import exception.PNumberException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Phonebook {

	public static void main(String[] args) {
		int choice = 0;
		
		while(choice != 5){
			System.out.println("PHONE BOOK");
			System.out.println("1 -> Add new contact");
			System.out.println("2 -> Edit contact");
			System.out.println("3 -> Delete contact");
			System.out.println("4 -> Display all contacts");
			System.out.println("5 -> Exit \n");
			
			choice = Helper.readInt("Select an action to perform");
			
			if(choice == 1){
				addContact();
			}
			else if(choice == 2){
				editContact();
			}
			else if(choice == 3){
				deleteContact();
			}
			else if(choice == 4){
				displayContact();
			}
			else if(choice == 5){
				System.out.println("Program Exiting...");
			}
			else{
				System.out.println("Input Error! Try Again. \n");
			}
		}
	}
	
	static void displayContact(){
		String line;
		String textFile = "phonebook.txt";
		
		Functions.checkTextFile(textFile);
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(textFile));
			while((line = br.readLine()) != null){
				String[] contact = line.split(",");
				System.out.println(contact[0] + " | " + contact[1]);
			}
			
			br.close();
			System.out.println("");
		}
		catch(IOException ioe) {
			System.err.println("Error");
		}
		
	}
	
	static void addContact(){
		String contactName;
		String contactNumber;
		int numberLength;
		
		try{
			
			FileWriter fw = new FileWriter("phonebook.txt",true);
			PrintWriter pw = new PrintWriter(fw);
			
			try{
				contactName = Helper.readString("name");
				contactNumber = Helper.readString("phone number");
				
				numberLength = Functions.getNumberLength(contactNumber);
				
				Functions.checkPhoneNumber(numberLength);
				
				StringBuilder builder = new StringBuilder();
				builder.append(contactName + ",");
				builder.append(contactNumber + "\n");
				pw.write(builder.toString());
				pw.close();
			}
			catch(PNumberException pe){
				System.err.println(pe.getMessage());
				pw.close();
			}
		
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	static void editContact(){
		String filepath = "phonebook.txt";
		String editContact;
		String tempFile = "temp.txt";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		String line;
		int numberLength = 0;
		String newName;
		String newNumber;
		
		try{
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			editContact = Helper.readString("name of the contact that will be edited");
			newName = Helper.readString("the new name of this contact");
			newNumber = Helper.readString("the new number of this contact");
			
			Functions.checkTextFile(filepath);
			
			try{
						
				numberLength = Functions.getNumberLength(newNumber);
						
				Functions.checkPhoneNumber(numberLength);
						
			}
			catch(PNumberException pe){
				System.err.println(pe.getMessage());
				br.close();
				pw.flush();
				pw.close();
				File temp = new File(tempFile);
				temp.delete();
			}
			
			while((line = br.readLine()) != null){
				String[] contacts = line.split(",");
						
				if(contacts[0].equals(editContact) && numberLength == 11){
					pw.println(newName + "," + newNumber);
				}
				else{
					pw.println(contacts[0] + "," + contacts[1]);
				}
				
				
			}
			
			br.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File current = new File(filepath);
			newFile.renameTo(current);
		}
		catch(IOException e){
			System.err.println("Error");
		}
	}
	
	static void deleteContact(){
		String filepath = "phonebook.txt";
		String deleteContact;
		String tempFile = "temp.txt";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		String line;
		
		deleteContact = Helper.readString("name of the contact that will be deleted");
		
		Functions.checkTextFile(filepath);
		
		try{
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			
			while((line = br.readLine()) != null){
				String[] contacts = line.split(",");
				
				if(!contacts[0].equals(deleteContact)){
					pw.println(contacts[0] + "," + contacts[1]);
				}
			}
			
			br.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File current = new File(filepath);
			newFile.renameTo(current);
			System.out.println("Contact deletion complete.");
		}
		catch(IOException ioe) {
			System.err.println("Error");
		}
	}
	
}
