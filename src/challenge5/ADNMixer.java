/**
 * ADN MIXER
 * 
 * This program has the aim of finding a sequence of ADN that was badly split.
 * For example, if we have an initial ADN sequence of 'aaccGgTTC', there are two
 * random splits that we receive by parameter, such as 'aacc' 'GgTTC' or 'aaccG'
 * 'gTTC'. However, to make it interesting, they come mixed with parts from
 * another experiment such as 'aaaa' 'cccc' and 'gttc'.
 * 
 * In order to find the original sequence, first of all we make an array with the 
 * possible solutions, those are the ones that start with the same set of characters. 
 * After that, we try calling the addSubstring method with each pair of
 * solutions until we find two solution with the same string.
 * 
 * The addSubstring method receives a pair of solutions, look for the difference 
 * between the pair ADN string and look for a non-visited ADN part that contains the
 * difference or a part of it. Once the part is found, we call this method
 * recursivelY in case that string is not the one that is going to help finding
 * the final substring.
 * 
 * Finally, to receive the test we have to connect to an ip and solve the tests
 * in a fixed time. Let's do it!
 * 
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 28-04-2018
*/

package challenge5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Class that contains the main method that reads from the socket and solve the
 * solutions.
 */
public class ADNMixer {
	/** Parameters for socket. */
	private static final int PORT = 3241;
	private static final String IP = "52.49.91.111";
	private static final String MODE = "SUBMIT";

	/** Key output file. */
	private static final String OUTPUT_FILE = "src/challenge5/key";

	/**
	 * Main method. Comunicate with the socket and call getSolution method.
	 */
	public static void main(String[] args) throws InterruptedException {
		try {
			Socket socket = new Socket(IP, PORT);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			System.out.println("Connection stablished with: " + socket.getInetAddress());
			System.out.println("Starting Challenge 5!");

			// Getting the number of Cases
			String firstLine = reader.readLine();
			int numberOfCases = Integer.parseInt(firstLine.split("\\s")[1]);

			// Choosing mode
			System.out.println(reader.readLine());
			System.out.println("Setting MODE: " + MODE);
			;
			writer.println(MODE);
			writer.flush();

			// Starting tests
			String key = "";
			System.out.println(reader.readLine());
			for (int i = 0; i < numberOfCases; ++i) {
				String ADN = reader.readLine();
				System.out.println(ADN);
				String solution = getSolutionOf(ADN);
				System.out.println(solution);

				writer.println(solution);
				writer.flush();
				key = reader.readLine();
				System.out.println(key);
			}

			reader.close();
			writer.close();
			socket.close();

			BufferedWriter keyWriter = new BufferedWriter(new FileWriter(OUTPUT_FILE));
			keyWriter.write(key.split("\"")[1]); // To write the key
			keyWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that receives an ADN String Array and found the parts of the original
	 * ADN String. First of all we make an array with the possible solutions, those
	 * are the ones that start with the same set of characters.
	 * 
	 * After that, we try calling the addSubstring method with each pair of
	 * solutions until we find two solution with the same string.
	 */
	public static String getSolutionOf(String ADN) {
		final ArrayList<String> aDNParts = new ArrayList<String>(Arrays.asList(ADN.split("\\s")));

		ArrayList<Solution> solutionArray = new ArrayList<Solution>();
		for (int i = 0; i < aDNParts.size(); ++i) {
			for (int j = 0; j < aDNParts.size(); ++j) {
				if (i == j) {
					continue;
				}
				else if (aDNParts.get(i).startsWith(aDNParts.get(j))) {
					solutionArray.add(new Solution(aDNParts.get(i), new ArrayList<Integer>(Arrays.asList(i))));
					solutionArray.add(new Solution(aDNParts.get(j), new ArrayList<Integer>(Arrays.asList(j))));
				}
			}
		}

		boolean finished = false;
		while (!finished) {
			// Try pair of solutions
			ArrayList<Solution> currentSolution = new ArrayList<Solution>(solutionArray.subList(0, 2));
			finished = addSubString(currentSolution, aDNParts);

			if (finished) {
				solutionArray.clear();
				solutionArray.addAll(currentSolution);
			}
			else if (solutionArray.size() > 1) { // If there are more initial solutions, try with them
				solutionArray.remove(0);
				solutionArray.remove(0);
			}
			else {
				throw new IllegalArgumentException("There is no solution!");
			}
		}

		TreeSet<Integer> solutionIndexesArray = new TreeSet<Integer>();
		solutionIndexesArray.addAll(solutionArray.get(0).getIndexUsedForConcat());
		solutionIndexesArray.addAll(solutionArray.get(1).getIndexUsedForConcat());

		String result = "";
		for (Integer index : solutionIndexesArray) {
			result += (index + 1) + ",";
		}
		return result.substring(0, result.length() - 1);
	}

	/**
	 * Method that receives a pair of solutions, look for the difference between the
	 * pair ADN string and look for a non-visited ADN part that contains the
	 * difference or a part of it. Once the part is found, we call this method
	 * recursively in case that string is not the one that is going to help finding
	 * the final substring.
	 */
	private static boolean addSubString(ArrayList<Solution> currentSolutionArray, ArrayList<String> aDNParts) {
		if (currentSolutionArray.get(0).equals(currentSolutionArray.get(1))) {
			return true;
		}

		// Get minimum length solution index
		String firstString = currentSolutionArray.get(0).getConcatenedString();
		String secondString = currentSolutionArray.get(1).getConcatenedString();
		int minimumSolutionIndex = (firstString.length() < secondString.length()) ? 0 : 1;

		String differenceString = (minimumSolutionIndex == 0) ? secondString.substring(firstString.length())
				: firstString.substring(secondString.length());

		// Look for difference string
		for (int i = 0; i < aDNParts.size(); ++i) {
			ArrayList<Integer> visitedIndexes = currentSolutionArray.get(0).getIndexUsedForConcat();
			visitedIndexes.addAll(currentSolutionArray.get(1).getIndexUsedForConcat());

			// If there is a string that starts with the difference or contains a part of
			// it, try looking for the solution recursively with that string.
			if (!visitedIndexes.contains(i)
					&& (aDNParts.get(i).startsWith(differenceString) || differenceString.startsWith(aDNParts.get(i)))) {
				ArrayList<Solution> newSolutionArray = new ArrayList<Solution>(currentSolutionArray);

				// Generate new solution attributes.
				ArrayList<Integer> newSolutionIndexesArray = currentSolutionArray.get(minimumSolutionIndex)
						.getIndexUsedForConcat();
				String newConcatenedString = currentSolutionArray.get(minimumSolutionIndex).getConcatenedString();
				newSolutionIndexesArray.add(i);
				newConcatenedString = newConcatenedString.concat(aDNParts.get(i));

				// Swap the current element with the new solution
				newSolutionArray.remove(minimumSolutionIndex);
				newSolutionArray.add(new Solution(newConcatenedString, newSolutionIndexesArray));

				// Recursive solve it
				if (addSubString(newSolutionArray, aDNParts)) {
					currentSolutionArray.clear();
					currentSolutionArray.addAll(newSolutionArray);
					return true;
				}
			}
		}

		// No substring to continue found
		return false;
	}

	/**
	 * Class that stores a string that was concatenated and the indexes of the ADN
	 * parts that were used to concatenate the string.
	 */
	private static class Solution {
		private String concatenedString; // ADN Parts concatenated.
		private ArrayList<Integer> indexUsedForConcat; // Indexes that were used to concatenate the string.

		/**
		 * Default constructor.
		 */
		public Solution(String concatenedString, ArrayList<Integer> indexUsedForConcat) {
			this.concatenedString = concatenedString;
			this.indexUsedForConcat = indexUsedForConcat;
		}

		/*
		 * Overloaded equals method to compare two strings of different solutions.
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object anObject) {
			Solution anSolution = (Solution) anObject;
			return this.concatenedString.equals(anSolution.getConcatenedString());
		}

		/*
		 * Overloaded String only to visual aims.
		 * 
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			String resultingString = "String: " + concatenedString + " Indexes Used: {";
			for (Integer index : this.getIndexUsedForConcat()) {
				resultingString += (index + 1) + ", ";
			}
			return resultingString + " }";
		}

		/**
		 * @return the concatenedString
		 */
		public String getConcatenedString() {
			return concatenedString;
		}

		/**
		 * @return the indexUsedForConcat
		 */
		public ArrayList<Integer> getIndexUsedForConcat() {
			return new ArrayList<Integer>(indexUsedForConcat);
		}
	}
}
