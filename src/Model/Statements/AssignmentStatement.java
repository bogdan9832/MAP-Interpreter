package Model.Statements;

import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Interfaces.IExpression;
import Model.Utils.Interfaces.IProgramState;
import Model.Interfaces.IStatement;
import Model.Utils.ProgramState;

public class AssignmentStatement implements IStatement {

    private String s ;
    private IExpression expression;
    public AssignmentStatement( String s, IExpression exp){
        this.s = s;
        this.setExpression(exp);
    }
    @Override
    public IProgramState execute(ProgramState state) throws DuplicateSymbolException, InvalidSignException, InvalidSymbolException, InvalidAddressException, NullAdressException {
    	
    	
			state.symTabel.addSymbol(s, this.expression.resolve(state.symTabel, state.heap));
		
        return state;
    }
    public String toString() {
    	return s + " = " + expression.toString() +" ";
    }
    

    public IExpression getExpression() {
        return expression;
    }

    public void setExpression(IExpression expression) {
        this.expression = expression;
    }
}
