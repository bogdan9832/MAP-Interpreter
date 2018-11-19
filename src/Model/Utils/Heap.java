package Model.Utils;

import java.util.HashMap;
import java.util.Set;

import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidSignException;
import Model.Interfaces.IExpression;
import Model.Utils.Interfaces.IHeap;
import Model.Utils.Interfaces.ISymbolTable;

public class Heap implements IHeap {

	private HashMap<Integer,Integer> heap;
	private int nextAddress;
	public Heap() {
		this.nextAddress = 1;
	}
	@Override
	public void addItem( int content) {
		heap.put(nextAddress, content);
		nextAddress++;
	}

	@Override
	public Set<Integer> getKeySet() {
	
		return heap.keySet();
	}

	@Override
	public int getContent(IExpression address, ISymbolTable st) throws InvalidSignException, InvalidAddressException {
		int actualAddress = address.resolve(st);
		
		if(heap.get(actualAddress) == null)
			throw new InvalidAddressException("Invalid address " + actualAddress,0);
		
		return heap.get(actualAddress);
	}

}
