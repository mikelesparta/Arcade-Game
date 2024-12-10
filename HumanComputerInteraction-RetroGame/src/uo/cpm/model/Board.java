package uo.cpm.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import uo.cpm.model.cells.Bubble;
import uo.cpm.model.cells.Donkey;
import uo.cpm.model.cells.Empty;
import uo.cpm.model.cells.Kirby;
import uo.cpm.model.cells.Mario;
import uo.cpm.model.cells.Metroid;
import uo.cpm.model.cells.Pacman;
import uo.cpm.model.cells.Sonic;
import uo.cpm.model.cells.Unavailable;
import uo.cpm.model.cells.Zelda;

public class Board {

    public final int POSX = 0;
    public final int POS_MIDDLE = 3;
    public final int POSY = 6;
    public static final int DIM = 7;
    public static final int NINVADERS = 5;

    private Cell[] choices = { new Bubble(), new Donkey(), new Kirby(),
	    new Mario(), new Metroid(), new Pacman(), new Sonic(),
	    new Zelda() };
    private Cell[] choicesNoLeader = { new Bubble(), new Donkey(), new Kirby(),
	    new Metroid(), new Pacman(), new Sonic(), new Zelda() };

    private Cell[][] cells;

    private boolean leaderEliminated = false;

    private int score = 0;

    public Board(int debugMode) {
	// We generate the board with empty cells
	cells = new Cell[DIM][DIM];
	for (int i = 0; i < DIM; i++)
	    for (int j = 0; j < DIM; j++)
		cells[i][j] = new Empty();

	// Non-accesible
	cells[POSX][POSX] = new Unavailable();
	cells[POSX][POSY] = new Unavailable();
	cells[POS_MIDDLE][POS_MIDDLE] = new Unavailable();
	cells[POSY][POSX] = new Unavailable();
	cells[POSY][POSY] = new Unavailable();

	newWave(debugMode);
    }

    public int getScore() {
	return this.score;
    }

    public void setScore(int score) {
	this.score = score;
    }

    public void newWave(int debugMode) {
	int row = (int) (Math.random() * DIM);
	int col = (int) (Math.random() * DIM);

	if (debugMode == Game.DEBUG0) {
	    int randomChoice = new Random().nextInt(choices.length);

	    for (int i = 0; i < NINVADERS; i++) {
		while (cells[row][col].isOccupied()) {
		    row = (int) (Math.random() * DIM);
		    col = (int) (Math.random() * DIM);
		}
		randomChoice = new Random().nextInt(choices.length);
		cells[row][col] = choices[randomChoice];
	    }
	} else if (debugMode == Game.DEBUG1) {
	    for (int i = 0; i < NINVADERS; i++) {
		while (cells[row][col].isOccupied()) {
		    row = (int) (Math.random() * DIM);
		    col = (int) (Math.random() * DIM);
		}

		cells[row][col] = new Mario();
	    }
	} else if (debugMode == Game.DEBUG2) {
	    int randomChoiceNoLeader = new Random()
		    .nextInt(choicesNoLeader.length);

	    for (int i = 0; i < NINVADERS; i++) {
		while (cells[row][col].isOccupied()) {
		    row = (int) (Math.random() * DIM);
		    col = (int) (Math.random() * DIM);
		}
		randomChoiceNoLeader = new Random()
			.nextInt(choicesNoLeader.length);
		cells[row][col] = choicesNoLeader[randomChoiceNoLeader];
	    }
	} else {
	    throw new RuntimeException("There is only debug mode 0, 1 or 2.");
	}
    }

    public void setCell(int x, int y, Cell cell) {
	this.cells[x][y] = cell;
    }

    public Cell[][] getCells() {
	return cells;
    }

    public Cell getCell(int x, int y) {
	return cells[x][y];
    }

    public void setCells(Cell[][] cells) {
	this.cells = cells;
    }

    public String getPicture(int x, int y) {
	return this.cells[x][y].getPicture();
    }

    public boolean isBoardFull() {
	boolean isFull = true;

	for (int i = 0; i < DIM; i++)
	    for (int j = 0; j < DIM; j++)
		if (!cells[i][j].isOccupied())
		    isFull = false;

	return isFull;
    }

    public boolean hasFreeSpaces() {
	boolean freeSpaces = false;

	// If there is a cell that is not occupied, there are free spaces
	for (int i = 0; i < DIM; i++)
	    for (int j = 0; j < DIM; j++)
		if (!cells[i][j].isOccupied())
		    freeSpaces = true;

	return freeSpaces;
    }

    public boolean isLeaderEliminated() {
	return leaderEliminated;
    }

    public void removePairs() {
	// First remove crosses
	removingCross(this.cells);

	// Remove horizontal pairs
	this.cells = removeHorizontal(this.cells);

	// To remove vertical pairs rotate the board, delete them and rotate to
	// its natural form
	this.cells = rotateMatrix(cells);
	this.cells = removeHorizontal(cells);
	this.cells = rotateMatrix(cells);
	this.cells = rotateMatrix(cells);

	// Set the board to the updated rotated cells
	this.cells = rotateMatrix(cells);
    }

    private static Cell[][] rotateMatrix(Cell[][] matrix) {
	Cell[][] cellsRotated = new Cell[DIM][DIM];

	for (int i = 0; i < DIM; i++)
	    for (int j = 0; j < DIM; j++)
		cellsRotated[j][DIM - i - 1] = matrix[i][j];

	return cellsRotated;
    }

    private Cell[][] removeHorizontal(Cell[][] matrix) {
	// Iterate the entire board
	for (int i = 0; i < DIM; i++) {
	    for (int j = 0; j < DIM; j++) {

		// Check on those that are NOT empty or unavailable
		if (!matrix[i][j].isEmpty()) {

		    // Count the consecutive pairs
		    int countConsecutive = countConsecutive(matrix, i, j);

		    // Has to be greater or equal to 3 in order to remove them
		    if (countConsecutive >= 3) {

			// Condition to end in case it is 5 or more from Mario
			// (leader type)
			if (countConsecutive >= 5 && matrix[i][j].getPicture()
				.equals(new Mario().getPicture()))
			    this.leaderEliminated = true;

			if (countConsecutive == 7)
			    score += 10000;
			else if (countConsecutive == 6)
			    score += 5000;
			else if (countConsecutive == 5)
			    score += 1000;
			else if (countConsecutive == 4)
			    score += 200;
			else if (countConsecutive == 3)
			    score += 50;

			removePairs(matrix, i, j, countConsecutive);
		    }
		}
	    }
	}

	return matrix;
    }

    private int countConsecutive(Cell[][] matrix, int row, int col) {
	int countConsecutive = 0;
	boolean isConsecutive;

	// Maximum possible value depending of the position of the column to
	// iterate
	int maxPairs = Math.min(DIM - col, 7);

	for (int numPairs = maxPairs; numPairs > 2; numPairs--) {
	    isConsecutive = true;

	    for (int k = 1; k < numPairs; k++) {
		// If the image is not the same anymore then not consecutive
		if (!matrix[row][col].getPicture()
			.equals(matrix[row][col + k].getPicture())) {
		    isConsecutive = false;
		    break;
		}
	    }

	    if (isConsecutive) {
		countConsecutive = numPairs;
		break;
	    }
	}

	return countConsecutive;
    }

    private void removePairs(Cell[][] matrix, int row, int col,
	    int countConsecutive) {
	for (int i = 0; i < countConsecutive; i++)
	    matrix[row][col + i] = new Empty();
    }

    private void removePairsVertical(Cell[][] matrix, int row, int col,
	    int countConsecutive) {
	for (int i = 0; i < countConsecutive; i++) {
	    if (row == 0)
		matrix[row + i][col] = new Empty();
	    else {
		// If the cell is empty, do nothing
		if (row + i - 1 < DIM && col + 1 < DIM)
		    if (!matrix[row + i - 1][col + 1].isEmpty())
			matrix[row + i - 1][col + 1] = new Empty();
	    }
	}
    }

    public void removingCross(Cell[][] matrix) {
	for (int i = 0; i < DIM; i++) {
	    for (int j = 0; j < DIM; j++) {

		// Check on those that are NOT empty or unavailable
		if (!matrix[i][j].isEmpty()) {

		    // Count the consecutive pairs
		    int countConsecutiveHorizontal = countConsecutive(matrix, i,
			    j);
		    Cell[][] rotatedMatrix = rotateMatrix(matrix);
		    int countConsecutiveVertical = countConsecutive(
			    rotatedMatrix, i, j);

		    if (countConsecutiveHorizontal >= 3
			    && countConsecutiveVertical >= 3) {
			removePairs(matrix, i, j, countConsecutiveHorizontal);
			removePairsVertical(matrix, i, j,
				countConsecutiveVertical);
		    }

		}
	    }
	}
    }

    public static void removeCross(Cell[][] matrix) {
	// A set to not have duplicates
	Set<Cell> cellsSet = new HashSet<Cell>();

	// Horizontal pairs
	for (int i = 0; i < DIM; i++) {
	    for (int j = 0; j < DIM - 2; j++) {
//		int maxPairs = Math.min(DIM - j, 7);
		String current = matrix[i][j].getPicture();
		String next1 = matrix[i][j + 1].getPicture();
		String next2 = matrix[i][j + 2].getPicture();
		String next3 = matrix[i][j + 3].getPicture();
		String next4 = matrix[i][j + 4].getPicture();

		// 5x3
		if (current.equals(next1) && current.equals(next2)
			&& current.equals(next3) && current.equals(next4)) {
		    cellsSet.add(matrix[i][j]);
		    cellsSet.add(matrix[i][j + 1]);
		    cellsSet.add(matrix[i][j + 2]);
		    cellsSet.add(matrix[i][j + 3]);
		    cellsSet.add(matrix[i][j + 4]);
		}

		// 4x3
		else if (current.equals(next1) && current.equals(next2)
			&& current.equals(next3)) {
		    cellsSet.add(matrix[i][j]);
		    cellsSet.add(matrix[i][j + 1]);
		    cellsSet.add(matrix[i][j + 2]);
		    cellsSet.add(matrix[i][j + 3]);
		}

		// 3x3
		else if (current.equals(next1) && current.equals(next2)) {
		    cellsSet.add(matrix[i][j]);
		    cellsSet.add(matrix[i][j + 1]);
		    cellsSet.add(matrix[i][j + 2]);
		}

	    }

	}

	// Vertical pairs
	for (int j = 0; j < DIM; j++) {
	    for (int i = 0; i < DIM - 2; i++) {
		String current = matrix[i][j].getPicture();
		String next1 = matrix[i + 1][j].getPicture();
		String next2 = matrix[i + 2][j].getPicture();
		String next3 = matrix[i + 3][j].getPicture();
		String next4 = matrix[i + 4][j].getPicture();

		// 3x5
		if (current.equals(next1) && current.equals(next2)
			&& current.equals(next3) && current.equals(next4)) {
		    cellsSet.add(matrix[i][j]);
		    cellsSet.add(matrix[i + 1][j]);
		    cellsSet.add(matrix[i + 2][j]);
		    cellsSet.add(matrix[i + 3][j]);
		    cellsSet.add(matrix[i + 4][j]);
		}

		// 3x4
		else if (current.equals(next1) && current.equals(next2)
			&& current.equals(next3)) {
		    cellsSet.add(matrix[i][j]);
		    cellsSet.add(matrix[i + 1][j]);
		    cellsSet.add(matrix[i + 2][j]);
		    cellsSet.add(matrix[i + 3][j]);
		}
		// 3x3
		else if (current.equals(next1) && current.equals(next2)) {
		    cellsSet.add(matrix[i][j]);
		    cellsSet.add(matrix[i + 1][j]);
		    cellsSet.add(matrix[i + 2][j]);
		}

	    }
	}

	// Remove pairs
	for (int i = 0; i < DIM; i++)
	    for (int j = 0; j < DIM; j++)
		if (cellsSet.contains(matrix[i][j]))
		    matrix[i][j] = new Empty();
    }
}
