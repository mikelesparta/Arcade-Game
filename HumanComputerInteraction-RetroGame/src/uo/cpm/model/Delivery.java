package uo.cpm.model;

import java.util.List;

public class Delivery {

    private String id, storeCode;
    private List<String> prizeCodes;

    public Delivery(String id, String storeCode, List<String> prizeCodes) {
	super();
	this.id = id;
	this.storeCode = storeCode;
	this.prizeCodes = prizeCodes;
    }

    public String getId() {
	return id;
    }

    public String getStoreCode() {
	return storeCode;
    }

    public List<String> getPrizeCodes() {
	return prizeCodes;
    }

    @Override
    public String toString() {
	String message = "" + id + "@" + storeCode + "@";

	for (int i = 0; i < prizeCodes.size(); i++) {
	    message += prizeCodes.get(i);
	    if (i == prizeCodes.size() - 1) {
		;
	    } else {
		message += "@";
	    }
	}
	return message;
    }

}
