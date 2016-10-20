package edu.ncsu.csc540.dao.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.ncsu.csc540.dao.JournalDao;
import edu.ncsu.csc540.model.Books;
import edu.ncsu.csc540.model.ConferenceProceedings;
import edu.ncsu.csc540.model.Faculty;
import edu.ncsu.csc540.model.Journals;
import edu.ncsu.csc540.model.Patrons;
import edu.ncsu.csc540.model.RentalHistory;
import edu.ncsu.csc540.model.WaitListPublications;

@Repository("journalDao")
public class JournalDaoImpl implements JournalDao {

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	
	HashMap<String, Queue<String>> map = new HashMap<String, Queue<String>>();

	@Override
	public List<Journals> getAllJournals() {
		String sql = "SELECT * FROM Journals j";

		jdbcTemplate = new JdbcTemplate(dataSource);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Journals> journal = (List<Journals>) jdbcTemplate.query(sql,
				new Object[] {}, new BeanPropertyRowMapper(Journals.class));

		return journal;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean checkoutJournals(Patrons patron, int resource_id, String checked_outDateTime, String dueDateTime) {
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

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean checkifAvailable(String issn, String unityId) {
		int journal_res_id = 0;
		Journals journals = null;
		boolean flag = true;
		
		String sql1 = "SELECT j.resource_id FROM journals j where j.issn = ?";
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		List copies =  jdbcTemplate.query(sql1,
				new Object[] {issn}, new BeanPropertyRowMapper(Journals.class));
		
		if(!copies.isEmpty())
		{
			journals = (Journals)copies.get(0);
			journal_res_id = journals.getResource_id();
			
			String sql2 = "SELECT * FROM Rental_History r where r.Resource_id = ?";
			jdbcTemplate = new JdbcTemplate(dataSource);
			List rented_journal_copies =  jdbcTemplate.query(sql1,
					new Object[] {journal_res_id}, new BeanPropertyRowMapper(Journals.class));
			if(!rented_journal_copies.isEmpty())
			{
				flag = false;
			}
			
		}
		return flag;
	}

	@Override
	public int checkinJournal(int resource_id, Patrons p) {
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
	public int renewJournal(int resource_id, String unityid, Date renewalDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String todayDate = format.format(renewalDate);
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "UPDATE Rental_History set due_date = TO_DATE(?,'yyyy-mm-dd hh24:mi:ss') where resource_id = ? and unityId = ?";
		
		return jdbcTemplate.update(sql,todayDate,resource_id,unityid);
		
	}
	


}
