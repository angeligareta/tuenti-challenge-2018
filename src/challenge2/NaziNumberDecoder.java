/**
 * Challenge 2 of the Tuenti Challenge 2018. This program is about decoding a
 * set of characters that are written in an arbitrary base, those characters
 * represent a number.
 * 
 * We know that all digits in the base are present only ONE TIME in the String.
 * For example, for a string of length 3, depending on the characters we could
 * the numbers 102, 120, 210 and 201 in an arbitrary base (we don't take leading
 * zeroes).
 * 
 * However, the aim of this challenge is not to know which number the string
 * represents, but to represent the difference between the minimum and maximum
 * values that the numbers in the string can be.
 * 
 * So, the first thing we have to do is to discover the arbitrary base. The
 * first approach was, depending on the first letter, the position in the
 * alphabet. However, this was not the solution. Nevertheless, if we try with
 * the length of the string as the base, voila!, we get the correct results.
 * 
 * For getting the minimum number we have to start in 1, followed by 0 and then
 * 2, 3... until reaching the base - 1. On the other hand, for the maximum
 * number we have to start by the base -1 and decrease.
 * 
 * The only difficulty was to show the big numbers, such as 26 powered 26.
 * However, we made use of BigInteger.
 * 
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 26-04-2018
 */
package challenge2;

import java.io.*;
import java.math.BigInteger;

public class NaziNumberDecoder {

	/** String that specifies the name of the input file. */
	private final static String INPUT_FILENAME = "src/challenge2/submitInput";
	/**
	 * String that specifies the name of the output file, with the required format.
	 */
	private final static String OUTPUT_FILENAME = "src/challenge2/submitOutput";

	/**
	 * Main method. It doesn't receive anything. It reads the input file, evaluate
	 * it and write the result in the output file.
	 */
	public static void main(String[] args) {
		try {
			BufferedReader codeReader = new BufferedReader(new FileReader(INPUT_FILENAME));
			BufferedWriter codeWriter = new BufferedWriter(new FileWriter(OUTPUT_FILENAME));

			int numberOfLines = Integer.parseInt(codeReader.readLine());

			for (int i = 1; i <= numberOfLines; ++i) {
				String encodedLine = codeReader.readLine().trim();
				int base = encodedLine.length(); // For commodity
				if (base < 2 || base > 26) {
					codeReader.close();
					codeWriter.close();
					throw new IOException("Unsupported length of word at case: " + i);
				}

				BigInteger bigBase = new BigInteger(String.valueOf(base));
				BigInteger difference = bigBase.pow(base - 1).add(bigBase.pow(base - 2));
				difference = difference.multiply(new BigInteger(String.valueOf(base - 2)));

				for (int j = base - 3; j >= 0; --j) {
					BigInteger localDifference = bigBase.pow(j);
					localDifference = localDifference.multiply(new BigInteger(String.valueOf(j - (base - j - 1))));
					difference = difference.add(localDifference);
				}

				codeWriter.write("Case #" + i + ": " + difference + System.lineSeparator());
			}

			codeReader.close();
			codeWriter.close();
		}
		catch (IOException e) {
			System.err.println("ERROR with IO:");
			e.printStackTrace();
		}
	}
}
