package edu.ncsu.csc540.dao;

import java.util.Date;
import java.util.List;

import edu.ncsu.csc540.model.ConferenceProceedings;
import edu.ncsu.csc540.model.Patrons;

public interface ConferenceProceedingDao {
	List<ConferenceProceedings> getAllConfProceedings();

	public boolean checkifAvailable(String ConfNum,String unityId);
	
	boolean checkoutConfProceeding(Patrons patron, int resource_id, String checked_outDateTime, String dueDateTime);
	
	int addToWaitList(int resource_id ,String unityId, String check_outDateTime);
	
	int renewConfProceedings(Patrons patron, int resource_id, Date current);

	int checkinConfProc(int resource_id, Patrons p);

	int renewConfProc(Integer resource_id_1, String unityid, Date renewalDate);

}
