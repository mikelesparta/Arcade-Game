package uo.cpm.p6.rules;

import uo.cpm.p6.model.Board;
import uo.cpm.p6.model.Dice;
import uo.cpm.p6.model.Invader;
import uo.cpm.p6.model.Meteorite;
import uo.cpm.p6.util.Console;

public class Game {

    public static final Integer MAX_SHOTS_EASY = 5;
    public static final Integer MAX_SHOTS = 4;
    public static final Integer MAX_SHOTS_HARD = 3;

    private int score, shots;
    private boolean invaderFound, invader2Found, meteoriteFound,
	    meteorite2Found;

    private Board board;
    private Level level;

    public enum Level {
	EASY, INTERMIDIATE, HARD
    }

    public Game(Level level) {
	initialize(level);
    }

    public void initialize(Level level) {
	this.level = level;
	board = new Board(level);
	score = 800;
	shots = 0;
	invaderFound = false;
	invader2Found = false;
	meteoriteFound = false;
	meteorite2Found = false;
    }

    public void launch() {
	setShots(Dice.launch(level));
    }

    public void shoot(int i) {
	shots--;

	if (board.getCells()[i] instanceof Invader) {
	    invaderFound = true;
	    Console.printlnBlue("Invader!");
	}

	else if (board.getCells()[i] instanceof Meteorite) {
	    meteoriteFound = true;
	    score = 0;
	    Console.printlnRed("Meteorite!");
	} else
	    Console.println("Space!");

	score = score + board.getCells()[i].discover();
    }

    public boolean isGameOver() {
	switch (level) {
	case EASY:
	    return (isInvaderFound() || isInvader2Found() || getShots() == 0);
	case INTERMIDIATE:
	    return (isInvaderFound() || isMeteoriteFound() || getShots() == 0);
	case HARD:
	    return (isInvaderFound() || isMeteoriteFound()
		    || isMeteorite2Found() || getShots() == 0);
	}
	// Default
	return (isInvaderFound() || isMeteoriteFound() || getShots() == 0);
    }

    public Board getBoard() {
	return board;
    }

    public boolean isInvaderFound() {
	return invaderFound;
    }

    public boolean isInvader2Found() {
	return invader2Found;
    }

    public boolean isMeteoriteFound() {
	return meteoriteFound;
    }

    public boolean isMeteorite2Found() {
	return meteorite2Found;
    }

    public int getScore() {
	return score;
    }

    public int getShots() {
	return shots;
    }

    private void setShots(int shots) {
	this.shots = shots;
    }
}
