package Controller.Interfaces;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidStateException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Utils.Interfaces.IProgramState;

public interface IController {
	public void allSteps()
			;

	public void nextStep()
			throws InvalidStateException, InvalidSignException, DuplicateSymbolException, InvalidFileException,
			DuplicateFileException, IOException, InvalidSymbolException, InvalidAddressException, NullAdressException;

	public IProgramState getNextState(IProgramState s)
			throws InvalidStateException, InvalidSignException, DuplicateSymbolException, InvalidFileException,
			IOException, DuplicateFileException, InvalidSymbolException, InvalidAddressException, NullAdressException;

	public HashMap<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
			HashMap<Integer, Integer> heap);
}
