/**
 * BraveKnight is a program that receives a board with a fixed width and height
 * and with squares as elements. However, there are different types of squares.
 * - The simplest one is represented by '.' and is the ground square. - The next
 * one is represented by '*' and is the trampolin square. - FInally there is a
 * forbidden square represented by '#' and called the lava square. As the name
 * indicates, we can not touch this square.
 * 
 * Besides, in the board there is knight ('S'), a princess ('P') and a
 * destination ('D'). The mission is (as expected) to save the princess! For
 * doing so we have to reach the princess square and then carry her to the
 * destination.
 * 
 * Nevertheless there are some difficulties in our way. The first one is that,
 * depending on the cell we are in, we can move in one way. In the ground square
 * we can move as a chess knight (two squares away in one direction and one in
 * another.) On the other hand, if we are in a trampolin one, we can duplicate
 * those movements.
 * 
 * The program consists on give the minimum number of steps the knight has to do
 * to reach the princess and carry her to the destination. For that, we make an
 * open-closed classes hierarchy for the square types, that help us to know what
 * movements we can do. Besides, for calculating the distances we made a
 * modified BFS.
 * 
 * The modified BFS starts from source and explore it's neighbors, if the
 * distances of the neighbors are uninitialized or are better than the current
 * one, update the neighbor distance. For each updated distance we explore it's
 * neighbors.
 * 
 * So the only step left is to do a BFS from the knight to the princess and from
 * the princess to the destination. If there weren't any problems, we return the
 * sum of those distances.
 * 
 * PRINCESS SAVED!
 *
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 28-04-2018
 */
package challenge4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BraveKnight {

	/** String that specifies the name of the input file. */
	private final static String INPUT_FILENAME = "src/challenge4/submitInput";
	/**
	 * String that specifies the name of the output file, with the required format.
	 */
	private final static String OUTPUT_FILENAME = "src/challenge4/submitOutput";

	/**
	 * Method that determine if a position in the board is valid, according to if
	 * it's inside the board and the destination is not a lava square.
	 */
	private static boolean isValid(ArrayList<ArrayList<Square>> board, int boardWidth, int boardHeight, int neighborXPos,
			int neighborYPos) {
		return ((neighborXPos < boardWidth) && (neighborXPos >= 0) && (neighborYPos < boardHeight) && (neighborYPos >= 0)
				&& !(board.get(neighborYPos).get(neighborXPos) instanceof LavaSquare));
	}

	/**
	 * Implementation of a Breadth-first-search modified. It starts from source and
	 * explore it's neighbors, if the distances of the neighbors are uninitialized
	 * or are better than the current one, update the neighbor distance. For each
	 * updated distance we explore it's neighbors.
	 */
	private static int BFS(ArrayList<ArrayList<Square>> board, int boardWidth, int boardHeight, Square source,
			Square destination) {
		Queue<Square> squareQueue = new ArrayDeque<>();
		squareQueue.add(source);
		int currentMinNumberOfSteps = 0;

		// While there are elements to evaluate in the board.
		while (!squareQueue.isEmpty()) {
			Square currentSquare = squareQueue.poll();

			// If reached destination and the distance is better to the current one.
			if ((currentSquare.getxPos() == destination.getxPos()) && (currentSquare.getyPos() == destination.getyPos())) {
				if ((currentMinNumberOfSteps > currentSquare.getNumberOfSteps()) || currentMinNumberOfSteps == 0) {
					currentMinNumberOfSteps = currentSquare.getNumberOfSteps();
				}
			}
			else {
				for (int i = 0; i < Square.MOVEMENT_NUMBER; ++i) {
					int neighborXPos = currentSquare.getxPos() + currentSquare.getRowMovementAt(i);
					int neighborYPos = currentSquare.getyPos() + currentSquare.getColumnMovementAt(i);

					// If is valid and the distance to the element is lower than the current one,
					// update the element distance and add it to the queue.
					if (isValid(board, boardWidth, boardHeight, neighborXPos, neighborYPos)) {
						Square neighborSquare = board.get(neighborYPos).get(neighborXPos);
						int neighborNumberOfSteps = neighborSquare.getNumberOfSteps();
						if (((currentSquare.getNumberOfSteps() + 1) < neighborNumberOfSteps) || neighborNumberOfSteps == 0) {
							neighborSquare.setNumberOfSteps(currentSquare.getNumberOfSteps() + 1);
							squareQueue.add(neighborSquare);
						}
					}
				}
			}
		}
		return currentMinNumberOfSteps;
	}

	/**
	 * Method that resets the board distances, as they are used in the BFS.
	 */
	private static void resetBoardDistances(ArrayList<ArrayList<Square>> board) {
		for (ArrayList<Square> boardRow : board) {
			for (Square element : boardRow) {
				element.setNumberOfSteps(0);
			}
		}
	}

	/**
	 * Returns the minimum number of steps, doing a modified BFS from source to the
	 * princess square and from princess square to destination.
	 */
	private static int getMinNumberOfSteps(ArrayList<ArrayList<Square>> board, int boardWidth, int boardHeight,
			Square source, Square princess, Square destination) {
		// showBoard(boardWidth, boardHeight, board);

		if (source == null || princess == null) {
			throw new IllegalArgumentException("No source or princess in the board.");
		}
		else if (destination == null) {
			return 0;
		}

		int stepsFromSourceToPrincess = BFS(board, boardWidth, boardHeight, source, princess);
		resetBoardDistances(board);
		int stepsFromPrincessToDestination = BFS(board, boardWidth, boardHeight, princess, destination);

		return (stepsFromSourceToPrincess == 0 || stepsFromPrincessToDestination == 0) ? 0
				: stepsFromSourceToPrincess + stepsFromPrincessToDestination;
	}

	/**
	 * Main method. It doesn't receive anything. It reads the input file, evaluate
	 * it and write the result in the output file.
	 */
	public static void main(String[] args) {
		try {
			BufferedReader codeReader = new BufferedReader(new FileReader(INPUT_FILENAME));
			BufferedWriter codeWriter = new BufferedWriter(new FileWriter(OUTPUT_FILENAME));

			int numberOfLines = Integer.parseInt(codeReader.readLine());

			// Reading the file
			for (int i = 1; i <= numberOfLines; ++i) {
				String[] boardDimensions = codeReader.readLine().trim().split("\\s");
				if (boardDimensions.length > 2) {
					codeReader.close();
					codeWriter.close();
					throw new IllegalArgumentException("Board dimensions not valid.");
				}

				int boardHeight = Integer.parseInt(boardDimensions[0]);
				int boardWidth = Integer.parseInt(boardDimensions[1]);
				Square source = null, destination = null, princess = null;

				// Reading the board
				ArrayList<ArrayList<Square>> board = new ArrayList<ArrayList<Square>>();
				for (int j = 0; j < boardHeight; ++j) {
					String[] rowElementsArray = codeReader.readLine().trim().split("");
					if (boardDimensions.length >= boardWidth) {
						codeReader.close();
						codeWriter.close();
						throw new IllegalArgumentException("Board rows not valid.");
					}

					// Reading the rows and constructing the elements.
					ArrayList<Square> boardRow = new ArrayList<Square>();
					for (int k = 0; k < boardWidth; ++k) {
						Square newSquare = constructSquare(k, j, rowElementsArray[k]);
						if (newSquare.getType() != null) {
							switch (newSquare.getType()) {
								case "PRINCESS":
									if (princess == null) {
										princess = newSquare;
									}
									else {
										throw new IllegalArgumentException("Two princess in the board.");
									}
									break;
								case "KNIGHT":
									if (source == null) {
										source = newSquare;
									}
									else {
										throw new IllegalArgumentException("Two knights in the board.");
									}
									break;
								case "DESTINATION":
									if (destination == null) {
										destination = newSquare;
									}
									else {
										throw new IllegalArgumentException("Two destinations in the board.");
									}
									break;
							}
						}
						boardRow.add(newSquare);
					}
					board.add(boardRow);
				}

				int minNumberOfSteps = getMinNumberOfSteps(board, boardWidth, boardHeight, source, princess, destination);
				String solutionSteps = (minNumberOfSteps == 0) ? "IMPOSSIBLE" : String.valueOf(minNumberOfSteps);
				System.out.println("Case #" + i + ": " + solutionSteps);
				codeWriter.write("Case #" + i + ": " + solutionSteps + System.lineSeparator());
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
	 * Method that returns a subclass of square depending on the element received by
	 * argument.
	 */
	private static Square constructSquare(int xPos, int yPos, String rowElement) {
		switch (rowElement) {
			case ".":
			case "D":
			case "S":
			case "P":
				return new GroundSquare(xPos, yPos, rowElement);
			case "#":
				return new LavaSquare(xPos, yPos);
			case "*":
				return new TrampolinSquare(xPos, yPos);
			default:
				throw new IllegalArgumentException("Element in board row not valid.");
		}
	}

	/**
	 * Method that simply shows the board.
	 */
	private static void showBoard(int boardWidth, int boardHeight, ArrayList<ArrayList<Square>> board) {
		System.out.println("BOARD DIMENSIONS: " + boardWidth + " x " + boardHeight);
		System.out.print("   ");
		for (int i = 0; i < boardWidth; ++i) {
			System.out.print(i + "  ");
		}
		System.out.println("");
		int j = 0;
		for (ArrayList<Square> boardRow : board) {
			System.out.print(j++ + "  ");
			for (Square element : boardRow) {
				System.out.print(element + "  ");
			}
			System.out.println("");
		}
	}
}