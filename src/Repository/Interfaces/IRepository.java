package Repository.Interfaces;

import Model.Utils.Interfaces.IProgramState;

public interface IRepository {
	public void addProgramState(IProgramState st);
	public IProgramState getProgramState(int index);
	public int getSize();
	void logProgramStates();
	
}
