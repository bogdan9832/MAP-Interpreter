package Model.Expresions;

import Model.Interfaces.IExpression;
import Model.Utils.Interfaces.IHeap;
import Model.Utils.Interfaces.ISymbolTable;

public class ConstantExpression implements IExpression {

	int s;
	
	public ConstantExpression(int val) {
		this.s = val;
	}
	
	@Override
	public int resolve(ISymbolTable st, IHeap heap) {
		return s;
	}
	@Override
	public boolean isTrue(ISymbolTable st, IHeap heap) {
		return this.resolve(st,heap) != 0 ? true : false;
	}
	public String toString() {
    	return  Integer.toString(s) ;
    }

	

}
