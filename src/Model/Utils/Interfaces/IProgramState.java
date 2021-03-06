package Model.Utils.Interfaces;

import java.io.IOException;
import java.util.ArrayList;

import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidStateException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Interfaces.IStatement;
import Model.Utils.ProgramState;

public interface IProgramState  {
	public void executeNextStep()
			throws InvalidStateException, InvalidSignException, DuplicateSymbolException, InvalidFileException,
			IOException, DuplicateFileException, InvalidSymbolException, InvalidAddressException, NullAdressException;
	public String toString();
	public ArrayList<Integer> getOutput();
	public boolean isDone();
	public boolean isNotDone();
	public ProgramState oneStep() throws InvalidStateException, InvalidSignException, DuplicateSymbolException, InvalidFileException, IOException, DuplicateFileException, InvalidSymbolException, InvalidAddressException, NullAdressException;
	public IHeap getHeap();
	public ISymbolTable getSymTable();
	public IFileTable getFileTable();
	void addStatement(IStatement s);
}
