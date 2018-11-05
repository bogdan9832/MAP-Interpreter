package View;

import java.util.Scanner;

import Model.Utils.Interfaces.IProgramState;


public class View implements IView{
	Scanner sc;
	public View() {
		sc = new Scanner(System.in);
	}
	@Override
	public int run() {
		
			displayMenu();
			return getInput();
			
		
	}

	@Override
	public void displayMenu() {
		System.out.println("1. v = 2; print(v)");
		System.out.println("2. a=2+3*5;\r\n" + 
				   "   b=a-4/2 + 7;\r\n" + 
				   "   Print(b)");
		System.out.println("2. a=2-2;\r\n" + 
						   "   If a Then v=2 Else v=3;\r\n" + 
						   "   Print(v)");
		System.out.println("0. Exit");
		
	}
	
	
	
	@Override
	public int getInput() {
		try {
		return sc.nextInt();
		}
		catch(Exception e){
			System.out.println("Invalid Input");
			return 0;
		}
		
	}
	@Override
	public void displayResult(int val) {
		System.out.println(val);
		
	}
	
	@Override
	public void displayStatus(IProgramState s) {
		System.out.println(s.toString());
		
	}
	@Override
	public void printMessage(String msg) {
		System.out.println("MESSAGE: " + msg);
		
	}
	@Override
	public String getMessage(String toPrint) {
		System.out.println(toPrint);
		return sc.nextLine();
	}

}
