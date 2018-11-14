package Model.Comands;

import java.io.IOException;

import Controller.Controller;
import Controller.Interfaces.IController;
import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidStateException;
import Model.Utils.Interfaces.PrintCallBack;

public class RunExample extends Command {
	IController c;
	public RunExample(String key, String description, Controller c) {
		super(key,description);
		
		this.c = c;
	}
	
	@Override
	public void setCallBack(PrintCallBack callback) {
		
		super.setCallBack(callback);
		((Controller) c).callback = callback;
	}
	@Override
	public void execute() throws InvalidStateException, InvalidSignException, DuplicateSymbolException, InvalidFileException, DuplicateFileException, IOException {
		
			c.allSteps();
		

	}

}
