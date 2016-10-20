package edu.ncsu.csc540.dao;

import java.util.List;


import edu.ncsu.csc540.model.LateFees;
import edu.ncsu.csc540.model.Notifications;
import edu.ncsu.csc540.model.Books;
import edu.ncsu.csc540.model.Cameras;
import edu.ncsu.csc540.model.ConferenceProceedings;
import edu.ncsu.csc540.model.Journals;

import edu.ncsu.csc540.model.Patrons;
import edu.ncsu.csc540.model.RentalHistory;
import edu.ncsu.csc540.model.RequestedResource;
import edu.ncsu.csc540.model.Rooms;

public interface PatronDao {
	Patrons getPatron(String patronId);

	Patrons loginSuccsessful(String unityId, String password);
	
	List<RentalHistory> getCheckedOutResources(Patrons patron);
	
	List<Notifications> getNotifications(Patrons patron);

	List<LateFees> calculateLateFee(Patrons patrons);
	
	boolean clearDues(Patrons patrons);
	
	boolean renewResource(int resource_id);

	char mapResource(int resource_id);
	
	List<Books> getBook (int resource_id);
	
	List<Cameras> getCamera(int resource_id);
	
	List<Journals> getJournal(int resource_id);
	
	List<ConferenceProceedings> getConfProc(int resource_id);
	
	List<Rooms> getRoom (int resource_id);
	
	List<RequestedResource> getRequestedResources(Patrons patrons);

}
