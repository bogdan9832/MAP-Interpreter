package Model.Statements;

import Model.Exceptions.InvalidSignException;
import Model.Interfaces.IExpression;
import Model.Utils.Interfaces.IProgramState;
import Model.Interfaces.IStatement;
import Model.Utils.ProgramState;

public class PrintStatement implements IStatement {
    private IExpression exp;
    public PrintStatement(IExpression exp){
        this.setExp(exp);
    }
    @Override
    public IProgramState execute(ProgramState state) throws InvalidSignException 
    {

        state.output.addOutput(getExp().resolve(state.symTabel));
        return state;
    }

    public IExpression getExp() {
        return exp;
    }

    public void setExp(IExpression exp) {
        this.exp = exp;
    }
    public String toString() {
    	return "Print( " + exp.toString() +  " )";
    }
}
