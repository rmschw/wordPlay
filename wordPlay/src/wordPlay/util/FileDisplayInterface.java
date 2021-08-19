package wordPlay.util;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import wordPlay.CutomExceptions.invalidInputException;

/**
 * @author Ramesh Chowdarapally
 *
 */

public interface FileDisplayInterface {
	
	 //writeToFile();
	public void writeToFile() throws InvalidPathException, SecurityException, invalidInputException;
	
}
