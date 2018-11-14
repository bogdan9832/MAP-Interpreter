package Controller.Interfaces;

import java.io.IOException;

import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidStateException;
import Model.Utils.Interfaces.IProgramState;
public interface IController {
	public void allSteps() throws InvalidStateException, InvalidSignException,
		DuplicateSymbolException, InvalidFileException, DuplicateFileException, IOException;
	public void nextStep() throws  InvalidStateException, InvalidSignException,
		DuplicateSymbolException, InvalidFileException, DuplicateFileException, IOException;
	public IProgramState getNextState(IProgramState s) throws InvalidStateException,
		InvalidSignException, DuplicateSymbolException, InvalidFileException, IOException,
		DuplicateFileException;
	
}
