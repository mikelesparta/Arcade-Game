package uo.cpm.model;

public abstract class Cell {

    private String picture;
    private boolean isLeader, occupied, replace, empty;

    public String getPicture() {
	return picture;
    }

    public void setPicture(String picture) {
	this.picture = picture;
    }

    public boolean isLeader() {
	return isLeader;
    }

    public void setLeader(boolean isLeader) {
	this.isLeader = isLeader;
    }

    public boolean isOccupied() {
	return occupied;
    }

    public void setOccupied(boolean occupied) {
	this.occupied = occupied;
    }

    public boolean isReplaced() {
	return replace;
    }

    public void setReplace(boolean replace) {
	this.replace = replace;
    }

    public void setEmpty(boolean empty) {
	this.empty = empty;
    }

    public boolean isEmpty() {
	return empty;
    }
}