package edu.ncsu.csc540.dao;



import java.util.Date;

import edu.ncsu.csc540.model.Patrons;

public interface CheckoutDao {

	boolean checkoutRoom(Patrons patron, int resource_id, String check_out, String check_in);

	String occupyRoom(Patrons patron, String room_number, Date occupyTime);
	
	void checkoutBooks(String name, String type, String publisher,
			Patrons patron);

	void checkoutJournals(String name, String type, String publisher,
			Patrons patron);

	void checkoutConfProcedings(String name, String type, String[] authors,
			Patrons patron);
}
