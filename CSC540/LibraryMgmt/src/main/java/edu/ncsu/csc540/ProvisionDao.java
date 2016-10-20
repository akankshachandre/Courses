package edu.ncsu.csc540;

import edu.ncsu.csc540.model.Patrons;

public interface ProvisionDao {
	public void resetDatabase();

	public void checkCourses(Patrons patron);
}
