/**
 * ADN MIXER
 * 
 * This program has the aim of finding a sequence of ADN that was badly split.
 * For example, if we have an initial ADN sequence of 'aaccGgTTC', there are two
 * random splits that we receive by parameter, such as 'aacc' 'GgTTC' or 'aaccG'
 * 'gTTC'. However, to make it interesting, they come mixed with parts from
 * another experiment such as 'aaaa' 'cccc' and 'gttc'.
 * 
 * In order to find the original sequence, we have to make permutations of
 * strings of a size. For example, we start with strings formed by two strings
 * and, if during the permutation, we see that this string has been already
 * formed and the words used to form it are different than the ones used to form
 * the current one, we found the solution.
 * 
 * If during that process we don't find a pair of concatenated strings equal, we
 * make the same process but choosing the strings formed by 3 strings. We repeat
 * the process until a solution is found or the size of the subgroups is higher
 * than the half of the number of strings.
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

	/** Sorted set of integer that indicate the final solution indexes. */
	private static TreeSet<Integer> finalSolution = new TreeSet<Integer>();

	/** Parameters for socket. */
	private static final int PORT = 3241;
	private static final String IP = "52.49.91.111";
	private static final String MODE = "SUBMIT";

	/** Key output file. */
	private static final String OUTPUT_FILE = "src/challenge5/key";

	/**
	 * Main method.
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println(getSolutionOf(
				"TATaCaACcCG aAgGacctcTtGgt TAttaCAtaTtA CgG TAttaCAtaTtAC CTATaCaACcCGcGg CCcCTaGcATaC cTgTGGAGAg cGgCgG"));
		// try {
		// Socket socket = new Socket(IP, PORT);
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(socket.getInputStream()));
		// PrintWriter writer = new PrintWriter(socket.getOutputStream());
		// System.out.println("Connection stablished with: " + socket.getInetAddress());
		// System.out.println("Starting Challenge 5!");
		//
		// // Getting the number of Cases
		// String firstLine = reader.readLine();
		// int numberOfCases = Integer.parseInt(firstLine.split("\\s")[1]);
		//
		// // Choosing mode
		// System.out.println(reader.readLine());
		// System.out.println("Setting MODE: " + MODE);
		// ;
		// writer.println(MODE);
		// writer.flush();
		//
		// // Starting tests
		// String key = "";
		// System.out.println(reader.readLine());
		// for (int i = 0; i < numberOfCases; ++i) {
		// String ADN = reader.readLine();
		// System.out.println(ADN);
		// String solution = getSolutionOf(ADN);
		// System.out.println(solution);
		//
		// writer.println(solution);
		// writer.flush();
		// key = reader.readLine();
		// System.out.println(key);
		// }
		//
		// reader.close();
		// writer.close();
		// socket.close();
		//
		// BufferedWriter keyWriter = new BufferedWriter(new FileWriter(OUTPUT_FILE));
		// keyWriter.write(key.split("\"")[1]); // To write the key
		// keyWriter.close();
		// }
		// catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	public static String getSolutionOf(String ADN) {
		finalSolution = new TreeSet<Integer>();
		ArrayList<String> aDNParts = new ArrayList<String>(Arrays.asList(ADN.split("\\s")));
		int maxSizeOfSubGroups = aDNParts.size() / 2;

		// Array to initialize the base solution, formed simply by the ADNParts without
		// permute.
		ArrayList<Solution> lastPermutedSolution = new ArrayList<Solution>();
		for (int i = 0; i < aDNParts.size(); ++i) {
			ArrayList<Integer> indexArray = new ArrayList<Integer>();
			indexArray.add(i);
			lastPermutedSolution.add(new Solution(aDNParts.get(i), indexArray));
		}
		ArrayList<Solution> initialPermutedSolution = new ArrayList<Solution>(lastPermutedSolution);
		ArrayList<Integer> bannedSolution = new ArrayList<Integer>();

		// Loop to permute between loops of size i + 1
		for (int i = 1; i < maxSizeOfSubGroups; ++i) {
			ArrayList<Solution> currentPermutatedSolution = permutateGroupsOf(initialPermutedSolution, lastPermutedSolution);
			if (currentPermutatedSolution == null) { // Solution Found
				break;
			}
			else {
				// bannedSolution = getBannedSolution(currentPermutatedSolution);
				lastPermutedSolution = currentPermutatedSolution;
			}
		}

		if (finalSolution == null) {
			throw new IllegalArgumentException("Not solution Found");
		}
		else {
			String result = "";
			for (Integer finalIndex : finalSolution) {
				result += (finalIndex + 1) + ",";
			}
			return result.substring(0, result.length() - 1);
		}
	}

	/**
	 * @param currentPermutatedSolution
	 */
	private static ArrayList<Integer> getBannedSolution(ArrayList<Solution> currentPermutatedSolution) {
		ArrayList<Integer> bannedSolutions = new ArrayList<Integer>();

		for (int i = 0; i < currentPermutatedSolution.size(); ++i) {
			boolean contained = false;
			String currentString = currentPermutatedSolution.get(i).getConcatenedString();
			for (int j = 0; j < currentPermutatedSolution.size(); ++j) {
				if (i == j) {
					continue;
				}
				String secondaryString = currentPermutatedSolution.get(j).getConcatenedString();
				if (currentString.contains(secondaryString.substring(secondaryString.length() / 2))
						|| currentString.contains(secondaryString.substring(0, secondaryString.length() / 2))) {
					System.out.println("Containing: " + currentString + " " + secondaryString);
					contained = true;
				}
			}
			if (!contained) {
				// System.out.println("Removing " + currentPermutatedSolution.get(i));
				for (Integer bannedIndex : currentPermutatedSolution.get(i).getIndexUsedForConcat()) {
					if (!bannedSolutions.contains(bannedIndex)) {
						bannedSolutions.add(bannedIndex);
					}
				}
			}
		}

		System.out.println("\nBanned: ");
		for (Integer i : bannedSolutions) {
			System.out.print(i + " ");
		}

		return bannedSolutions;
	}

	/**
	 * Method that permutes the ADNParts of size 1 with the base Array received by
	 * argument. If during that process there is another solution with the same
	 * string and formed by different indexes, it adds the two solutions to the
	 * finalSolution object.
	 */
	private static ArrayList<Solution> permutateGroupsOf(ArrayList<Solution> initialPermutedSolution,
			ArrayList<Solution> baseArray) {
		ArrayList<Solution> concatStringMap = new ArrayList<Solution>();
		for (int i = 0; i < baseArray.size(); ++i) {
			Solution currentBaseSolution = baseArray.get(i);

			for (int j = 0; j < initialPermutedSolution.size(); ++j) {
				if (currentBaseSolution.getIndexUsedForConcat().contains(j)) {
					continue;
				}
				ArrayList<Integer> newIndexArray = currentBaseSolution.getIndexUsedForConcat();
				newIndexArray.add(j);
				String currentConcatenedString = currentBaseSolution.getConcatenedString()
						.concat(initialPermutedSolution.get(j).getConcatenedString());
				System.out.println(
						currentBaseSolution.getConcatenedString() + " + " + initialPermutedSolution.get(j).getConcatenedString());

				Solution newSolution = new Solution(currentConcatenedString, newIndexArray);
				if (concatStringMap.contains(newSolution)) {
					finalSolution.addAll(concatStringMap.get(concatStringMap.indexOf(newSolution)).getIndexUsedForConcat());
					finalSolution.addAll(newSolution.getIndexUsedForConcat());
					return null;
				}
				else {
					concatStringMap.add(newSolution);
				}
			}
		}

		return concatStringMap;
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
