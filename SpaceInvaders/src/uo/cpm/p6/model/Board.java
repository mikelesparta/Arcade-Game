package uo.cpm.p6.model;

import uo.cpm.p6.rules.Game.Level;

public class Board {

    public static final int DIM_EASY = 10;
    public static final int DIM = 8;
    public static final int DIM_HARD = 6;

    Cell[] cells;

    public Board(Level level) {
	switch (level) {

	case EASY: {
	    cells = new Cell[DIM_EASY];
	    for (int i = 0; i < DIM_EASY; i++)
		cells[i] = new Space(i);

	    int invaderPosition = (int) (Math.random() * DIM_EASY);
	    cells[invaderPosition] = new Invader();

	    int invader2Position = (int) (Math.random() * DIM_EASY);
	    while (invader2Position == invaderPosition)
		invader2Position = (int) (Math.random() * DIM_EASY);
	    cells[invader2Position] = new Invader();
	    break;
	}

	case INTERMIDIATE: {
	    cells = new Cell[DIM];
	    for (int i = 0; i < DIM; i++)
		cells[i] = new Space(i);

	    int invaderPosition = (int) (Math.random() * DIM);
	    cells[invaderPosition] = new Invader();

	    int meteoritePosition = (int) (Math.random() * DIM);
	    while (meteoritePosition == invaderPosition)
		meteoritePosition = (int) (Math.random() * DIM);
	    cells[meteoritePosition] = new Meteorite();
	    break;
	}

	case HARD: {
	    cells = new Cell[DIM_HARD];
	    for (int i = 0; i < DIM_HARD; i++)
		cells[i] = new Space(i);

	    int invaderPosition = (int) (Math.random() * DIM_HARD);
	    cells[invaderPosition] = new Invader();

	    int meteoritePosition = (int) (Math.random() * DIM_HARD);
	    while (meteoritePosition == invaderPosition)
		meteoritePosition = (int) (Math.random() * DIM_HARD);
	    cells[meteoritePosition] = new Meteorite();

	    int meteorite2Position = (int) (Math.random() * DIM_HARD);
	    while (meteorite2Position == invaderPosition
		    || meteorite2Position == meteoritePosition)
		meteorite2Position = (int) (Math.random() * DIM_HARD);
	    cells[meteorite2Position] = new Meteorite();
	    break;
	}
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
}
