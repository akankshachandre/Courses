package edu.ncsu.csc540.dao;

import java.sql.Date;
import java.util.List;

import edu.ncsu.csc540.model.Books;
import edu.ncsu.csc540.model.Patrons;
import edu.ncsu.csc540.model.ReservedBooks;
import oracle.sql.DATE;

public interface BookDao {
	List<Books> getAllBooks();
	
	boolean checkifAvailable(String isbn,String unityId);
	
	int addToWaitList(int res_id,String unityId, java.util.Date checked_out);
	
	boolean validateReturnDate(java.util.Date checkedOut,java.util.Date checkedIn, Patrons p);
	
	boolean validateReturnDateReserved(java.util.Date checkedOut,java.util.Date checkedIn);
	
	boolean checkoutBook(java.util.Date checkedOut,java.util.Date checkedIn,int res_id,String unityId);

	
	int checkifReserved(int res_id);
	
	boolean checkifEnrolled(String unityId,int cid);

	int checkinBook(int resource_id, Patrons p);

	int renewBook(int resource_id, String unityid, java.util.Date renewalDate);
}
