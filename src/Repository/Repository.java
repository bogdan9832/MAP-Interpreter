package Repository;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Model.Utils.ProgramState;
import Model.Utils.Interfaces.IProgramState;
import Repository.Interfaces.IRepository;;
public class Repository implements IRepository {

	public ArrayList<ProgramState> list;
	public String logFilePath;
	public Repository(String logFilePath) {
		list = new ArrayList<>();
		this.logFilePath = logFilePath;
	}
	@Override
	public void addProgramState(IProgramState st) {
		list.add((ProgramState) st);
	}

	
	@Override
	public int getSize() {
		return list.size();
	}
	@Override
	public void logProgramStates(IProgramState st) throws IOException {
		PrintWriter printWriter;
		
		printWriter = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
		printWriter.print(st.toString());
		printWriter.close();
		
		
		
	}
	@Override
	public void clean() {
		for(int i = 1 ; i < list.size(); i++)
			list.remove(i);
		
	}
	@Override
	public List<ProgramState> getProgramStateList() {
		
		return list;
	}
	@Override
	public void setProgramStateList(List<ProgramState> lst) {
		this.list = (ArrayList<ProgramState>) lst;
		
	}
	@Override
	public IProgramState getFirstProgramState() {
		return list.get(0);
	}
	
}
