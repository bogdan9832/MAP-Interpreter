package Model.Statements;

import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Interfaces.IExpression;
import Model.Utils.Interfaces.IProgramState;
import Model.Interfaces.IStatement;
import Model.Utils.ProgramState;

public class WhileStatement implements IStatement {
	private IExpression exp;
	private IStatement statementToExecute;

	public WhileStatement(IExpression exp, IStatement statementToExecute) {
		this.exp = exp;
		this.statementToExecute = statementToExecute;
	}

	@Override
	public IProgramState execute(ProgramState state) throws InvalidSignException, InvalidSymbolException, InvalidAddressException, NullAdressException {
		if(exp.isTrue(state.symTabel, state.heap)) {
			state.exeStack.push(this);
			state.exeStack.push(statementToExecute);
		}
		
		return null;
	}

	public IExpression getExp() {
		return exp;
	}

	public void setExp(IExpression exp) {
		this.exp = exp;
	}

	
	public String toString() {
		return "While( " + exp.toString() + " then: " + statementToExecute.toString() + " ";
	}
}
