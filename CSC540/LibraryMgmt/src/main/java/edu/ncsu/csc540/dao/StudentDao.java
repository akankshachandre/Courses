package edu.ncsu.csc540.dao;

import java.util.List;

import edu.ncsu.csc540.model.Patrons;
import edu.ncsu.csc540.model.Students;

public interface StudentDao {
	List<Students> getStudent(String std_unityId);

	boolean isStudent(String unityId);

	void updateStudent(Students s, Patrons p);
}
