package Model.Statements;

import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Interfaces.IExpression;
import Model.Interfaces.IStatement;
import Model.Utils.ProgramState;
import Model.Utils.SymbolTable;
import Model.Utils.Interfaces.IProgramState;

public class HeapWritingStatement implements IStatement {

	String varName;
	IExpression val;

	public HeapWritingStatement(String varName, IExpression val) {
		this.varName = varName;
		this.val = val;
	}

	@Override
	public IProgramState execute(ProgramState state) throws InvalidSignException, DuplicateSymbolException,
			InvalidSymbolException, InvalidAddressException, NullAdressException {
		int value = val.resolve(state.symTabel, state.heap);

		int address = state.symTabel.getValueOf(varName);
		state.heap.updateValue(address, value);
		return state;
	}

}
