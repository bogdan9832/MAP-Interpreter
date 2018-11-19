package Model.Statements;

import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Interfaces.IExpression;
import Model.Utils.Interfaces.IProgramState;
import Model.Interfaces.IStatement;
import Model.Utils.ProgramState;

public class IfStatement implements IStatement {
	private IExpression exp;
	private IStatement trueStatement;
	private IStatement falseStatement;

	public IfStatement(IExpression exp, IStatement trueStatement, IStatement falseStatement) {
		this.setExp(exp);
		this.setTrueStatement(trueStatement);
		this.setFalseStatement(falseStatement);
	}

	@Override
	public IProgramState execute(ProgramState state) throws InvalidSignException, InvalidSymbolException, InvalidAddressException, NullAdressException {
		if (getExp().isTrue(state.symTabel, state.heap)) {
			state.exeStack.push(trueStatement);
		} else {
			state.exeStack.push(falseStatement);
		}
		return state;
	}

	public IExpression getExp() {
		return exp;
	}

	public void setExp(IExpression exp) {
		this.exp = exp;
	}

	public IStatement getTrueStatement() {
		return trueStatement;
	}

	public void setTrueStatement(IStatement trueStatement) {
		this.trueStatement = trueStatement;
	}

	public IStatement getFalseStatement() {
		return falseStatement;
	}

	public void setFalseStatement(IStatement falseStatement) {
		this.falseStatement = falseStatement;
	}

	public String toString() {
		return "If " + exp.toString() + " then: " + trueStatement.toString() + "else:" + falseStatement.toString()
				+ " ";
	}
}
