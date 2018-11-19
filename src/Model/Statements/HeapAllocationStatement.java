package Model.Statements;

import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Interfaces.IExpression;
import Model.Interfaces.IStatement;
import Model.Utils.ProgramState;
import Model.Utils.Interfaces.IProgramState;

public class HeapAllocationStatement implements IStatement{

	String varName;
	IExpression exp;
	public HeapAllocationStatement(String varName, IExpression exp) {
		this.varName = varName;
		this.exp = exp;
	}
	@Override
	public IProgramState execute(ProgramState state) throws InvalidSignException, DuplicateSymbolException, InvalidSymbolException, InvalidAddressException, NullAdressException { 
		int value = exp.resolve(state.symTabel, state.heap);
		
		int address = state.heap.addItem(value);
		
		state.symTabel.addSymbol(varName, address);
		return state;
	}
	
	@Override
	public String toString() {
		return "new( " + varName + ", " + exp.toString() + ")";
	}

}
