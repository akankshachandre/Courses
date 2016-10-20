package edu.ncsu.csc540.dao;

import java.util.Date;
import java.util.List;

import edu.ncsu.csc540.model.Cameras;
import edu.ncsu.csc540.model.Patrons;

public interface CameraDao {

	List<Cameras> getAllCameras();

	String requestCamera(int resourceId, Patrons patron, Date pickUpDate);

	String checkoutCamera(int resourceId, Patrons patron);

	Integer getRequestPosition(int resourceId, Patrons patron,
			Date pickUpDate);

	int checkinCamera(int resource_id, Patrons p);

}
