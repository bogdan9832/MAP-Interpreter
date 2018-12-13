package Model.Statements;

import java.io.BufferedReader;
import java.io.IOException;

import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
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
	public IProgramState execute(ProgramState state) throws InvalidSignException, InvalidFileException, IOException, DuplicateSymbolException, InvalidSymbolException, InvalidAddressException, NullAdressException {
		int fid = exp.resolve(state.symTabel,  state.heap);
		BufferedReader r = state.fileTable.getFile(fid);
		int value = 0;
		try{
			String l = r.readLine();
			value = Integer.parseInt(l);	
		} catch (NumberFormatException  e) {
			value = 0;
		}
		state.symTabel.addSymbol(symbol, value);
		return null;
	}
	@Override
	public String toString() {
		return "ReadFileStatement(" + exp.toString() + ", \"" + symbol + "\")";
	}
}
