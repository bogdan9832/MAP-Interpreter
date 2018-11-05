package Model.Utils.Interfaces;

import java.util.Stack;

import Model.Interfaces.IStatement;

public interface IExecutionStack {
	IStatement pop();
	void push(IStatement s);
	boolean isEmpty();
	Stack<IStatement> getIterator();
	String toString();
}
