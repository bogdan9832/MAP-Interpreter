package Model.Expresions;

import Model.Interfaces.IExpression;
import Model.Utils.Interfaces.ISymbolTable;

public class VariableExpression implements IExpression{
	public String s;
	
	public VariableExpression(String name) {
		s = name;
	}
	@Override
	public int resolve(ISymbolTable st) {
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
	public boolean isTrue(ISymbolTable st) {
		return this.resolve(st) != 0 ? true : false;
	}
	public String toString() {
    	return "ConstantExpression( "+ s + ")";
    }

	
	
}
