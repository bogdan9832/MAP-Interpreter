package Model.Utils;


import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidSymbolException;
import Model.Utils.Interfaces.ISymbolTable;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Set;

public class SymbolTable implements ISymbolTable {

	HashMap<String,Integer> symbols;
	
	public SymbolTable() {
		symbols = new HashMap<>();
	}
	// replace with custom exception
	@Override
	public int getValueOf(String s) throws InvalidSymbolException  {
		
		int val = symbols.get(s);
		if(symbols.get(s) == null)
			throw new InvalidSymbolException("Symbol does not exist",1);
		return val;		
	}

	@Override
	public void addSymbol(String s, int val) throws DuplicateSymbolException{
		

			symbols.put(s, val);
			
	}
	@Override
	public void setValue(String s, int val) throws InvalidSymbolException {		
		this.getValueOf(s);
		symbols.put(s, val);
				
	}
	@Override
	public Set<String> getKeyset() {
		return this.symbols.keySet();
		 
	}
	public String toString() {
		String toReturn = "";
		Formatter form = new Formatter();
		boolean empty = true;
		toReturn += "\r\nSymbolTable: \r\n";
		
		for(String k : symbols.keySet()) {
			try {			
				form.format("	%-5s = %d\r\n", k, symbols.get(k));
				empty = false;
			} catch (Exception e) {
				
			}
		}
		if(empty)
			toReturn += "	Empty\r\n";
		toReturn += form.toString();
		form.close();
		return toReturn;
	}

    
}
