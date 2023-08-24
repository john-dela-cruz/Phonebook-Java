package exception;

public class PNumberException extends Exception{

	public static boolean checkNumberLength(int number){
		
		if(number == 11){
			return true;
		}
		else {
			return false;
		}
	}
	
	public PNumberException(){
		super("The phone number you have entered is invalid! \n");
	}
}
