package uo.cpm.model;

public class Game {

    public static int DEBUG0 = 0;
    public static int DEBUG1 = 1;
    public static int DEBUG2 = 2;

    public final static int MAX_SHOTS = 5;
    public final static int MAX_WAVE = 10;

    private Board board;
    private Army army;
    private int shots, wave;
    private boolean isSuccesful, won;

    public Game(int debugMode) {
	initialize(debugMode);
    }

    public void initialize(int debugMode) {
	board = new Board(debugMode);
	army = new Army(debugMode);

	shots = MAX_SHOTS;
	wave = 1;
    }

    public void shoot(int i, int j, Cell newCell) {
	isSuccesful = false;

	// Do nothing
	if (board.getCells()[i][j].isOccupied())
	    ;

	// Create a instance of the cell in that position, remove one shot from
	// the army and update the score
	else {
	    shots--;
	    board.setCell(i, j, newCell);
	    isSuccesful = true;
	}
    }

    public Board getBoard() {
	return board;
    }

    public Army getArmy() {
	return army;
    }

    public int getScore() {
	return board.getScore();
    }

    public int getShots() {
	return shots;
    }

    public boolean isSuccesful() {
	return isSuccesful;
    }

    public int getWave() {
	return wave;
    }

    public void setWave(int wave) {
	this.wave = wave;
    }

    public void setShots(int i) {
	this.shots = i;
    }

    public boolean hasWon() {
	return won;
    }

    public boolean isBoardFull() {
	return board.isBoardFull();
    }

    public boolean hasFreeSpaces() {
	return board.hasFreeSpaces();
    }

    public boolean areWavesFinish() {
	if (wave > MAX_WAVE)
	    return true;
	return false;
    }

    public boolean isGameOver() {
	// In case the board is full of invaders
	if (isBoardFull()) {
	    won = false;
	    board.setScore(0);
	    return true;
	}

	// Once we finish wave 10
	else if (areWavesFinish()) {
	    if (hasFreeSpaces())
		won = true;

	    return true;
	}

	// When 5 or more of leader type (Mario) are eliminated
	else if (isLeaderEliminated()) {
	    won = true;
	    board.setScore(board.getScore() + 20000);
	    return true;
	}

	return false;
    }

    public boolean isLeaderEliminated() {
	return board.isLeaderEliminated();
    }
}
