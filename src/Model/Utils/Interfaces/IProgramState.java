package Model.Utils.Interfaces;

import java.io.IOException;
import java.util.ArrayList;

import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidStateException;

public interface IProgramState  {
	public void executeNextStep() throws InvalidStateException, InvalidSignException, DuplicateSymbolException, InvalidFileException, IOException, DuplicateFileException;
	public String toString();
	public ArrayList<Integer> getOutput();
	public boolean isDone();
}
