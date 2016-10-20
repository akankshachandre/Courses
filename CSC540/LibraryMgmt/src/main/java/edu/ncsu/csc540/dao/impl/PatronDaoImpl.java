package edu.ncsu.csc540.dao.impl;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.ncsu.csc540.dao.PatronDao;
import edu.ncsu.csc540.dao.StudentDao;
import edu.ncsu.csc540.model.LateFees;
import edu.ncsu.csc540.model.Notifications;
import edu.ncsu.csc540.model.Books;
import edu.ncsu.csc540.model.Cameras;
import edu.ncsu.csc540.model.ConferenceProceedings;
import edu.ncsu.csc540.model.Journals;
import edu.ncsu.csc540.model.Patrons;
import edu.ncsu.csc540.model.RentalHistory;
import edu.ncsu.csc540.model.RequestedResource;
import edu.ncsu.csc540.model.Resources;
import edu.ncsu.csc540.model.WaitListPublications;
import edu.ncsu.csc540.model.Rooms;


@Repository("patronDao")
public class PatronDaoImpl implements PatronDao {

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	private String sql1;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Patrons getPatron(String patronId) {
		String sql = "SELECT * FROM Patrons WHERE unityid = ?";

		jdbcTemplate = new JdbcTemplate(dataSource);
		Patrons patron = (Patrons) jdbcTemplate.queryForObject(sql,
				new Object[] { patronId }, new BeanPropertyRowMapper(
						Patrons.class));

		return patron;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Patrons loginSuccsessful(String unityId, String password) {
		String sql = "SELECT * FROM Patrons WHERE unityid = ? and password = ?";

		Patrons patron = null;
		jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			patron = (Patrons) jdbcTemplate.queryForObject(sql, new Object[] {
					unityId, password }, new BeanPropertyRowMapper(
					Patrons.class));
		} catch (EmptyResultDataAccessException e) {
		}
		return patron;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<RentalHistory> getCheckedOutResources(Patrons patron) {
		// TODO Auto-generated method stub
		String sql = "SELECT resource_id, checked_out, due_date FROM rental_history WHERE unityid = ? AND checked_in IS NULL";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<RentalHistory> CCResources = (List<RentalHistory>) jdbcTemplate.query(sql,
				new Object[] {patron.getUnityid()}, new BeanPropertyRowMapper(RentalHistory.class));
		return CCResources;
	}

	@Override
	public List<LateFees> calculateLateFee(Patrons patrons) {
		Date today = new Date();
		String sql2 = null;
		int fees = 0;
		RentalHistory lateHistory = null;

		LateFees lateFees = null;
		List<LateFees> lateFeesList = new ArrayList<LateFees>();

		String sql = "SELECT * FROM RENTAL_HISTORY where ((checked_in > due_date)  OR (checked_in IS NULL)) and TO_DATE(sysdate) > due_date and unityId = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<RentalHistory> lateResources = jdbcTemplate.query(sql,
				new Object[] { patrons.getUnityid() },
				new BeanPropertyRowMapper<RentalHistory>(RentalHistory.class));
		
		if (!lateResources.isEmpty()) {
			for (int i = 0; i < lateResources.size(); i++) {
				lateHistory = (RentalHistory) lateResources.get(i);
				if (lateHistory.getChecked_in() != null) {
					if (resourceIsCamera(lateHistory.getResource_id())) {
						fees = (int) (diffDatesHours(today,
								lateHistory.getChecked_in()));
					} else {
						fees = (int) (2 * diffDates(today,
								lateHistory.getChecked_in()));
					}
				} else {
					if (resourceIsCamera(lateHistory.getResource_id())) {
						fees = (int) (diffDatesHours(today,
								lateHistory.getDue_date()));
					} else {
						fees = (int) (2 * diffDates(today,
								lateHistory.getDue_date()));
					}
				}

				lateFees = new LateFees();
				lateFees.setFees(fees);
				lateFees.setUnityId(patrons.getUnityid());
				lateFees.setResource_Id(lateHistory.getResource_id());
				lateFeesList.add(lateFees);

				sql1 = "DELETE FROM LateFees where unityId = ?";
				jdbcTemplate.update(sql1, patrons.getUnityid());
				sql2 = "INSERT INTO LateFees(unityId,resource_id,fees) values(?,?,?)";
				jdbcTemplate.update(sql2, patrons.getUnityid(),
						lateHistory.getResource_id(), fees);
			}
		}

		return lateFeesList;

	}
	
	private boolean resourceIsCamera(Integer resourceId) {
		if (resourceId == null) {
			return false;
		}
		String sql = "select type from RESOURCES WHERE RESOURCE_ID = ?";

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<String> strings = jdbcTemplate.queryForList(sql,
				new Object[] { resourceId }, String.class);
		if (strings != null && strings.size() > 0
				&& strings.get(0).equals("Camera")) {
			return true;
		}
		return false;
	}

	private long diffDates(Date checkinDate,Date checkoutDate){
		long milliseconds_1 = checkinDate.getTime();
		long milliseconds_2 = checkoutDate.getTime();
		long diffInMilis = milliseconds_1 - milliseconds_2;
		
		
		
		long diffInDays = diffInMilis / (24 * 60 * 60 * 1000);
		
		return diffInDays;
		
	}

	
	private long diffDatesHours(Date checkinDate, Date checkoutDate) {
		long milliseconds_1 = checkinDate.getTime();
		long milliseconds_2 = checkoutDate.getTime();
		long diffInMilis = milliseconds_1 - milliseconds_2;
		long diffInHours = diffInMilis / (60 * 60 * 1000);
		return diffInHours;
	}

	@Override
	public boolean clearDues(Patrons patrons) {
		boolean clearDues = false;
		String checkUnReturned = "SELECT * FROM RENTAL_HISTORY where checked_in IS NULL and unityId = ?";
		List checkUnReturnedItems = jdbcTemplate.query(checkUnReturned,
				new Object[] {patrons.getUnityid()}, new BeanPropertyRowMapper<RentalHistory>(RentalHistory.class));
		if(checkUnReturnedItems.isEmpty()){
			clearDues = true;
		}
		
		
		String insertSql = "INSERT INTO paid_latefees (UNITYID, RESOURCE_ID, FEES, TRANSACTION_DATE) "
		+ "select UNITYID, RESOURCE_ID, FEES, sysdate from latefees where unityid = ?";
		jdbcTemplate.update(insertSql,patrons.getUnityid());
		
		String sql1 = "DELETE FROM LateFees where unityId = ?";
		jdbcTemplate.update(sql1,patrons.getUnityid());
		return clearDues;
	}

	@Override
	public boolean renewResource(int resource_id) {
		boolean renew = true;
		String sql = "SELECT * from Resources where resource_id = ?";
		Resources resource = null;
		List<Resources> resources = jdbcTemplate.query(sql,
				new Object[] {new Integer(resource_id)}, new BeanPropertyRowMapper<Resources>(Resources.class));
		
		
		resource = resources.get(0);
		if("Camera".equalsIgnoreCase(resource.getType())){
			renew = false;
		}
		else{
			String sql2 = "SELECT * FROM WAITLIST_PUBLICATIONS where resource_id = ?";
			List<WaitListPublications> waitlist = jdbcTemplate.query(sql2,
					new Object[] {new Integer(resource_id)}, new BeanPropertyRowMapper<WaitListPublications>(WaitListPublications.class));
			if(!waitlist.isEmpty()){
				renew = false;
			}
		}
		return renew;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public char mapResource(int resource_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT type FROM resources WHERE resource_id = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		String resource_type = (String)jdbcTemplate.queryForObject(sql,
				new Object[] {resource_id}, String.class);
		if(resource_type == "Conference Proceeding")
			return 'P';
		if(resource_type == "Book"){
			String sqlEbook = "SELECT type from books where resource_id = ?";
			jdbcTemplate = new JdbcTemplate(dataSource);
			String type = (String)jdbcTemplate.queryForObject(sqlEbook, new Object[] {(Integer)resource_id},String.class);
			if(type == "Electronic Copy")
				return 'E';
		}			
		return resource_type.charAt(0);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Books> getBook(int resource_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM books WHERE resource_id = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Books> book = (List<Books>) jdbcTemplate.query(sql,
				new Object[] {(Integer)resource_id}, new BeanPropertyRowMapper(Books.class));
		return book;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Cameras> getCamera(int resource_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM cameras WHERE resource_id = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Cameras> camera = (List<Cameras>) jdbcTemplate.query(sql,
				new Object[] {(Integer)resource_id}, new BeanPropertyRowMapper(Cameras.class));
		return camera;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Journals> getJournal(int resource_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM journals WHERE resource_id = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Journals> journal = (List<Journals>) jdbcTemplate.query(sql,
				new Object[] {(Integer)resource_id}, new BeanPropertyRowMapper(Journals.class));
		return journal;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ConferenceProceedings> getConfProc(int resource_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM conference_proceedings WHERE resource_id = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<ConferenceProceedings> confproc = (List<ConferenceProceedings>) jdbcTemplate.query(sql,
				new Object[] {(Integer)resource_id}, new BeanPropertyRowMapper(ConferenceProceedings.class));
		return confproc;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Rooms> getRoom(int resource_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM rooms WHERE res_id = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Rooms> room = (List<Rooms>) jdbcTemplate.query(sql,
				new Object[] {(Integer)resource_id}, new BeanPropertyRowMapper(Rooms.class));
		return room;

	}

	@Override
	public List<RequestedResource> getRequestedResources(Patrons patrons) {
		String requestedResources = "Select * from waitlist_publications where unityId = ?";
		WaitListPublications waitlist = null;
		Resources resource = null;
		Books book = null;
		ConferenceProceedings conf = null;
		Journals journal = null;
		
		List<RequestedResource> reqResources = new ArrayList<RequestedResource>();
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<WaitListPublications> requests = (List<WaitListPublications>) jdbcTemplate.query(requestedResources,
				new Object[] {patrons.getUnityid()}, new BeanPropertyRowMapper<WaitListPublications>(WaitListPublications.class));
		
		if(!requests.isEmpty()){
			RequestedResource reqRes = new RequestedResource();
			for(int i = 0;i<requests.size();i++){
				waitlist = requests.get(i);
				String sql = "Select * from resources where resource_id = ?";
				List<Resources> resources = (List<Resources>) jdbcTemplate.query(sql,
						new Object[] {new Integer(waitlist.getResource_id())}, new BeanPropertyRowMapper<Resources>(Resources.class));
				if(!resources.isEmpty()){
					resource = resources.get(0);
					if("Book".equalsIgnoreCase(resource.getType())){
						reqRes.setType("Book");
						String sql2 = "Select * from books where resource_id = ?";
						List<Books> books = (List<Books>)jdbcTemplate.query(sql2,
								new Object[] {new Integer(resource.getResourceId())}, new BeanPropertyRowMapper<Books>(Books.class));
						if(!books.isEmpty()){
							book = (Books) books.get(0);
							reqRes.setTitle(book.getTitle());
							reqResources.add(reqRes);
							
						}
					}
					if("Conference Proceeding".equalsIgnoreCase(resource.getType())){
						reqRes.setType("Conference Proceeding");
						String sql2 = "Select * from conference_proceedings where resource_id = ?";
						List<ConferenceProceedings> confProcs = (List<ConferenceProceedings>)jdbcTemplate.query(sql2,
								new Object[] {new Integer(resource.getResourceId())}, new BeanPropertyRowMapper<ConferenceProceedings>(ConferenceProceedings.class));
						if(!confProcs.isEmpty()){
							conf = (ConferenceProceedings) confProcs.get(0);
							reqRes.setTitle(conf.getTitle());
							reqResources.add(reqRes);
							
						}
					}
					
					if("Journal".equalsIgnoreCase(resource.getType())){
						reqRes.setType("Conference Proceeding");
						String sql2 = "Select * from journals where resource_id = ?";
						List<Journals> journals = (List<Journals>)jdbcTemplate.query(sql2,
								new Object[] {new Integer(resource.getResourceId())}, new BeanPropertyRowMapper<Journals>(Journals.class));
						if(!journals.isEmpty()){
							journal = (Journals) journals.get(0);
							reqRes.setTitle(journal.getTitle());
							reqResources.add(reqRes);
							
						}
					}
					
				}
			}
		}
		return reqResources;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Notifications> getNotifications(Patrons patron) {
		// TODO Auto-generated method stub
		String sqlNotifs = "SELECT * from Notifications where patron_unityid = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Notifications> notifs = (List<Notifications>)jdbcTemplate.query(sqlNotifs,
				new Object[] {patron.getUnityid()}, new BeanPropertyRowMapper<Notifications>(Notifications.class));
		return notifs;
	}

}
