package uo.cpm.service;

import uo.cpm.model.Award;
import uo.cpm.model.Delivery;

public class AwardSelection {

    private Menu menu = new Menu();
    private Order order;
    private RetroGame game;

    public AwardSelection(RetroGame retroGame) {
	this.game = retroGame;
	order = new Order(game);
    }

    public Award[] getAwards() {
	return menu.getAwards();
    }

    public void initOrder() {
	order.initialize();
    }

    public String getOrderCode() {
	return order.getCode();
    }

    public void addToOrder(Award award, Integer units) {
	order.add(award, units);
    }

    public void removeFromOrder(Award award, Integer units) {
	order.remove(award, units);
    }

    public boolean isSuccessful() {
	return order.isSuccessful();
    }

    public void saveOrder(Delivery delivery) {
	order.saveOrder(delivery);
    }

    public int getOrderTotalPoints() {
	return order.getTotalPoints();
    }

    public int getDifference() {
	return game.getScore() - getOrderTotalPoints();
    }

    public Order getOrder() {
	return order;
    }
}
