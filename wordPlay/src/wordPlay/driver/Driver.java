package wordPlay.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.text.DecimalFormat;

import wordPlay.CutomExceptions.EmptyFileException;
import wordPlay.CutomExceptions.invalidInputException;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

/**
 * Driver has main method
 * 
 * @author Ramesh Chowdarapally
 *
 */
public class Driver {

	/**
	 * Retrieving inputs from input file and writes output to output and metrics
	 * file
	 * 
	 * @param args to retrieve input, output and metrics file path
	 * @throws InvalidPathException
	 * @throws SecurityException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws invalidInputException
	 * @throws EmptyFileException
	 */
	public static void main(String[] args) throws InvalidPathException, SecurityException, FileNotFoundException,
			IOException, invalidInputException, EmptyFileException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case
		 * the argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}"))
				|| (args[2].equals("${metrics}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}

		System.out.println("Hello World! Lets get started with the assignment");
		// String inputFilePath = "input.txt";
		String inputFilePath = args[0];
		String outputFilepath = args[1];
		String metricsFilePath = args[2];
		Results results = new Results(inputFilePath, outputFilepath, metricsFilePath);
		results.wordRead();
		results.writeToStdout();
		results.writeToFile();

	}

}
