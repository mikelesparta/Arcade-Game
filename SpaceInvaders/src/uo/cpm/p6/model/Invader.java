package uo.cpm.p6.model;

public class Invader extends Cell {

    private boolean erased;

    public Invader() {
	setScore(3000);
	setErased(false);
	setPicture("/img/invader.jpg");
    }

    public boolean isErased() {
	return erased;
    }

    public void setErased(boolean erased) {
	this.erased = erased;
    }

    @Override
    public Integer discover() {
	setErased(true);
	return getScore();
    }
}
