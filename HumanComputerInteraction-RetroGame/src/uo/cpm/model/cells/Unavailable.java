package uo.cpm.model.cells;

import uo.cpm.model.Cell;

public class Unavailable extends Cell {

    public Unavailable() {
	setPicture("/img/blank2.png");
	setOccupied(true);
	setEmpty(true);
    }
}
