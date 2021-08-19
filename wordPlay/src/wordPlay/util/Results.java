package wordPlay.util;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wordPlay.CutomExceptions.EmptyFileException;
import wordPlay.CutomExceptions.invalidInputException;
import wordPlay.handler.WordRotator;
import wordPlay.metrics.MetricsCalculator;

/**
 * Results is a utility to be used to show the results of the input file.
 * 
 * @author Ramesh Chowdarapally
 *
 *
 */

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	int SentenceCount;
	int wordListCout;
	int charCount;
	double AVG_NUM_WORDS_PER_SENTENCE;
	String AVG_WORD_LENGTH;
	List alWords;

	public Results(String inputFilePath, String outputFilepath, String metricsFilePath) {
		this.inputFilePath = inputFilePath;
		this.outputFilepath = outputFilepath;
		this.metricsFilePath = metricsFilePath;
	}

	public int getSentenceCount() {
		return SentenceCount;
	}

	public void setSentenceCount(int sentenceCount) {
		SentenceCount = sentenceCount;
	}

	public int getWordListCout() {
		return wordListCout;
	}

	public void setWordListCout(int wordListCout) {
		this.wordListCout = wordListCout;
	}

	public int getCharCount() {
		return charCount;
	}

	public void setCharCount(int charCount) {
		this.charCount = charCount;
	}

	public String getOutputFilepath() {
		return outputFilepath;
	}

	public void setOutputFilepath(String outputFilepath) {
		this.outputFilepath = outputFilepath;
	}

	public String getMetricsFilePath() {
		return metricsFilePath;
	}

	public void setMetricsFilePath(String metricsFilePath) {
		this.metricsFilePath = metricsFilePath;
	}

	public String getInputFilePath() {
		return inputFilePath;
	}

	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	String inputFilePath;
	String outputFilepath;
	String metricsFilePath;
	int index;
	String word;

	/**
	 * wordRead method retrieves Strings from poll method and passes to rotate
	 * method
	 * 
	 * @return List of rotated words in a sentence
	 * @exception InvalidPathException  On invalid path string.
	 * @exception SecurityException     On not having necessary read permissions to
	 *                                  the input file.
	 * @exception FileNotFoundException On input file not found.
	 * @exception IOException           On any I/O errors while reading lines from
	 *                                  input file.
	 * @throws invalidInputException on invalid input
	 * @throws EmptyFileException    on empty file
	 */

	public List wordRead() throws InvalidPathException, SecurityException, FileNotFoundException, IOException,
			invalidInputException, EmptyFileException {
		FileProcessor fr = new FileProcessor(inputFilePath);
		String word;

		alWords = new ArrayList();

		WordRotator wr = new WordRotator();
		MetricsCalculator mcl = new MetricsCalculator();
		if ((word = fr.poll()) == null) {
			throw new EmptyFileException("File is empty");
		}
		while ((word = fr.poll()) != null) {
			wordListCout++;
			index++;

			/**
			 * Words contain characters other than [a-zA-Z0-9]: 2 points
			 */

			Pattern pattern = Pattern.compile("^^[A-Za-z0-9\\.]*$");
			Matcher matcher = pattern.matcher(word);

			boolean found = false;

			while (matcher.find()) {
				found = true;
			}
			if (!found) {
				throw new invalidInputException("Invalid input entered.");
			}

			alWords.add((wr.rotateString(word, index)));

			charCount += word.length();
			if (word.contains(".")) {
				SentenceCount++;
				charCount--;
				index = 0;

			}

		}
		AVG_NUM_WORDS_PER_SENTENCE = mcl.calcAvgWordsPSent(wordListCout, SentenceCount);

		AVG_WORD_LENGTH = mcl.calcAvgWordLength(charCount, wordListCout);

		return alWords;

	}

	/**
	 * writeToFile method used to write the rotated Strings to the output file and
	 * writes the metrics to metrics file
	 * 
	 * @throws invalidInputException
	 * @throws SecurityException
	 * @throws InvalidPathException
	 */

	public void writeToFile() throws InvalidPathException, SecurityException, invalidInputException {
		// write code to write to files
		File file = new File(outputFilepath);

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
			// List alWords = wordRead();

			for (int i = 0; i < alWords.size(); i++) {
				String strWord = (String) alWords.get(i);
				bw.write(alWords.get(i) + " ");
				if (strWord.contains(".")) {
					bw.newLine();
				}
			}

			bw.close();

			File fileMetrics = new File(metricsFilePath);
			BufferedWriter bwMetrics = new BufferedWriter(new FileWriter(fileMetrics, false));
			bwMetrics.write("Average length of a word: " + AVG_WORD_LENGTH);
			bwMetrics.newLine();
			bwMetrics.write("Average no. of words per sentence: " + AVG_NUM_WORDS_PER_SENTENCE);
			bwMetrics.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * writeToStdout used to display the rotated Strings and displays metrics to
	 * Console
	 * 
	 * @throws invalidInputException
	 */
	public void writeToStdout() throws invalidInputException {
		try {
			// List alWords = wordRead();

			for (int i = 0; i < alWords.size(); i++) {
				String strWord = (String) alWords.get(i);
				System.out.print(alWords.get(i) + " ");
				if (strWord.contains(".")) {
					System.out.println("");
				}
			}

			System.out.println("\nAverage no. of words per sentence: " + AVG_NUM_WORDS_PER_SENTENCE);
			System.out.println("Average length of a word: " + AVG_WORD_LENGTH);

		} catch (InvalidPathException e) {
			// TODO 
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 
			e.printStackTrace();
		}

	}

	/**
	 * toString() will be used to supply a string representation of the
	 * instance/object.
	 * 
	 */

	@Override
	public String toString() {
		return "Results [SentenceCount=" + SentenceCount + ", wordListCout=" + wordListCout + ", charCount=" + charCount
				+ ", AVG_NUM_WORDS_PER_SENTENCE=" + AVG_NUM_WORDS_PER_SENTENCE + ", AVG_WORD_LENGTH=" + AVG_WORD_LENGTH
				+ ", alWords=" + alWords + ", inputFilePath=" + inputFilePath + ", outputFilepath=" + outputFilepath
				+ ", metricsFilePath=" + metricsFilePath + ", index=" + index + ", word=" + word + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
