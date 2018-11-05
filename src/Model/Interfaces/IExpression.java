package Model.Interfaces;

import Model.Exceptions.InvalidSignException;
import Model.Utils.Interfaces.ISymbolTable;

public interface IExpression {
	public int resolve(ISymbolTable st) throws InvalidSignException;
	public boolean isTrue(ISymbolTable st) throws InvalidSignException;

}
