package Model.Utils.Interfaces;

import java.util.Set;

import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidSymbolException;;

public interface ISymbolTable {
	int getValueOf(String s) throws InvalidSymbolException;
	void addSymbol (String s, int val) throws DuplicateSymbolException;
	void setValue(String s , int val) throws InvalidSymbolException;
	Set<String> getKeyset();
	String toString();
}