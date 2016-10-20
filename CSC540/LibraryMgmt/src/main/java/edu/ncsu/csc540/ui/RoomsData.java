package edu.ncsu.csc540.ui;

import java.util.List;

import edu.ncsu.csc540.model.Rooms;;

public class RoomsData {
	public static Object[][] getRoomData(List<Rooms> rooms){
		int n = rooms.size();
		Object roomsData[][] = new Object[n][5];
		if(!rooms.isEmpty()){
			for(int i = 0; i < n; i++) {
				roomsData[i][0] = ((Rooms)rooms.get(i)).getRoomNumber();
				roomsData[i][1] = ((Rooms)rooms.get(i)).getCapacity();
				roomsData[i][2] = ((Rooms)rooms.get(i)).getFloor();
				roomsData[i][3] = ((Rooms)rooms.get(i)).getType();
				roomsData[i][4] = ((Rooms)rooms.get(i)).getRes_id();
			}
		}
		return roomsData;
	}
}
