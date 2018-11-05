package Model.Utils;

import java.util.ArrayList;
import java.util.List;

import Model.Utils.Interfaces.IOutput;

public class Output implements IOutput {

	ArrayList<Integer> out;
	public Output() {
		out = new ArrayList<>();
	}
	@Override
	public void addOutput(int s) {
		out.add(s);
	}
	@Override
	public List<Integer> getIterator() {
		return out;
	}
	
	public String toString() {
		String toReturn = "Output: \r\n\t";
		for(int i : out) {
			toReturn += i + ", " ;
		}
		if(out.size() == 0)
				toReturn += "Empty\r\n";
		return toReturn;
	}
	
	
}
