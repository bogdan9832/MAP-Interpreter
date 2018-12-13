package Controller;

import java.io.IOException;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import Controller.Interfaces.IController;
import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidStateException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Expresions.ConstantExpression;
import Model.Statements.CloseReadFileStatement;
import Model.Statements.HeapAllocationStatement;
import Model.Utils.ProgramState;
import Model.Utils.Interfaces.IProgramState;
import Model.Utils.Interfaces.PrintCallBack;
import Repository.Interfaces.IRepository;

public class Controller implements IController {

	IRepository repo;
	public PrintCallBack callback;
	private ExecutorService executor = Executors.newFixedThreadPool(2);
	public Controller(IRepository r) {
		repo = r;

	}

	public ProgramState getNextState(IProgramState s)
			throws InvalidStateException, InvalidSignException, DuplicateSymbolException, InvalidFileException,
			IOException, DuplicateFileException, InvalidSymbolException, InvalidAddressException, NullAdressException {
		ProgramState st = (ProgramState) s;

		st.executeNextStep();
		return st;
	}

	public void oneStepForAllProgramStates(List<ProgramState> prgList) throws InterruptedException {

		List<Callable<ProgramState>> callList = prgList.stream()
				.map((ProgramState p) -> (Callable<ProgramState>) (() -> p.oneStep())).collect(Collectors.toList());
		
		List<ProgramState> newPrgList = ((ExecutorService) executor).invokeAll(callList).stream().map(future -> {
			try {
				return future.get();
			} catch (Exception ex) {
				String msg = ex.getMessage();
				callback.printCallBack(msg.substring(msg.indexOf(':') + 1, msg.length() - 1));
			}
			return null;
		}).filter(p -> p != null).collect(Collectors.toList());

		prgList.addAll(newPrgList);

		prgList.forEach(prg -> {
			try {
				repo.logProgramStates(prg);
			} catch (IOException e) {
				callback.printCallBack(e.getMessage());
			}
		});

		repo.setProgramStateList(new ArrayList<>(prgList));
	}

	@Override
	public void allSteps() {

		List<ProgramState> prgList = removeCompletedProgramStates(repo.getProgramStateList());
		prgList.forEach(prg -> {
			try {
				repo.logProgramStates(prg);
			} catch (IOException e) {
				callback.printCallBack(e.getMessage());
			}
		});
		while (prgList.size() > 0) {
			try {

				oneStepForAllProgramStates(prgList);
			} catch (InterruptedException e) {
				callback.printCallBack("execution interupted");
			}

			prgList = removeCompletedProgramStates(repo.getProgramStateList());
		}
		((ExecutorService) executor).shutdownNow();
		/*
		 * for(IProgramState s : prgList) { for (int fd :
		 * s.getSymTable().getContent().values()) { if (s.getFileTable().contains(fd)) {
		 * try {
		 * 
		 * s.addStatement(new CloseReadFileStatement(new ConstantExpression(fd)));
		 * s.executeNextStep(); callback.printCallBack(s.toString()); } catch
		 * (InvalidStateException | InvalidSignException | DuplicateSymbolException |
		 * InvalidFileException | IOException | DuplicateFileException |
		 * InvalidSymbolException | InvalidAddressException | NullAdressException e) {
		 * 
		 * callback.printCallBack(e.getMessage()); } } } }
		 */

		repo.setProgramStateList(prgList);

	}

	@Override
	public void nextStep() throws IOException, InvalidStateException, InvalidSignException, DuplicateSymbolException,
			InvalidFileException, DuplicateFileException, InvalidSymbolException, InvalidAddressException,
			NullAdressException {

	}

	@Override
	public void conservativeGarbageCollector(List<IProgramState> prgStates) {
		HashSet<Integer> symbolTableValues = new HashSet<>();
		HashMap<Integer, Integer> heap = new HashMap<>();
		for (IProgramState s : prgStates)
			symbolTableValues.addAll(s.getSymTable().getContent().values());

		for (IProgramState s : prgStates) {
			for (Integer val : s.getHeap().getContent().values())
				if (!symbolTableValues.contains(val)) {
					heap.remove(val);
				}
		}

		if (prgStates.size() > 0) {
			prgStates.get(0).getHeap().setContent(heap);
		}

	}

	@Override
	public List<ProgramState> removeCompletedProgramStates(List<ProgramState> prgStates) {

		return prgStates.stream().filter(i -> i.isNotDone()).collect(Collectors.toList());
	}

}
