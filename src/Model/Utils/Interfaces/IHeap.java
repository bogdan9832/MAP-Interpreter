package Model.Utils.Interfaces;

import java.util.Set;

import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidSignException;
import Model.Interfaces.IExpression;

public interface IHeap {
	public void addItem(int content);
	public Set<Integer> getKeySet();
	public int getContent(IExpression address, ISymbolTable st) throws InvalidSignException, InvalidAddressException;
}
