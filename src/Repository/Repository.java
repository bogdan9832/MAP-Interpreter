package Repository;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Model.Utils.Interfaces.IProgramState;
import Repository.Interfaces.IRepository;;
public class Repository implements IRepository {

	public ArrayList<IProgramState> list;
	public String logFilePath;
	public Repository(String logFilePath) {
		list = new ArrayList<>();
		this.logFilePath = logFilePath;
	}
	@Override
	public void addProgramState(IProgramState st) {
		list.add(st);
	}

	@Override
	public IProgramState getProgramState(int index){
		return list.get(index);

	}
	@Override
	public int getSize() {
		return list.size();
	}
	@Override
	public void logProgramStates() throws IOException {
		PrintWriter printWriter;
		
		printWriter = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
		printWriter.print(list.get(list.size() - 1).toString());
		printWriter.close();
		
		
		
	}
	@Override
	public void clean() {
		for(int i = 1 ; i < list.size(); i++)
			list.remove(i);
		
	}
	
}
