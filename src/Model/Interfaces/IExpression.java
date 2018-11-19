package Model.Interfaces;

import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Utils.Interfaces.IHeap;
import Model.Utils.Interfaces.ISymbolTable;

public interface IExpression {
	public boolean isTrue(ISymbolTable st, IHeap heap) throws InvalidSignException, InvalidSymbolException, InvalidAddressException, NullAdressException;
	int resolve(ISymbolTable st, IHeap heap) throws InvalidSymbolException, InvalidSignException, InvalidAddressException, NullAdressException;

}
