package uo.cpm.model;

import java.util.Random;

import uo.cpm.model.cells.Bubble;
import uo.cpm.model.cells.Donkey;
import uo.cpm.model.cells.Empty;
import uo.cpm.model.cells.Kirby;
import uo.cpm.model.cells.Mario;
import uo.cpm.model.cells.Metroid;
import uo.cpm.model.cells.Pacman;
import uo.cpm.model.cells.Sonic;
import uo.cpm.model.cells.Zelda;

public class Army {

    public static final int DIM_ARMY = 5;
    public static final Random RANDOM = new Random();

    private Cell[] choices = { new Bubble(), new Donkey(), new Kirby(),
	    new Mario(), new Metroid(), new Pacman(), new Sonic(),
	    new Zelda() };

    private Cell[] choicesNoLeader = { new Bubble(), new Donkey(), new Kirby(),
	    new Metroid(), new Pacman(), new Sonic(), new Zelda() };

    private Cell[] cells;

    public Army(int debugMode) {
	cells = new Cell[DIM_ARMY];
	for (int i = 0; i < DIM_ARMY; i++)
	    cells[i] = new Empty();

	shuffle(debugMode);
    }

    public void shuffle(int debugMode) {
	if (debugMode == Game.DEBUG0) {
	    for (int i = 0; i < DIM_ARMY; i++) {
		int randomChoice = RANDOM.nextInt(choices.length);
		cells[i] = choices[randomChoice];
	    }
	} else if (debugMode == Game.DEBUG1) {
	    for (int i = 0; i < DIM_ARMY; i++)
		cells[i] = new Mario();
	} else if (debugMode == Game.DEBUG2) {
	    for (int i = 0; i < DIM_ARMY; i++) {
		int randomChoiceNoLeader = RANDOM
			.nextInt(choicesNoLeader.length);
		cells[i] = choicesNoLeader[randomChoiceNoLeader];
	    }
	} else {
	    throw new RuntimeException("There is only debug mode 0, 1 or 2.");
	}
    }

    public Cell[] getCells() {
	return cells;
    }

    public Cell getCell(int position) {
	return cells[position];
    }

    public void setCells(Cell[] cells) {
	this.cells = cells;
    }

    public String getPicture(Integer position) {
	return this.cells[position].getPicture();
    }

    public void removeCell(int pos) {
	for (int i = 0; i < DIM_ARMY - 1; i++) {
	    if (i >= pos) {
		cells[i] = cells[i + 1];
	    }
	}
	cells[DIM_ARMY - 1] = new Empty();

    }
}
