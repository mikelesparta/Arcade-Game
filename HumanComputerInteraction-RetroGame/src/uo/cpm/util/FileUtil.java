package uo.cpm.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import uo.cpm.model.Award;
import uo.cpm.model.Delivery;
import uo.cpm.model.Store;
import uo.cpm.model.Ticket;

public abstract class FileUtil {

    public static void loadTicketsFile(String fileName,
	    List<Ticket> productsList) {

	String line;
	String[] productData = null;

	try {
	    BufferedReader file = new BufferedReader(new FileReader(fileName));
	    while (file.ready()) {
		line = file.readLine();
		productData = line.split("@");
		productsList.add(new Ticket(productData[0],
			Double.valueOf(productData[1])));
	    }
	    file.close();
	} catch (FileNotFoundException fnfe) {
	    System.out.println("File not found.");
	} catch (IOException ioe) {
	    new RuntimeException("I/O Error.");
	}
    }

    public static void removeTicket(String fileName, List<Ticket> tickets) {
	try {
	    BufferedWriter file = new BufferedWriter(
		    new FileWriter(fileName, false));
	    String newLine = "\n";

	    for (Ticket t : tickets) {
		String line = t.toString();
		file.write(line);
		file.write(newLine);
	    }

	    file.close();
	} catch (FileNotFoundException fnfe) {
	    System.out.println("The file could not be saved.");
	} catch (IOException ioe) {
	    new RuntimeException("I/O Error.");
	}
    }

    public static void loadStoreFile(String fileName, List<Store> storesList) {
	String line;
	String[] productData = null;

	try {
	    BufferedReader file = new BufferedReader(new FileReader(fileName));
	    while (file.ready()) {
		line = file.readLine();
		productData = line.split("@");
		storesList.add(new Store(productData[0], productData[1]));
	    }
	    file.close();
	} catch (FileNotFoundException fnfe) {
	    System.out.println("File not found.");
	} catch (IOException ioe) {
	    new RuntimeException("I/O Error.");
	}
    }

    public static void loadAwardsFile(String fileName, List<Award> awardsList) {
	String line;
	String[] productData = null;

	try {
	    BufferedReader file = new BufferedReader(new FileReader(fileName));
	    while (file.ready()) {
		line = file.readLine();
		productData = line.split("@");
		awardsList.add(new Award(productData[0], productData[1],
			productData[2], productData[3],
			Integer.valueOf(productData[4])));
	    }
	    file.close();
	} catch (FileNotFoundException fnfe) {
	    System.out.println("File not found.");
	} catch (IOException ioe) {
	    new RuntimeException("I/O Error.");
	}
    }

    public static void saveToFile(String fileName, Delivery delivery) {
	try {
	    BufferedWriter file = new BufferedWriter(
		    new FileWriter(fileName, true));
	    File newFile = new File(fileName);
	    if (newFile.length() == 0) {
		String line = delivery.toString();
		file.append(line);
		file.close();
	    } else {
		String newLine = "\n";
		String line = delivery.toString();
		file.append(newLine);
		file.append(line);
		file.close();
	    }

	} catch (FileNotFoundException fnfe) {
	    System.out.println("The file could not be saved.");
	} catch (IOException ioe) {
	    new RuntimeException("I/O Error.");
	}
    }
}
