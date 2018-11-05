package Model.Utils;

import Model.Interfaces.IStatement;
import Model.Utils.Interfaces.IExecutionStack;
import java.util.Stack;

public class ExecutionStack implements IExecutionStack {
    private Stack<IStatement> stack;

    public ExecutionStack(){
        stack = new Stack<>();
    }

    public Stack<IStatement> getStack() {
        return stack;
    }

    public void setStack(Stack<IStatement> stack) {
        this.stack = stack;
    }
    
    @Override
    public IStatement pop() {
        return stack.pop();
    }

    @Override
    public void push(IStatement s) {
        stack.push(s);
    }

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Stack<IStatement> getIterator() {
		return (Stack<IStatement>) this.stack.clone();
	}
	public String toString() {
		String toReturn = "";
		toReturn += "ExecutionStack: \r\n";
		boolean empty = true;
		for(int i = stack.size() - 1 ; i >= 0 ; i--) {
			empty = false;			
			toReturn += '\t' + stack.get(i).toString() + "\r\n";
			
		}
		
		if(empty)
			toReturn += "Empty";
		return toReturn;
	}
}
