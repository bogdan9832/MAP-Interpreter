package Model.Statements;

import java.io.BufferedReader;
import java.io.IOException;

import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Interfaces.IExpression;
import Model.Interfaces.IStatement;
import Model.Utils.ProgramState;
import Model.Utils.Interfaces.IProgramState;

public class ReadFileStatement implements IStatement {

	IExpression exp;
	String symbol;
	public ReadFileStatement(IExpression exp, String symbol) {
		this.exp = exp;
		this.symbol = symbol;
	}
	@Override
	public IProgramState execute(ProgramState state) throws InvalidSignException, InvalidFileException, IOException, DuplicateSymbolException {
		int fid = exp.resolve(state.symTabel);
		BufferedReader r = state.fileTable.getFile(fid);
		int value = 0;
		try{
			String l = r.readLine();
			value = Integer.parseInt(l);	
		} catch (NumberFormatException  e) {
			value = 0;
		}
		state.symTabel.addSymbol(symbol, value);
		return state;
	}
	@Override
	public String toString() {
		return "ReadFileStatement(" + exp.toString() + ", \"" + symbol + "\")";
	}
}
