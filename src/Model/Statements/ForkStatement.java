package Model.Statements;

import java.io.IOException;

import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Interfaces.IStatement;
import Model.Utils.ExecutionStack;
import Model.Utils.ProgramState;
import Model.Utils.SymbolTable;
import Model.Utils.Interfaces.IProgramState;

public class ForkStatement implements IStatement {

	public IStatement statement;
	public ForkStatement(IStatement statement) {
		this.statement = statement;
	}
	@Override
	public IProgramState execute(ProgramState state)
			throws InvalidSignException, DuplicateSymbolException, InvalidFileException, IOException,
			DuplicateFileException, InvalidSymbolException, InvalidAddressException, NullAdressException {
		IProgramState newState = new ProgramState(
								new ExecutionStack(),
								state.output,
								new SymbolTable((SymbolTable)state.symTabel),
								state.fileTable,
								state.heap
								);
		newState.addStatement(this.statement);
		return newState;
	}
	@Override
	public String toString() {
		return "fork(" + statement.toString() + ")";
	}

}
