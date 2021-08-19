package wordPlay.handler;

/**
 * Rotates a string in right direction by index positions.
 * 
 * @param word  String which we are going to rotate
 * @param index is the number of positions to rotate
 * @return returns String(word) rotated by index positions
 */

public class WordRotator {

	public String rotateString(String word, int index) {
		// System.out.println(word+", "+index);
		String ans = word;
		boolean bString = false;
		int wordlength = word.length();
		if (word.contains(".")) {
			bString = true;
			ans = ans.substring(0, word.length() - 1);

		}
		while (index != 0) {
			ans = ans.substring(ans.length() - 1) + ans.substring(0, ans.length() - 1);
			index--;
		}
		if (bString) {
			ans = ans + ".";
		}
		return ans;

	}
}
