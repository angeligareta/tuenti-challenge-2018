/**
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
