package uo.cpm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.cpm.model.Award;
import uo.cpm.model.Store;
import uo.cpm.model.Ticket;
import uo.cpm.util.FileUtil;

public class Menu {

    private static final String TICKETS_FILE = "files/tickets.dat";
    private static final String AWARDS_FILE = "files/awards.dat";
    private static final String STORE_FILE = "files/config.dat";

    private List<Ticket> ticketsList = null;
    private List<Award> awardsList = null;
    private List<Store> storesList = null;

    public Menu() {
	ticketsList = new ArrayList<Ticket>();
	awardsList = new ArrayList<Award>();
	storesList = new ArrayList<Store>();

	loadTickets();
	loadAwards();
	loadStore();
    }

    private void loadTickets() {
	FileUtil.loadTicketsFile(TICKETS_FILE, ticketsList);
    }

    public Ticket[] getTickets() {
	Ticket[] tickets = ticketsList.toArray(new Ticket[ticketsList.size()]);
	return tickets;
    }

    public List<Ticket> getTicketsList() {
	return ticketsList;
    }

    public Optional<Ticket> findTicket(Ticket t) {
	for (Ticket ticket : getTickets()) {
	    if (ticket.getNumber().equals(t.getNumber()))
		return Optional.of(ticket);
	}

	return Optional.empty();
    }

    private void loadAwards() {
	FileUtil.loadAwardsFile(AWARDS_FILE, awardsList);
    }

    public Award[] getAwards() {
	Award[] awards = awardsList.toArray(new Award[awardsList.size()]);
	return awards;
    }

    private void loadStore() {
	FileUtil.loadStoreFile(STORE_FILE, storesList);
    }

    public Store getStore() {
	return storesList.get(0);
    }

    public void removeTicket() {
	FileUtil.removeTicket(TICKETS_FILE, getTicketsList());
    }

    public void setTicketToInvalid(Ticket ticket) {
	int ticketIndex = getTicketIndex(ticket);
	this.ticketsList.remove(ticketIndex);
    }

    private int getTicketIndex(Ticket ticket) {
	int i = 0;

	for (Ticket t : ticketsList) {
	    if (t.equals(ticket))
		return i;

	    i++;
	}

	return i;
    }
}
