package Model.Expresions;

import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Interfaces.IExpression;
import Model.Utils.Heap;
import Model.Utils.Interfaces.IHeap;
import Model.Utils.Interfaces.ISymbolTable;

public class HeapReadExpression implements IExpression{
	public String s;
	
	public HeapReadExpression(String name) {
		s = name;
	}
	@Override
	public int resolve(ISymbolTable st, IHeap heap) throws InvalidSymbolException, InvalidSignException, InvalidAddressException, NullAdressException {
		
			IExpression address = new ConstantExpression(st.getValueOf(s));
			return heap.getContent(address, st);
			

		
	}
	@Override
	public boolean isTrue(ISymbolTable st, IHeap heap) throws InvalidSymbolException, InvalidSignException, InvalidAddressException, NullAdressException {
		return this.resolve(st, heap) != 0 ? true : false;
	}
	public String toString() {
    	return s;
    }

	
	
}
