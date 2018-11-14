package Model.Statements;

import java.io.IOException;

import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Interfaces.IExpression;
import Model.Interfaces.IStatement;
import Model.Utils.ProgramState;
import Model.Utils.Interfaces.IProgramState;

public class CloseReadFileStatement implements IStatement {
	IExpression exp;
	public CloseReadFileStatement(IExpression exp) {
		this.exp = exp;
	}
	@Override
	public IProgramState execute(ProgramState state) throws InvalidSignException, InvalidFileException, IOException {
		
		int fid = exp.resolve(state.symTabel);	
		state.fileTable.getFile(fid).close();
		state.fileTable.removeFile(fid);
		
		return null;
	}
	
	@Override
	public String toString() {
		
		return "CloseReadFile( " + exp.toString() + ")";
	}

}
