package edu.ncsu.csc540.dao.impl;

import java.sql.Date;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.ncsu.csc540.dao.CheckoutDao;
import edu.ncsu.csc540.model.Patrons;
import edu.ncsu.csc540.model.RentalHistory;
import edu.ncsu.csc540.model.Rooms;

@Repository("checkoutDao")
public class CheckoutDaoImpl implements CheckoutDao {

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean checkoutRoom(Patrons patron, int resource_id, String check_out, String check_in) {
		// TODO Auto-generated method stub
		String sqlCheck = "SELECT * FROM rental_history rh WHERE rh.resource_id = ? AND (TO_DATE(?,'mm/dd/yyyy hh24:mi:ss') between rh.checked_out AND rh.due_date OR TO_DATE(?,'mm/dd/yyyy hh24:mi:ss') between rh.checked_out AND rh.due_date)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<RentalHistory> check = (List<RentalHistory>) jdbcTemplate.query(sqlCheck,
				new Object[] {(Integer)resource_id, check_out, check_in}, new BeanPropertyRowMapper(RentalHistory.class));
		if(check.isEmpty()){
			String sql = "INSERT INTO Rental_History (checked_out, due_date, Resource_id, unityId) VALUES (TO_DATE(?,'mm/dd/yyyy hh24:mi:ss'), TO_DATE(?,'mm/dd/yyyy hh24:mi:ss'), ?, ?)";
			jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sql,check_out, check_in, resource_id, patron.getUnityid());
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void checkoutBooks(String name, String type, String publisher,
			Patrons patron) {
		// TODO Auto-generated method stub

	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void checkoutJournals(String name, String type, String publisher,
			Patrons patron) {
		// TODO Auto-generated method stub

	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void checkoutConfProcedings(String name, String type,
			String[] authors, Patrons patron) {
		// TODO Auto-generated method stub

	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String occupyRoom(Patrons patron, String room_number, java.util.Date occupyTime) {
		// TODO Auto-generated method stub
		String sqlResID = "SELECT res_id FROM Rooms WHERE room_number = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		Integer resource_id = (Integer)jdbcTemplate.queryForObject(sqlResID,
				new Object[] {room_number}, Integer.class);
		if(resource_id == null)
			return "No room found. Enter a valid room number";
		String sqlCheck = "SELECT * FROM rental_history WHERE unityid = ? AND resource_id = ? AND checked_in IS NULL";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<RentalHistory> check = (List<RentalHistory>) jdbcTemplate.query(sqlCheck,
				new Object[] {patron.getUnityid(),(Integer)resource_id}, new BeanPropertyRowMapper(RentalHistory.class));
		if(!check.isEmpty()){
			String sqlCheckedOut = "SELECT checked_out + 60/1440 FROM rental_history WHERE resource_id = ? AND unityid = ?";
			jdbcTemplate = new JdbcTemplate(dataSource);
			String check_out = (String)jdbcTemplate.queryForObject(sqlCheckedOut,
					new Object[] {(Integer)resource_id, patron.getUnityid()}, String.class);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date checked_out = null;
			check_out = check_out.substring(0, check_out.length() - 2);
			try {
				checked_out = sdf.parse(check_out);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int compare = occupyTime.compareTo(checked_out);
			if(compare <= 0){
				Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String checked_in = formatter.format(occupyTime);
		        String sqlUpdate = "UPDATE rental_history SET checked_in = TO_DATE(?, 'yyyy-MM-dd hh24:mi:ss') WHERE resource_id = ? AND unityid = ?";
		        jdbcTemplate = new JdbcTemplate(dataSource);
				jdbcTemplate.update(sqlUpdate, checked_in, resource_id, patron.getUnityid());
				return "Operation successful";
			}
			else {
				return "You have exceeded time limit to occupy the room";
			}
		}
		else {
			return "No reservations found for given room by you";
		}
	}
}
