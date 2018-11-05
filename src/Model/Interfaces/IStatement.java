package Model.Interfaces;

import Model.Exceptions.InvalidSignException;
import Model.Utils.ProgramState;
import Model.Utils.Interfaces.IProgramState;

public interface IStatement {
	
	public IProgramState execute(ProgramState state) throws InvalidSignException, Exception;
}
