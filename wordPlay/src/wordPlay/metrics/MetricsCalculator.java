package wordPlay.metrics;

import java.text.DecimalFormat;

public class MetricsCalculator {

	/**
	 * calcAvgWordsPSent calculates the Average Words Per Sentence
	 * 
	 * @param wordListCout  is the total number of words in the file
	 * @param SentenceCount is the total number of sentences in the file
	 * @return Average words in the file
	 */
	public double calcAvgWordsPSent(int wordListCout, int SentenceCount) {

		double AVGWORDS = wordListCout / SentenceCount;
		return AVGWORDS;
	}

	/**
	 * calcAvgWordLength method used to calculate the Average Word Length for the
	 * given file
	 * 
	 * @param charCount    is the total characters in the file
	 * @param wordListCout is the total number of words in the file
	 * @return Average word length formatted to two decimals
	 */
	public static String calcAvgWordLength(int charCount, int wordListCout) {

		double AVGWORDS = (double) charCount / wordListCout;
		DecimalFormat f = new DecimalFormat("##.00");

		String strWordsAvg = f.format(AVGWORDS);
		return strWordsAvg;
	}

}
