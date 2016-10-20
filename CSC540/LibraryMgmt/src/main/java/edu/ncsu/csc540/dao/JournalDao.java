package edu.ncsu.csc540.dao;

import java.util.Date;
import java.util.List;

import edu.ncsu.csc540.model.Journals;
import edu.ncsu.csc540.model.Patrons;

public interface JournalDao {
    List<Journals> getAllJournals();
	
	public boolean checkifAvailable(String issn,String unityId);

	boolean checkoutJournals(Patrons patron, int resource_id, String checked_outDateTime, String dueDateTime);
	
	int addToWaitList(int res_id, String unityId, String check_outDateTime);

	int checkinJournal(int resource_id, Patrons p);

	int renewJournal(int resource_id, String unityid, Date renewalDate);

}
