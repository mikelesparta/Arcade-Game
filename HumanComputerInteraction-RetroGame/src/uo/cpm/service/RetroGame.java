package uo.cpm.service;

import uo.cpm.model.Army;
import uo.cpm.model.Board;
import uo.cpm.model.Cell;
import uo.cpm.model.Game;
import uo.cpm.model.Ticket;

public class RetroGame {

    private Menu menu;
    private Game game;

    private int debugMode;

    public RetroGame(int debugMode) {
	this.debugMode = debugMode;

	menu = new Menu();
	game = new Game(debugMode);
    }

    public Menu getMenu() {
	return menu;
    }

    public Game getGame() {
	return game;
    }

    public Board getBoard() {
	return game.getBoard();
    }

    public Army getArmy() {
	return game.getArmy();
    }

    public void initialize() {
	game.initialize(debugMode);
    }

    public void shoot(int x, int y, Cell newCell) {
	game.shoot(x, y, newCell);
    }

    public boolean isGameOver() {
	return game.isGameOver();
    }

    public boolean isSucessful() {
	return game.isSuccesful();
    }

    public int getScore() {
	return game.getScore();
    }

    public int getShots() {
	return game.getShots();
    }

    public int getWave() {
	return game.getWave();
    }

    public void setWave(int wave) {
	game.setWave(wave);
    }

    public void incWave() {
	game.setWave(getWave() + 1);
    }

    public void setShots(int i) {
	game.setShots(i);
    }

    public boolean isBoardFull() {
	return game.isBoardFull();
    }

    public boolean hasWon() {
	return game.hasWon();
    }

    public boolean isLeaderEliminated() {
	return game.isLeaderEliminated();
    }

    public void removeTicket() {
	menu.removeTicket();
    }

    public void setTicketToInvalid(Ticket ticket) {
	menu.setTicketToInvalid(ticket);
    }

}
