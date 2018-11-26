package Model.Expresions;

import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Interfaces.IExpression;
import Model.Utils.Interfaces.IHeap;
import Model.Utils.Interfaces.ISymbolTable;

public class BooleanExpression implements IExpression {
	public String operand;
	public IExpression operator1;
	public IExpression operator2;

	public BooleanExpression(String operand, IExpression operator1, IExpression operator2) {
		this.operand = operand;
		this.operator1 = operator1;
		this.operator2 = operator2;
	}

	@Override
	public int resolve(ISymbolTable st, IHeap heap)
			throws InvalidSignException, InvalidSymbolException, InvalidAddressException, NullAdressException {
		
		int operator1Result = operator1.resolve(st, heap);
		int operator2Result = operator2.resolve(st, heap);

		switch (operand) {
		case "<":
			return operator1Result < operator2Result ? 1 : 0;			
		case "<=":
			return operator1Result <= operator2Result ? 1 : 0;
		case "==":
			return operator1Result == operator2Result ? 1 : 0;
		case ">=":
			return operator1Result >= operator2Result ? 1 : 0;
		case ">":
			return operator1Result > operator2Result ? 1 : 0;
		case "!=":
			return operator1Result != operator2Result ? 1 : 0;

		default:
			throw new InvalidSignException("Valid sign expected ( <, <=, ==, !=, >, >= ). Sign given: " + operand, 1);
		}
		
	}

	@Override
	public boolean isTrue(ISymbolTable st, IHeap heap)
			throws InvalidSignException, InvalidSymbolException, InvalidAddressException, NullAdressException {
		return this.resolve(st, heap) != 0 ? true : false;
	}

	public String toString() {
		return operator1 + " " + operand + " " + operator2;
	}

}
