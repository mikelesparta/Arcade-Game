package uo.cpm.model;

import java.util.Objects;

public class Ticket {

    public final static double MIN_PRICE = 20;

    private String number;
    private double amount;
    private boolean isValid = true;

    public Ticket(String number) {
	this.number = number;
    }

    public Ticket(String number, double amount) {
	this.number = number;
	this.amount = amount;
    }

    public String getNumber() {
	return number;
    }

    public double getAmount() {
	return amount;
    }

    public boolean isValid() {
	return isValid;
    }

    public void setValid(boolean isValid) {
	this.isValid = isValid;
    }

    @Override
    public String toString() {
	return number + "@" + amount;
    }

    @Override
    public int hashCode() {
	return Objects.hash(number);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Ticket other = (Ticket) obj;
	return Objects.equals(number, other.number);
    }

}
