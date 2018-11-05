package Model.Utils.Interfaces;

import java.util.ArrayList;

public interface IProgramState  {
	public void executeNextStep() throws Exception;
	public String toString();
	public ArrayList<Integer> getOutput();
}
