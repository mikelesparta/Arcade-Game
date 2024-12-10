package uo.cpm.model.cells;

import uo.cpm.model.Cell;

public class Empty extends Cell {

    public Empty() {
	setPicture("/img/blank.png");
	setOccupied(false);
	setEmpty(true);
    }
}
