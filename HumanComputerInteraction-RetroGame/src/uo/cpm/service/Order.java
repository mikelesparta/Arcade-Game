package uo.cpm.service;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.model.Award;
import uo.cpm.model.Delivery;
import uo.cpm.util.FileUtil;

public class Order {

    private static final String DELIVERIES_FILE = "files/deliveries.dat";

    // Auxiliary list that will be used on the combobox to enable the remove
    // button
    private List<String> awardsNameList = null;

    private List<Award> awardsList = null;

    private List<String> prizeCodeList = null;

    private String code = "";
    private boolean isSuccessful;
    private RetroGame game;

    public Order(RetroGame game) {
	awardsList = new ArrayList<Award>();
	awardsNameList = new ArrayList<String>();
	prizeCodeList = new ArrayList<String>();

	this.game = game;
    }

    public void initialize() {
	awardsList.clear();
    }

    public String getCode() {
	return code;
    }

    public List<Award> getAwardsList() {
	return awardsList;
    }

    public List<String> getAwardsNameList() {
	return awardsNameList;
    }

    public List<String> getPrizeCodeList() {
	return prizeCodeList;
    }

    public boolean isSuccessful() {
	return isSuccessful;
    }

    public RetroGame getGame() {
	return game;
    }

    public void add(Award item, int units) {
	Award itemInOrder = null;

	// Already have that award in your order
	for (Award award : awardsList) {
	    // Can add the item
	    if (award.getCode().equals(item.getCode())
		    && (getTotalPoints() + item.getPoints() * units) <= game
			    .getScore()) {
		itemInOrder = award;
		itemInOrder.setUnits(itemInOrder.getUnits() + units);
		isSuccessful = true;
		break;
	    } else { // Can NOT add the item
		isSuccessful = false;
	    }
	}

	// We don´t have that item in our order yet
	if (itemInOrder == null) {
	    Award itemToOrder = new Award(item);

	    // Can add the item
	    if ((getTotalPoints() + itemToOrder.getPoints() * units) <= game
		    .getScore()) {
		itemToOrder.setUnits(units);
		awardsList.add(itemToOrder);
		prizeCodeList.add(itemToOrder.getCode());
		awardsNameList.add(itemToOrder.getName());
		isSuccessful = true;
	    } else { // Can NOT add the item
		isSuccessful = false;
	    }
	}
    }

    public void remove(Award item, int units) {
	Award itemInOrder = null;

	// Already have that award in your order
	for (Award award : awardsList) {
	    if (award.getCode().equals(item.getCode())) {
		itemInOrder = award;
		int finalUnits = itemInOrder.getUnits() - units;

		if (finalUnits <= 0) {
		    itemInOrder.setUnits(0);
		    awardsList.remove(itemInOrder);
		    prizeCodeList.remove(itemInOrder.getCode());
		    awardsNameList.remove(itemInOrder.getName());
		    break;
		}

		itemInOrder.setUnits(finalUnits);
		break;
	    }
	}
    }

    public int getTotalPoints() {
	int total = 0;

	for (Award award : awardsList)
	    total += award.getPoints() * award.getUnits();

	return total;
    }

    public void saveOrder(Delivery delivery) {
	FileUtil.saveToFile(DELIVERIES_FILE, delivery);
    }

    @Override
    public String toString() {
	String text = "";
	for (Award a : awardsList)
	    text += a.orderToString() + "\n";
	text += "Total points: " + getTotalPoints();

	return text;
    }
}
