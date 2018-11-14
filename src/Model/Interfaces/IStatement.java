package Model.Interfaces;

import java.io.IOException;

import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Utils.ProgramState;
import Model.Utils.Interfaces.IProgramState;

public interface IStatement {
	
	public IProgramState execute(ProgramState state) throws InvalidSignException, DuplicateSymbolException, InvalidFileException, IOException, DuplicateFileException;
}
