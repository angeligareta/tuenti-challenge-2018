/**
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
