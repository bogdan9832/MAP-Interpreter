package View;

import Model.Utils.Interfaces.IProgramState;

public interface IView {
	int run();
	void displayMenu();
	int getInput();
	String getMessage(String toPrint);
	void displayResult(int val);
	void displayStatus(IProgramState s);
	void printMessage(String msg);
}
