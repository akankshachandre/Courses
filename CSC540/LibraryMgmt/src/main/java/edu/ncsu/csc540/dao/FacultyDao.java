package edu.ncsu.csc540.dao;

import java.util.Date;
import java.util.List;

import edu.ncsu.csc540.model.Faculty;
import edu.ncsu.csc540.model.Patrons;

public interface FacultyDao {
	List<Faculty> getFaculty(String fac_unityId);

	void updateFaculty(Patrons p);

	boolean checkifValidReservation(Date reservedStartDate, Date reservedEndDate);

	boolean reserveBook(int book_ResourceID, Patrons patrons,Date reservedStartDate, Date reservedEndDate);
}
