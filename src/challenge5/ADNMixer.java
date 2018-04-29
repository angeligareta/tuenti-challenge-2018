/**
 * ADNMixer.java
 *
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 28-04-2018
 */
package challenge5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * [DESCRIPTION]
 */
public class ADNMixer {

	/** Sorted set of integer that indicate the final solution indexes. */
	private static TreeSet<Integer> finalSolution = new TreeSet<Integer>();

	/**
	 * Main method.
	 */
	public static void main(String[] args) throws InterruptedException {
		String ADN = "TATaCaACcCG aAgGacctcTtGgt TAttaCAtaTtA CgG TAttaCAtaTtAC CTATaCaACcCGcGg CCcCTaGcATaC cTgTGGAGAg cGgCgG";
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

		// Loop to permute between loops of size i + 1
		for (int i = 1; i < maxSizeOfSubGroups; ++i) {
			ArrayList<Solution> currentPermutatedSolution = permutateGroupsOf(aDNParts, lastPermutedSolution);
			if (currentPermutatedSolution == null) { // Solution Found
				break;
			}
			else {
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
			result = result.substring(0, result.length() - 1);
			System.out.println(result);
		}
	}

	/**
	 * Method that permutes the ADNParts of size 1 with the base Array received by
	 * argument. If during that process there is another solution with the same
	 * string and formed by different indexes, it adds the two solutions to the
	 * finalSolution object.
	 */
	private static ArrayList<Solution> permutateGroupsOf(ArrayList<String> aDNParts, ArrayList<Solution> baseArray) {
		ArrayList<Solution> concatStringMap = new ArrayList<Solution>();

		for (int i = 0; i < baseArray.size(); ++i) {
			Solution currentBaseSolution = baseArray.get(i);

			for (int j = 0; j < aDNParts.size(); ++j) {
				if (currentBaseSolution.getIndexUsedForConcat().contains(j)) {
					continue;
				}

				ArrayList<Integer> newIndexArray = currentBaseSolution.getIndexUsedForConcat();
				newIndexArray.add(j);
				String currentConcatenedString = currentBaseSolution.getConcatenedString().concat(aDNParts.get(j));

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
