MusicalNoteTest.java                                                                                0000664 0001750 0001750 00000013033 13270664146 015541  0                                                                                                    ustar   angeliton                       angeliton                                                                                                                                                                                                              /**
 * Class to test the static methods of Musical Note Enumerator.
 *
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 27-04-2018
 */
package challenge3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Class to test the static methods of Musical Note Enumerator.
 */
public class MusicalNoteTest {

	/**
	 * Test method for {@link challenge3.MusicalNote#getNoteWithPitch(double)}.
	 */
	@Test
	public void testGetNoteWithPitchNormal() {
		assertEquals(MusicalNote.A, MusicalNote.getNoteWithPitch(0));
		assertEquals(MusicalNote.AS, MusicalNote.getNoteWithPitch(0.5));
		assertEquals(MusicalNote.B, MusicalNote.getNoteWithPitch(1));
		assertEquals(MusicalNote.C, MusicalNote.getNoteWithPitch(1.5));
		assertEquals(MusicalNote.CS, MusicalNote.getNoteWithPitch(2));
		assertEquals(MusicalNote.D, MusicalNote.getNoteWithPitch(2.5));
		assertEquals(MusicalNote.DS, MusicalNote.getNoteWithPitch(3));
		assertEquals(MusicalNote.E, MusicalNote.getNoteWithPitch(3.5));
		assertEquals(MusicalNote.F, MusicalNote.getNoteWithPitch(4));
		assertEquals(MusicalNote.FS, MusicalNote.getNoteWithPitch(4.5));
		assertEquals(MusicalNote.G, MusicalNote.getNoteWithPitch(5));
		assertEquals(MusicalNote.GS, MusicalNote.getNoteWithPitch(5.5));
	}

	/**
	 * Test method for {@link challenge3.MusicalNote#getNoteWithPitch(double)}.
	 */
	@Test
	public void testGetNoteWithPitchNegative() {
		assertEquals(MusicalNote.A, MusicalNote.getNoteWithPitch(-0));
		assertEquals(MusicalNote.GS, MusicalNote.getNoteWithPitch(-0.5));
		assertEquals(MusicalNote.G, MusicalNote.getNoteWithPitch(-1));
		assertEquals(MusicalNote.FS, MusicalNote.getNoteWithPitch(-1.5));
		assertEquals(MusicalNote.F, MusicalNote.getNoteWithPitch(-2));
		assertEquals(MusicalNote.E, MusicalNote.getNoteWithPitch(-2.5));
		assertEquals(MusicalNote.DS, MusicalNote.getNoteWithPitch(-3));
		assertEquals(MusicalNote.D, MusicalNote.getNoteWithPitch(-3.5));
		assertEquals(MusicalNote.CS, MusicalNote.getNoteWithPitch(-4));
		assertEquals(MusicalNote.C, MusicalNote.getNoteWithPitch(-4.5));
		assertEquals(MusicalNote.B, MusicalNote.getNoteWithPitch(-5));
		assertEquals(MusicalNote.AS, MusicalNote.getNoteWithPitch(-5.5));
		assertEquals(MusicalNote.A, MusicalNote.getNoteWithPitch(-6));
	}

	/**
	 * Test method for {@link challenge3.MusicalNote#getNoteWithPitch(double)}.
	 */
	@Test
	public void testGetNoteWithPitchHigher() {
		assertEquals(MusicalNote.A, MusicalNote.getNoteWithPitch(6));
		assertEquals(MusicalNote.AS, MusicalNote.getNoteWithPitch(6.5));
		assertEquals(MusicalNote.B, MusicalNote.getNoteWithPitch(7));
		assertEquals(MusicalNote.C, MusicalNote.getNoteWithPitch(7.5));
		assertEquals(MusicalNote.CS, MusicalNote.getNoteWithPitch(8));
		assertEquals(MusicalNote.D, MusicalNote.getNoteWithPitch(8.5));
		assertEquals(MusicalNote.DS, MusicalNote.getNoteWithPitch(9));
		assertEquals(MusicalNote.E, MusicalNote.getNoteWithPitch(9.5));
		assertEquals(MusicalNote.F, MusicalNote.getNoteWithPitch(10));
		assertEquals(MusicalNote.FS, MusicalNote.getNoteWithPitch(10.5));
		assertEquals(MusicalNote.G, MusicalNote.getNoteWithPitch(11));
		assertEquals(MusicalNote.GS, MusicalNote.getNoteWithPitch(11.5));
		assertEquals(MusicalNote.A, MusicalNote.getNoteWithPitch(12));
	}

	/**
	 * Test method for {@link challenge3.MusicalNote#getNoteWithPitch(double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNoteWithPitchErrorOver() {
		assertEquals(MusicalNote.A, MusicalNote.getNoteWithPitch(12.5));
	}

	/**
	 * Test method for {@link challenge3.MusicalNote#getNoteWithPitch(double)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNoteWithPitchErrorUnder() {
		assertEquals(MusicalNote.A, MusicalNote.getNoteWithPitch(-6.5));
	}

	/**
	 * Test method for {@link challenge3.MusicalNote#normalizeNote(String)}.
	 */
	@Test
	public void testNormalizeNoteNormal() {
		assertEquals(MusicalNote.A, MusicalNote.normalizeNote("A"));
		assertEquals(MusicalNote.AS, MusicalNote.normalizeNote("A#"));
		assertEquals(MusicalNote.B, MusicalNote.normalizeNote("B"));
		assertEquals(MusicalNote.C, MusicalNote.normalizeNote("C"));
		assertEquals(MusicalNote.CS, MusicalNote.normalizeNote("C#"));
		assertEquals(MusicalNote.D, MusicalNote.normalizeNote("D"));
		assertEquals(MusicalNote.DS, MusicalNote.normalizeNote("D#"));
		assertEquals(MusicalNote.E, MusicalNote.normalizeNote("E"));
		assertEquals(MusicalNote.F, MusicalNote.normalizeNote("F"));
		assertEquals(MusicalNote.FS, MusicalNote.normalizeNote("F#"));
		assertEquals(MusicalNote.G, MusicalNote.normalizeNote("G"));
		assertEquals(MusicalNote.GS, MusicalNote.normalizeNote("G#"));
	}

	/**
	 * Test method for {@link challenge3.MusicalNote#normalizeNote(String)}.
	 */
	@Test
	public void testNormalizeNoteAnormal() {
		assertEquals(MusicalNote.GS, MusicalNote.normalizeNote("Ab"));
		assertEquals(MusicalNote.AS, MusicalNote.normalizeNote("Bb"));
		assertEquals(MusicalNote.B, MusicalNote.normalizeNote("Cb"));
		assertEquals(MusicalNote.CS, MusicalNote.normalizeNote("Db"));
		assertEquals(MusicalNote.DS, MusicalNote.normalizeNote("Eb"));
		assertEquals(MusicalNote.E, MusicalNote.normalizeNote("Fb"));
		assertEquals(MusicalNote.FS, MusicalNote.normalizeNote("Gb"));

		assertEquals(MusicalNote.C, MusicalNote.normalizeNote("B#"));
		assertEquals(MusicalNote.F, MusicalNote.normalizeNote("E#"));
	}

	/**
	 * Test method for {@link challenge3.MusicalNote#normalizeNote(String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNormalizeNoteError() {
		assertEquals(MusicalNote.GS, MusicalNote.normalizeNote("A##"));
	}
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     MusicalScaleFinder.java                                                                             0000664 0001750 0001750 00000007602 13270667006 016156  0                                                                                                    ustar   angeliton                       angeliton                                                                                                                                                                                                              /**
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
                                                                                                                              MusicalScale.java                                                                                   0000664 0001750 0001750 00000005531 13270665225 015026  0                                                                                                    ustar   angeliton                       angeliton                                                                                                                                                                                                              /**
 * Enum that represent scales and it's increment between the notes of the scale.
 *
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 27-04-2018
 */
package challenge3;

import java.util.ArrayList;
import java.util.TreeSet;

public enum MusicalScale {
	M(new double[] { 0, 1, 2, 2.5, 3.5, 4.5, 5.5 }), // Major scale
	m(new double[] { 0, 1, 1.5, 2.5, 3.5, 4, 5 }); // Minor scale

	/** Pattern of the notes that form the scale. */
	double[] scalePattern;

	private MusicalScale(double[] scalePattern) {
		this.scalePattern = scalePattern;
	}

	public double getPitchInPosition(int position) {
		if (position < 0 || position > 6) {
			throw new IllegalArgumentException("Bad position: " + position);
		}

		return this.scalePattern[position];
	}

	public static TreeSet<String> getPossibleScalesForNote(MusicalNote note) {
		TreeSet<String> possibleScales = new TreeSet<String>();

		for (MusicalScale scale : MusicalScale.values()) {
			for (int i = 0; i < 7; ++i) {
				double pitchInPosition = note.getPitch() - scale.getPitchInPosition(i);
				String scaleString = scale.toString() + MusicalNote.getNoteWithPitch(pitchInPosition);
				possibleScales.add(scaleString);
			}
		}

		return possibleScales;
	}

	/**
	 * Return the possible scales names in an ordered string array.
	 * 
	 * @return
	 */
	public static TreeSet<String> getPossibleScales() {
		TreeSet<String> possibleScales = new TreeSet<String>();

		for (MusicalNote note : MusicalNote.values()) {
			possibleScales.add("M" + note.toString());
			possibleScales.add("m" + note.toString());
		}

		return possibleScales;
	}

	/**
	 * Return the possible scales in an array of string array, containing all the
	 * notes that form the scale.
	 * 
	 * @return
	 */
	public static ArrayList<ArrayList<String>> getPossibleScalesInArray() {
		ArrayList<ArrayList<String>> possibleScales = new ArrayList<ArrayList<String>>();

		for (MusicalNote note : MusicalNote.values()) {
			ArrayList<String> majorScale = new ArrayList<String>();
			double[] majorScaleIncrements = MusicalScale.M.getScalePattern();

			for (int i = 0; i < 7; ++i) {
				double pitchInPosition = note.getPitch() + majorScaleIncrements[i];
				String scaleString = "M" + MusicalNote.getNoteWithPitch(pitchInPosition);
				majorScale.add(scaleString);
			}

			ArrayList<String> minorscale = new ArrayList<String>();
			double[] minorScaleIncrements = MusicalScale.m.getScalePattern();

			for (int i = 0; i < 7; ++i) {
				double pitchInPosition = note.getPitch() + minorScaleIncrements[i];
				String scaleString = "m" + MusicalNote.getNoteWithPitch(pitchInPosition);
				minorscale.add(scaleString);
			}

			possibleScales.add(majorScale);
			possibleScales.add(minorscale);
		}

		return possibleScales;
	}

	/**
	 * Getter of scale pattern.
	 * 
	 * @return the scalePattern
	 */
	public double[] getScalePattern() {
		return scalePattern;
	}
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       