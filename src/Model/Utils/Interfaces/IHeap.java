package Model.Utils.Interfaces;

import java.util.HashMap;
import java.util.Set;

import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Interfaces.IExpression;

public interface IHeap {
	public int addItem(int content);
	public Set<Integer> getKeySet();
	public int getContent(IExpression address, ISymbolTable st) throws InvalidSignException, InvalidAddressException, NullAdressException, InvalidSymbolException;
	public void updateValue(int address, int value) throws NullAdressException, InvalidAddressException;
	public HashMap<Integer,Integer> getContent();
	public void setContent( HashMap<Integer,Integer> c);

}
