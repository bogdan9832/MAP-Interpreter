package Controller;

import java.io.IOException;

import Controller.Interfaces.IController;
import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidStateException;
import Model.Utils.ProgramState;
import Model.Utils.Interfaces.IProgramState;
import Model.Utils.Interfaces.PrintCallBack;
import Repository.Interfaces.IRepository;

public class Controller implements IController {

	IRepository repo;
	public PrintCallBack callback;
	public Controller(IRepository r) {
		repo = r;
	}

	public ProgramState getNextState(IProgramState s) throws InvalidStateException, InvalidSignException,
			DuplicateSymbolException, InvalidFileException, IOException, DuplicateFileException {
		ProgramState st = new ProgramState((ProgramState) s);
		repo.addProgramState(st);
		st.executeNextStep();
		return st;
	}

	@Override
	public void allSteps() throws InvalidStateException, InvalidSignException, DuplicateSymbolException,
			InvalidFileException, IOException, DuplicateFileException {
		repo.clean();
		IProgramState s = repo.getProgramState(0);

		repo.logProgramStates();
		callback.printCallBack(s.toString());

		while (!s.isDone()) {
			s = this.getNextState(s);
			repo.logProgramStates();
			callback.printCallBack(s.toString());

		}
	}

	@Override
	public void nextStep() throws IOException, InvalidStateException, InvalidSignException, DuplicateSymbolException,
			InvalidFileException, DuplicateFileException {

		IProgramState s = repo.getProgramState(0);

		if (!s.isDone()) {
			s = this.getNextState(s);
			repo.logProgramStates();

		}
	}

}
