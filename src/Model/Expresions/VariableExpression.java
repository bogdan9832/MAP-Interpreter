package Model.Expresions;

import Model.Interfaces.IExpression;
import Model.Utils.Interfaces.IHeap;
import Model.Utils.Interfaces.ISymbolTable;

public class VariableExpression implements IExpression{
	public String s;
	
	public VariableExpression(String name) {
		s = name;
	}
	@Override
	public int resolve(ISymbolTable st, IHeap heap) {
		try {
			return st.getValueOf(s);
			
		}
		catch(Exception ex) {
			try {
				st.addSymbol(s,0);
			} catch (Exception e) {					
				return 0;
			}
			return 0;
		}
	}
	@Override
	public boolean isTrue(ISymbolTable st, IHeap heap) {
		return this.resolve(st, heap) != 0 ? true : false;
	}
	public String toString() {
    	return s;
    }

	
	
}
