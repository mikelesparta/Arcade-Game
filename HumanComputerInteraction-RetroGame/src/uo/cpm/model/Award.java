package uo.cpm.model;

public class Award {

    private String code, name, description, type;
    private int points, units;

    public Award() {
    }

    public Award(String code, String name, String description, String type,
	    int points) {
	super();
	this.code = code;
	this.name = name;
	this.description = description;
	this.type = type;
	this.points = points;
    }

    public Award(Award award) {
	this(award.code, award.name, award.description, award.type,
		award.points);
    }

    public String getCode() {
	return code;
    }

    public String getName() {
	return name;
    }

    public String getDescription() {
	return description;
    }

    public String getType() {
	return type;
    }

    public int getPoints() {
	return points;
    }

    public int getUnits() {
	return units;
    }

    public void setUnits(int units) {
	this.units = units;
    }

    @Override
    public String toString() {
	StringBuffer buffer = new StringBuffer();
	buffer.append(this.type);
	buffer.append(" - ");
	buffer.append(this.name);
	buffer.append(" - ");
	buffer.append(this.points);
	buffer.append(" points");
	return buffer.toString();
    }

    public String orderToString() {
	StringBuffer buffer = new StringBuffer();
	buffer.append(this.name);
	buffer.append(" - ");
	if (this.units != 0) {
	    buffer.append(this.units);
	    buffer.append(" uds");
	}
	return buffer.toString();
    }
}
