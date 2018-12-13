package Repository.Interfaces;

import java.io.IOException;
import java.util.List;

import Model.Utils.ProgramState;
import Model.Utils.Interfaces.IProgramState;

public interface IRepository {
	public void addProgramState(IProgramState st);	
	public int getSize();
	public void clean();
	void logProgramStates(IProgramState st) throws IOException;
	List<ProgramState> getProgramStateList();
	void setProgramStateList(List<ProgramState> prgList);
	IProgramState getFirstProgramState();
}
