package Model.Utils.Interfaces;

import java.io.BufferedReader;

import Model.Exceptions.DuplicateFileException;
import Model.Exceptions.InvalidFileException;

public interface IFileTable {
	BufferedReader getFile(int i) throws InvalidFileException;
	void addFile(int i, BufferedReader br) throws DuplicateFileException;
	void removeFile(int i) throws InvalidFileException;
	String toString();
	int count();
	boolean contains(int key);
	
}
