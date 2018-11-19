package View;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Model.Comands.Command;
import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.DuplicateSymbolException;
import Model.Exceptions.InvalidAddressException;
import Model.Exceptions.InvalidFileException;
import Model.Exceptions.InvalidSignException;
import Model.Exceptions.InvalidStateException;
import Model.Exceptions.InvalidSymbolException;
import Model.Exceptions.NullAdressException;
import Model.Utils.Interfaces.PrintCallBack;

public class TextMenu {
	private Map<String, Command> commands;

	public TextMenu() {
		commands = new HashMap<>();
	}

	public void addCommand(Command c) {
		commands.put(c.getKey(), c);
	}

	private void printMenu() {
		for (Command com : commands.values()) {
			String line = String.format("%4s : %s", com.getKey(), com.getDescription());
			System.out.println(line);
		}
	}

	PrintCallBack printCallBack = new PrintCallBack() {

		@Override
		public void printCallBack(String print) {
			System.out.println(print);
		}

	};

	@SuppressWarnings("resource")
	public void show() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			printMenu();
			System.out.printf("Input the option: ");
			String key = scanner.nextLine();
			Command com = commands.get(key);

			if (com == null) {
				System.out.println("Invalid Option");
				continue;
			}
			com.setCallBack(this.printCallBack);
			try {
				com.execute();
			} catch (InvalidStateException | InvalidSignException | DuplicateSymbolException | InvalidFileException
					| DuplicateFileException | IOException | InvalidSymbolException | InvalidAddressException
					| NullAdressException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
