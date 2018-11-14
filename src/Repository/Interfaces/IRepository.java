package Repository.Interfaces;

import java.io.IOException;

import Model.Utils.Interfaces.IProgramState;

public interface IRepository {
	public void addProgramState(IProgramState st);
	public IProgramState getProgramState(int index);
	public int getSize();
	public void clean();
	void logProgramStates() throws IOException;
	
}
