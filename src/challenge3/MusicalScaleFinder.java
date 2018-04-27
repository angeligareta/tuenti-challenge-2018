/**
 * The aim of this program is to calculate the possible scales in where a
 * musical piece can be played. However, we only use the minor and major scales.
 * 
 * Besides, the scales are ordered according to the scale where they belong and
 * if they represent a scale starting with a sharp note.
 * 
 * First, we have to consider the notes not by it's name, but to assign them a
 * double value that represent their position in the scale. If they have a sharp
 * modification, they will have 0.5 more of value, and, if they have a flat
 * modification, they will have 0.5 less of value. For this aim we created the
 * Musical Note enumerator.
 * 
 * For example, the A note, has a value of 0, but the A# has a value of 0.5 and
 * the Ab has a value of -0.5, but we consider the notes as a cycle, so it will
 * represent 6 - 0.5 = 5.5, the G# note. That's the reason we don't need to
 * represent the flat notes.
 * 
 * Second, according to the double values of the notes, we can represent a scale
 * as a double array, by setting the difference between the positions of the
 * pattern that each scale follows. For this aim we created the Musical Scale
 * enumerator.
 * 
 * For example, the Major scale has a pattern of "WWHWWWH", so from the first to
 * the second position there is a difference of 1, from the 3 to the 4 of 0.5
 * and so on. The double value array will be { 0, 1, 2, 2.5, 3.5, 4.5, 5.5 }).
 * 
 * Finally, for each note of the musical piece we have to calculate the possible
 * scales where it can be played. Finally, we intersect those result in a smart
 * way.
 * 
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 27-04-2018
 */
package challenge3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class MusicalScaleFinder {

	/** String that specifies the name of the input file. */
	private final static String INPUT_FILENAME = "src/challenge3/submitInput";
	/**
	 * String that specifies the name of the output file, with the required format.
	 */
	private final static String OUTPUT_FILENAME = "src/challenge3/submitOutput";

	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader codeReader = new BufferedReader(new FileReader(INPUT_FILENAME));
			BufferedWriter codeWriter = new BufferedWriter(new FileWriter(OUTPUT_FILENAME));

			int numberOfLines = Integer.parseInt(codeReader.readLine());

			for (int i = 1; i <= numberOfLines; ++i) {
				Set<String> possibleScales = MusicalScale.getPossibleScales();
				int numberOfNotes = Integer.parseInt(codeReader.readLine());

				if (numberOfNotes != 0) {
					String[] musicalPiece = codeReader.readLine().trim().split("\\s");

					for (int j = 0; j < musicalPiece.length; ++j) {
						MusicalNote currentNote = MusicalNote.normalizeNote(musicalPiece[j]);
						Set<String> currentNoteScales = MusicalScale.getPossibleScalesForNote(currentNote);
						possibleScales.retainAll(currentNoteScales);
					}
				}

				String possibleScalesString = possibleScales.isEmpty() ? "None" : String.join(" ", possibleScales);
				codeWriter.write("Case #" + i + ": " + possibleScalesString + System.lineSeparator());
			}

			codeReader.close();
			codeWriter.close();
		}
		catch (IOException e) {
			System.err.println("ERROR with IO:");
			e.printStackTrace();
		}
	}

	/**
	 * Method that show every possible scale in the program and the notes of that
	 * scale, without an order.
	 */
	public static void showPossibleScales() {
		ArrayList<ArrayList<String>> possibleScales = MusicalScale.getPossibleScalesInArray();

		System.out.println("\n** Possible Scales **");
		for (ArrayList<String> scale : possibleScales) {
			for (String currentScale : scale) {
				System.out.print(currentScale + " ");
			}
			System.out.print(System.lineSeparator());
		}
	}
}
