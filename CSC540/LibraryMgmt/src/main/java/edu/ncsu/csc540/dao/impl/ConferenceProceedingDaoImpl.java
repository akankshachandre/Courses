package edu.ncsu.csc540.dao.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.ncsu.csc540.dao.ConferenceProceedingDao;
import edu.ncsu.csc540.model.Books;
import edu.ncsu.csc540.model.ConferenceProceedings;
import edu.ncsu.csc540.model.Faculty;
import edu.ncsu.csc540.model.Patrons;
import edu.ncsu.csc540.model.RentalHistory;
import edu.ncsu.csc540.model.WaitListPublications;

@Repository("confProcDao")
public class ConferenceProceedingDaoImpl implements ConferenceProceedingDao {

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	
	HashMap<String, Queue<String>> map = new HashMap<String, Queue<String>>();
	

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ConferenceProceedings> getAllConfProceedings() {
		String sql = "SELECT * FROM conference_proceedings c";

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<ConferenceProceedings> conf = (List<ConferenceProceedings>) jdbcTemplate.query(sql,
				new Object[] {}, new BeanPropertyRowMapper(ConferenceProceedings.class));

		return conf;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean checkifAvailable(String ConfNum,String unityId) {
		int conf_res_id = 0;
		ConferenceProceedings conf = null;
		boolean flag = true;
		
		String sql1 = "SELECT c.resource_id FROM conference_proceeding c where c.conf_num = ?";
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		List copies =  jdbcTemplate.query(sql1,
				new Object[] {ConfNum}, new BeanPropertyRowMapper(Books.class));
		
		if(!copies.isEmpty())
		{
			conf = (ConferenceProceedings)copies.get(0);
			conf_res_id = conf.getResource_id();
			
			String sql2 = "SELECT * FROM Rental_History r where r.Resource_id = ?";
			jdbcTemplate = new JdbcTemplate(dataSource);
			List rented_conf_copies =  jdbcTemplate.query(sql1,
					new Object[] {conf_res_id}, new BeanPropertyRowMapper(ConferenceProceedings.class));
			if(!rented_conf_copies.isEmpty())
			{
				flag = false;
			}
			
		}
		return flag;		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean checkoutConfProceeding(Patrons patron, int resource_id, String checked_outDateTime, String dueDateTime) {
		// TODO Auto-generated method stub
		String sqlCheck = "SELECT * FROM rental_history rh WHERE rh.resource_id = ? AND (TO_DATE(?,'mm/dd/yyyy hh24:mi:ss') between rh.checked_out AND rh.due_date OR TO_DATE(?,'mm/dd/yyyy hh24:mi:ss') between rh.checked_out AND rh.due_date)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<RentalHistory> check = (List<RentalHistory>) jdbcTemplate.query(sqlCheck,
				new Object[] {(Integer)resource_id, checked_outDateTime, dueDateTime}, new BeanPropertyRowMapper(RentalHistory.class));
		if(check.isEmpty()){
			String sql = "INSERT INTO Rental_History (checked_out, due_date, Resource_id, unityId) VALUES (TO_DATE(?,'mm/dd/yyyy hh24:mi:ss'), TO_DATE(?,'mm/dd/yyyy hh24:mi:ss'), ?, ?)";
			jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sql,checked_outDateTime, dueDateTime, resource_id, patron.getUnityid());
			return true;
		}
		else {
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int addToWaitList(int resource_id, String unityId, String check_outDateTime) {
		int waitListed = 0;
		String checkedOutDate = check_outDateTime;
		 
	    jdbcTemplate = new JdbcTemplate(dataSource);
		String sql1 = "SELECT * FROM WAITLIST_PUBLICATIONS where resource_id = ? and unityId = ?";
		List<WaitListPublications> waitList = jdbcTemplate.query(sql1,
					new Object[] {new Integer(resource_id),unityId}, new BeanPropertyRowMapper<WaitListPublications>(WaitListPublications.class));
		if(waitList.isEmpty()){
			String sql2 = "INSERT INTO WAITLIST_PUBLICATIONS(resource_id,unityId,entered_date) values(?,?,TO_DATE(?,'mm/dd/yyyy hh24:mi:ss'))";
			waitListed = jdbcTemplate.update(sql2, resource_id,unityId,checkedOutDate);
		}
		return waitListed;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int renewConfProceedings(Patrons patron, int resource_id, Date current)
	{
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "UPDATE RENTAL_HISTORY SET due_date = TO_DATE(?,'yyyy-MM-dd hh24:mi:ss') + 720/1440 where resource_id = ? and unityId = ?";
		int updatedRows = jdbcTemplate.update(sql,current, resource_id, patron.getUnityid());
		return updatedRows; 
	}

	@Override
	public int checkinConfProc(int resource_id, Patrons p) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		WaitListPublications wait = null;
		
		int checkin = 0;
		
		String sql = "SELECT * FROM RENTAL_HISTORY WHERE resource_id = ? and unityId = ? and checked_in IS NULL";
		List<RentalHistory> rental = (List<RentalHistory>)jdbcTemplate.query(sql,new Object[] {new Integer(resource_id),p.getUnityid()}, new BeanPropertyRowMapper<RentalHistory>(RentalHistory.class));
		if(!rental.isEmpty()){
		
		
		
		sql = "UPDATE RENTAL_HISTORY set checked_in = TO_DATE(sysdate) where resource_id = ? and unityId = ?";
		checkin = jdbcTemplate.update(sql, resource_id,p.getUnityid());
		
		
		sql = "SELECT * FROM WAITLIST_PUBLICATIONS where resource_id = ? order by entered_date";
		List<WaitListPublications> waitlist = (List<WaitListPublications>)jdbcTemplate.query(sql,new Object[] {new Integer(resource_id)}, new BeanPropertyRowMapper<WaitListPublications>(WaitListPublications.class));
		if(!waitlist.isEmpty()){
			for(int i = 0;i<waitlist.size();i++){
				wait = waitlist.get(i);
				sql = "SELECT * from Faculty where unityId = ?";
				List<Faculty> faculty = (List<Faculty>)jdbcTemplate.query(sql,new Object[] {wait.getUnityId()}, new BeanPropertyRowMapper<Faculty>(Faculty.class));
				if(!faculty.isEmpty()){
					//send notification to faculty
				}
				
				else{
					//send notification to waitlist.get(0)
					
				}
				
				
			}
		}
		
		}
		return checkin;
	}

	@Override
	public int renewConfProc(Integer resource_id_1, String unityid, Date renewalDate) {
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String todayDate = format.format(renewalDate);
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "UPDATE Rental_History set due_date = TO_DATE(?,'yyyy-mm-dd hh24:mi:ss') where resource_id = ? and unityId = ?";
		
		return jdbcTemplate.update(sql,todayDate,resource_id_1,unityid);
	}

	
}
