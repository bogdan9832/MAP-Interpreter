package Model.Utils;

import Model.Utils.Interfaces.IProgramState;

import java.util.ArrayList;

import Model.Interfaces.IStatement;
import Model.Utils.Interfaces.IExecutionStack;
import Model.Utils.Interfaces.IOutput;
import Model.Utils.Interfaces.ISymbolTable;

public class ProgramState implements IProgramState {

	public IExecutionStack exeStack;
	public IOutput output;
	public ISymbolTable symTabel;
	public ProgramState(IExecutionStack exeStack, IOutput output, ISymbolTable simTable) {
		// TO-DO Replace null with instantiation
		this.exeStack = (exeStack == null) ? new ExecutionStack() : exeStack;
		this.output = (output == null) ? new Output() : output;
		this.symTabel = (simTable == null) ? new SymbolTable() : simTable;
	}
	public void addStatement(IStatement s) {
		exeStack.push(s);
	}
	public void executeNextStep() throws Exception {
		if(!exeStack.isEmpty()) {
			IStatement s = exeStack.pop();
			
			s.execute(this);
			
		}
		else {
			throw new Exception();
		}
		
	}
	public String toString() {
		String toReturn = "+---------------------------------------------------------------\r\n\r\n";
		toReturn += exeStack.toString();
		toReturn += "\r\n";
		toReturn += symTabel.toString();
		toReturn += "\r\n";
		toReturn += output.toString();
		toReturn += "\r\n";
		
		return toReturn;
	}
	@Override
	public ArrayList<Integer> getOutput() {
		return (ArrayList<Integer>) this.output.getIterator();
	}

}
