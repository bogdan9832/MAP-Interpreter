package Model.Statements;

import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
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
    public IProgramState execute(ProgramState state) throws InvalidSignException, InvalidSymbolException, InvalidAddressException, NullAdressException 
    {

        state.output.addOutput(getExp().resolve(state.symTabel , state.heap));
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
