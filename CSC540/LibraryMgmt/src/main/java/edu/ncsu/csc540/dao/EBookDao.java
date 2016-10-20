package edu.ncsu.csc540.dao;

import java.util.List;
import edu.ncsu.csc540.model.Books;
import edu.ncsu.csc540.model.Patrons;

public interface EBookDao {
	List<Books> getAllEBooks();
	
	boolean checkoutEBook(Patrons patron, int resource_id, String checked_outDateTime, String dueDateTime);

}
