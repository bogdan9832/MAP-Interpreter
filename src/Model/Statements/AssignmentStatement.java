package Model.Statements;

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
    public IProgramState execute(ProgramState state) throws Exception {
    	
    	
			state.symTabel.addSymbol(s, this.expression.resolve(state.symTabel));
		
        return state;
    }
    public String toString() {
    	return "AssignmentStatement( "+ s +", " + expression.toString() +")";
    }
    

    public IExpression getExpression() {
        return expression;
    }

    public void setExpression(IExpression expression) {
        this.expression = expression;
    }
}
