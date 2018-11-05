package Model.Statements;

import Model.Utils.Interfaces.IProgramState;
import Model.Interfaces.IStatement;
import Model.Utils.ProgramState;

public class CompoundStatement implements IStatement {
    private IStatement statement1;
    private IStatement statement2;

	public CompoundStatement(IStatement statement1, IStatement statement2){
		this.setStatement1(statement1);
		this.setStatement2(statement2);
	}

		@Override
	public IProgramState execute(ProgramState state) throws Exception {
       state.exeStack.push(statement2);
       state.exeStack.push(statement1);
       

		return state;
	}


    public IStatement getStatement1() {
        return statement1;
    }

    public void setStatement1(IStatement statement1) {
        this.statement1 = statement1;
    }

    public IStatement getStatement2() {
        return statement2;
    }

    public void setStatement2(IStatement statement2) {
        this.statement2 = statement2;
    }
    public String toString() {
    	return "CompoundStatement( "+ statement1.toString() +", " + statement2.toString() +")";
    }
}