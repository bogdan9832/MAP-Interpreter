package Model.Expresions;

import Model.Interfaces.IExpression;
import Model.Utils.Interfaces.ISymbolTable;

public class ConstantExpression implements IExpression {

	int s;
	
	public ConstantExpression(int val) {
		this.s = val;
	}
	
	@Override
	public int resolve(ISymbolTable st) {
		return s;
	}
	@Override
	public boolean isTrue(ISymbolTable st) {
		return this.resolve(st) != 0 ? true : false;
	}
	public String toString() {
    	return  Integer.toString(s) ;
    }

	

}
