/**
 * Square.java
 *
 * @author Ángel Igareta (angel@igareta.com)
 * @version 1.0
 * @since 28-04-2018
 */
package challenge4;

/**
 * Abstract class that represent a Square and the available movements from it.
 */
public abstract class Square {
	public static final int MOVEMENT_NUMBER = 8;
	private int xPos;
	private int yPos;
	private int numberOfSteps;

	private int[] rowMovements;
	private int[] columnMovements;

	public Square(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	/** For ground square */
	public String getType() {
		return null;
	}

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @return the numberOfSteps
	 */
	public int getNumberOfSteps() {
		return numberOfSteps;
	}

	/**
	 * @param numberOfSteps
	 *          the numberOfSteps to set
	 */
	public void setNumberOfSteps(int numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	/**
	 * @param i
	 * @return
	 */
	public int getColumnMovementAt(int i) {
		if (i >= columnMovements.length) {
			throw new IllegalArgumentException("Trying to access a not valid position of row movements." + i);
		}
		return this.columnMovements[i];
	}

	/**
	 * @param i
	 * @return
	 */
	public int getRowMovementAt(int i) {
		if (i >= rowMovements.length) {
			throw new IllegalArgumentException("Trying to access a not valid position of row movements." + i);
		}
		return this.rowMovements[i];
	}

	/**
	 * @param columnMovements
	 *          the columnMovements to set
	 */
	public void setColumnMovements(int[] columnMovements) {
		this.columnMovements = columnMovements;
	}

	/**
	 * @param rowMovements
	 *          the rowMovements to set
	 */
	public void setRowMovements(int[] rowMovements) {
		this.rowMovements = rowMovements;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return this.getxPos() * 31 + this.getyPos();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object anotherObject) {		
		Square anotherSquare = (Square) anotherObject;
		return !(this.getxPos() != anotherSquare.getxPos() || this.getyPos() != anotherSquare.getyPos());
	}
}

/**
 * Square of type Ground, with it's movements.
 */
class GroundSquare extends Square {
	/** Available row movements for this square. */
	private final int[] ROW_MOVEMENTS = new int[] { 2, 2, -2, -2, 1, 1, -1, -1 };
	/** Available column movements for this square. */
	private final int[] COLUMN_MOVEMENTS = new int[] { -1, 1, 1, -1, 2, -2, 2, -2 };
	/** D, P, S, or . */
	private String type;

	/**
	 * Default constructor.
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public GroundSquare(int xPos, int yPos, String type) {
		super(xPos, yPos);
		this.setRowMovements(ROW_MOVEMENTS);
		this.setColumnMovements(COLUMN_MOVEMENTS);

		this.type = type;
	}

	/** For ground square */
	public String getType() {
		switch (this.type) {
			case ".":
				return "NORMAL";
			case "S":
				return "KNIGHT";
			case "P":
				return "PRINCESS";
			case "D":
				return "DESTINATION";
			default:
				throw new IllegalArgumentException("Bad ground square format.");
		}
	}

	/*
	 * (non-Javadoc) Returns the square representation.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.type;
	}
}

/**
 * Square of type Lava, with it's movements.
 */
class LavaSquare extends Square {

	/** Representation of the square. */
	private final String LAVA_REPRESENTATION = "#";

	/**
	 * Default constructor.
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public LavaSquare(int xPos, int yPos) {
		super(xPos, yPos);
	}

	/*
	 * (non-Javadoc) Returns the square representation.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return LAVA_REPRESENTATION;
	}
}

/**
 * Square of type Trampolin, with it's movements.
 */
class TrampolinSquare extends Square {

	/** Available row movements for this square. */
	private final int[] ROW_MOVEMENTS = new int[] { 4, 4, -4, -4, 2, 2, -2, -2 };
	/** Available column movements for this square. */
	private final int[] COLUMN_MOVEMENTS = new int[] { -2, 2, 2, -2, 4, -4, 4, -4 };
	/** Representation of the square. */
	private final String TRAMPOLIN_REPRESENTATION = "*";

	/**
	 * Default constructor.
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public TrampolinSquare(int xPos, int yPos) {
		super(xPos, yPos);
		this.setRowMovements(ROW_MOVEMENTS);
		this.setColumnMovements(COLUMN_MOVEMENTS);
	}

	/*
	 * (non-Javadoc) Returns the square representation.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return TRAMPOLIN_REPRESENTATION;
	}
}
