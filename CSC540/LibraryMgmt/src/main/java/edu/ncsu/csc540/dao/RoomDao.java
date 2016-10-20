package edu.ncsu.csc540.dao;

import java.util.List;

import edu.ncsu.csc540.model.Rooms;

public interface RoomDao {
	List<Rooms> getAllRooms(String library, int capacity, char patronType);
	
	List<String> getRoomNumbers();
}
