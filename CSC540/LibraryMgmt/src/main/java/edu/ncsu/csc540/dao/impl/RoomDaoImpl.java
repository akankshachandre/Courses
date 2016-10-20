package edu.ncsu.csc540.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.ncsu.csc540.dao.RoomDao;
import edu.ncsu.csc540.model.Rooms;

@Repository("roomDao")
public class RoomDaoImpl implements RoomDao {

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Rooms> getAllRooms(String library, int capacity, char patronType) {
		String sql = null;
		if(patronType == 'f'){
			sql = "SELECT r.room_number, r.capacity, r.floor, r.type, r.res_id FROM Rooms r, Resources rs WHERE r.res_id = rs.resource_id AND r.capacity >= ? AND rs.host_library = ?";
		}
		else {
			sql = "SELECT r.room_number, r.capacity, r.floor, r.type, r.res_id FROM Rooms r, Resources rs WHERE r.res_id = rs.resource_id AND r.capacity >= ? AND rs.host_library = ? AND r.type = 'Study Room'";
		}
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Rooms> rooms = (List<Rooms>) jdbcTemplate.query(sql,
				new Object[] {(Integer)capacity, library}, new BeanPropertyRowMapper(Rooms.class));

		return rooms;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<String> getRoomNumbers() {
		// TODO Auto-generated method stub
		String sqlRoomNum = "SELECT room_number from rooms";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<String> room_numbers = (List<String>)jdbcTemplate.queryForList(sqlRoomNum, String.class);
		return room_numbers;
	}
	
	

}
