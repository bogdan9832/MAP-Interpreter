package Model.Utils;

import Model.Utils.Interfaces.IProgramState;

import java.io.IOException;
import java.util.ArrayList;

import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidStateException;
import Model.Interfaces.IStatement;
import Model.Utils.Interfaces.IExecutionStack;
import Model.Utils.Interfaces.IFileTable;
import Model.Utils.Interfaces.IOutput;
import Model.Utils.Interfaces.ISymbolTable;

public class ProgramState implements IProgramState {

	public IExecutionStack exeStack;
	public IOutput output;
	public ISymbolTable symTabel;
	public IFileTable fileTable;
	public ProgramState(IExecutionStack exeStack, IOutput output, ISymbolTable simTable, IFileTable table) {

		this.exeStack = (exeStack == null) ? new ExecutionStack() : exeStack;
		this.output = (output == null) ? new Output() : output;
		this.symTabel = (simTable == null) ? new SymbolTable() : simTable;
		this.fileTable = (fileTable == null) ? new FileTable() : fileTable;
	}
	
	public ProgramState(ProgramState p) {
		this.exeStack = new ExecutionStack((ExecutionStack)p.exeStack);
		this.output = new Output((Output)p.output);
		this.symTabel = new SymbolTable((SymbolTable)p.symTabel);
		this.fileTable = new FileTable((FileTable)p.fileTable);
	}
	
	public void addStatement(IStatement s) {
		exeStack.push(s);
	}
	public void executeNextStep() throws  InvalidSignException, InvalidStateException, DuplicateSymbolException, InvalidFileException, IOException, DuplicateFileException {
		if(!exeStack.isEmpty()) {
			IStatement s = exeStack.pop();
			
			s.execute(this);
			
		}
		else {
			throw new InvalidStateException("End of stack reached",0);
		}
	}
	public String toString() {
		String toReturn = "+---------------------------------------------------------------\r\n\r\n";
		toReturn += exeStack.toString();
		toReturn += "\r\n";
		toReturn += symTabel.toString();
		toReturn += "\r\n";
		toReturn += fileTable.toString();
		toReturn += "\r\n";
		toReturn += output.toString();
		toReturn += "\r\n";
		
		return toReturn;
	}
	@Override
	public ArrayList<Integer> getOutput() {
		return (ArrayList<Integer>) this.output.getIterator();
	}

	@Override
	public boolean isDone() {
		return this.exeStack.isEmpty();
	}

}
