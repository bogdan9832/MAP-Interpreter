package Model.Comands;

import java.io.IOException;

import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidStateException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Utils.Interfaces.PrintCallBack;

public abstract class Command {
	private String description;
	private String key;
	public PrintCallBack callback;
	public Command(String key,String description) {
		this.description = description;
		this.setKey(key);
	}
	
	public void setCallBack(PrintCallBack callback) {
		this.callback = callback;
	}
	public abstract void execute() throws InvalidStateException, InvalidSignException, DuplicateSymbolException, InvalidFileException, DuplicateFileException, IOException, InvalidSymbolException, InvalidAddressException, NullAdressException;

	public String getDescription() {
		return description;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
