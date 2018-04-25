/**
 * First Challenge of Tuenti Contest 2018. It consist on counting the holes of a
 * waffle by receiving the number of vertical and horizontal lines of it.
 * 
 * For solving it first we have to get the matrix dimensions. For doing so we
 * only have to subtract one from the line Number. After having the matrix
 * dimension the only step left is to multiply those numbers.
 *
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 25-04-2018
 */
package challenge1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * First Challenge of Tuenti Contest 2018. It consist on counting the holes of a
 * waffle by receiving the number of vertical and horizontal lines of it.
 * 
 * For solving it first we have to get the matrix dimensions. For doing so we
 * only have to subtract one from the line Number. After having the matrix
 * dimension the only step left is to multiply those numbers.
 */
public class WaffleHoleCounter {

	/** String that specifies the name of the input file. */
	private final static String INPUT_FILENAME = "src/challenge1/input.txt";
	/**
	 * String that specififes the name of the output file, with the required format.
	 */
	private final static String OUTPUT_FILENAME = "src/challenge1/output.txt";

	/**
	 * Main method. It doesn't receive anything. It reads the input file, evaluate
	 * it and write the result in the output file.
	 */
	public static void main(String[] args) {
		try {
			BufferedReader waffleReader = new BufferedReader(new FileReader(INPUT_FILENAME));
			BufferedWriter waffleHolesWriter = new BufferedWriter(new FileWriter(OUTPUT_FILENAME));

			int numberOfWaffles = Integer.parseInt(waffleReader.readLine());
			if (numberOfWaffles < 1 || numberOfWaffles > 100) {
				waffleReader.close();
				waffleHolesWriter.close();
				throw new IOException("Number of files must be an integer between 1 and 100.");
			}

			for (int i = 1; i <= numberOfWaffles; ++i) {
				String[] lineNumber = waffleReader.readLine().trim().split("\\s");
				int verticalLineNumber = Integer.parseInt(lineNumber[0]);
				int horizontalLineNumber = Integer.parseInt(lineNumber[1]);
				if (lineNumber.length > 2 || verticalLineNumber < 2 || verticalLineNumber > 10000 || horizontalLineNumber < 2
						|| horizontalLineNumber > 10000) {
					waffleReader.close();
					waffleHolesWriter.close();
					throw new IOException("Bad format in waffle input.");
				}

				int holeNumber = (verticalLineNumber - 1) * (horizontalLineNumber - 1);
				waffleHolesWriter.write("Case #" + i + ": " + holeNumber + System.lineSeparator());
			}

			waffleReader.close();
			waffleHolesWriter.close();
		}
		catch (IOException e) {
			System.err.println("ERROR with IO:");
			e.printStackTrace();
		}
	}
}
