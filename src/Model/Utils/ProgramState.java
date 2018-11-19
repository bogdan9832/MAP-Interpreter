package Model.Utils;

import Model.Utils.Interfaces.IProgramState;

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
import Model.Utils.Interfaces.IExecutionStack;
import Model.Utils.Interfaces.IFileTable;
import Model.Utils.Interfaces.IHeap;
import Model.Utils.Interfaces.IOutput;
import Model.Utils.Interfaces.ISymbolTable;

public class ProgramState implements IProgramState {

	public IExecutionStack exeStack;
	public IOutput output;
	public ISymbolTable symTabel;
	public IFileTable fileTable;
	public IHeap heap;

	public ProgramState() {

		this.exeStack = new ExecutionStack();
		this.output = new Output();
		this.symTabel = new SymbolTable();
		this.fileTable = new FileTable();
		this.heap = new Heap();
	}

	public ProgramState(IExecutionStack exeStack, IOutput output, ISymbolTable simTable, IFileTable fileTable,
			IHeap heap) {

		this.exeStack = exeStack;
		this.output = output;
		this.symTabel = simTable;
		this.fileTable = fileTable;
		this.heap = heap;

	}

	public ProgramState(ProgramState p) {
		this.exeStack = new ExecutionStack((ExecutionStack) p.exeStack);
		this.output = new Output((Output) p.output);
		this.symTabel = new SymbolTable((SymbolTable) p.symTabel);
		this.fileTable = new FileTable((FileTable) p.fileTable);
		this.heap = new Heap((Heap) p.heap);
	}

	public void addStatement(IStatement s) {
		exeStack.push(s);
	}

	public void executeNextStep() throws InvalidSignException, InvalidStateException, DuplicateSymbolException,
			InvalidFileException, IOException, DuplicateFileException, InvalidSymbolException, InvalidAddressException, NullAdressException {
		if (!exeStack.isEmpty()) {
			IStatement s = exeStack.pop();

			s.execute(this);

		} else {
			throw new InvalidStateException("End of stack reached", 0);
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
		toReturn += heap.toString();
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

	@Override
	public IHeap getHeap	() {
		return heap;
	}

	@Override
	public ISymbolTable getSymTable() {
		return symTabel;
	}

}
