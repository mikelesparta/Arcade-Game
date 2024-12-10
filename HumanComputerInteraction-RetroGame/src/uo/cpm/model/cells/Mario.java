package uo.cpm.model.cells;

import uo.cpm.model.Cell;

public class Mario extends Cell {

    public Mario() {
	setPicture("/img/mario.png");
	setLeader(true);
	setOccupied(true);
    }
}
