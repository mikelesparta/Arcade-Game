package uo.cpm.model;

public class Store {

    private String code, name;

    public Store() {
    }

    public Store(String code) {
	this.code = code;
    }

    public Store(String code, String name) {
	this.code = code;
	this.name = name;
    }

    public String getCode() {
	return code;
    }

    public String getName() {
	return name;
    }
}
