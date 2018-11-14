package Model.Statements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.DuplicateSymbolException;
import Model.Interfaces.IStatement;
import Model.Utils.ProgramState;
import Model.Utils.Interfaces.IProgramState;

public class OpenReadFileStatement implements IStatement {

	String fileName;
	String symbol;
	int fd;
	public OpenReadFileStatement(String fileName,String symbol) {
		this.fileName = fileName;
		this.symbol = symbol;
		
	}
	@Override
	public IProgramState execute(ProgramState state) throws FileNotFoundException, DuplicateFileException, DuplicateSymbolException {
		fd = state.fileTable.count();
		
		state.fileTable.addFile(fd, new BufferedReader(new FileReader(new File(fileName))));
		state.symTabel.addSymbol(symbol,fd);
		
		return state;
	}
	
	@Override
	public String toString() {
		return "OpenReadFileStatement(" + symbol + ", \"" + fileName + "\")";
	}

}
