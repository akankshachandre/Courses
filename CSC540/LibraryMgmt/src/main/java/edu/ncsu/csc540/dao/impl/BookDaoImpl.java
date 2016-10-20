package edu.ncsu.csc540.dao.impl;


import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Queue;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.ncsu.csc540.dao.BookDao;
import edu.ncsu.csc540.dao.StudentDao;
import edu.ncsu.csc540.model.Books;
import edu.ncsu.csc540.model.Courses;
import edu.ncsu.csc540.model.Faculty;
import edu.ncsu.csc540.model.Patrons;
import edu.ncsu.csc540.model.RentalHistory;
import edu.ncsu.csc540.model.ReservedBooks;
import edu.ncsu.csc540.model.Students;
import edu.ncsu.csc540.model.WaitListPublications;



@Repository("bookDao")
public class BookDaoImpl implements BookDao {
	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	
	StudentDao studentDao;
	
	HashMap<String, Queue<String>> map = new HashMap<String, Queue<String>>();
	

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Books> getAllBooks() {
		String sql = "SELECT * FROM Books b where b.type = 'Physical copy'";

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Books> books = (List<Books>) jdbcTemplate.query(sql,
				new Object[] {}, new BeanPropertyRowMapper(Books.class));

		return books;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean checkifAvailable(String isbn,String unityId) {
		int book_copies = 0;
		Books book = null;
		boolean flag = false;
		RentalHistory history = null;
		
		Date checkedOut = new Date();
		 Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String checkedOutDate = format.format(checkedOut);
			try {
				checkedOut = ((DateFormat) format).parse(checkedOutDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		String sql1 = "SELECT b.copies FROM Books b where b.isbn = ?";
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		List copies =  jdbcTemplate.query(sql1,
				new Object[] {isbn}, new BeanPropertyRowMapper(Books.class));
		if(!copies.isEmpty())
		{
			book = (Books)copies.get(0);
			book_copies = book.getCopies();
			
			if(book_copies>0){
				flag = true;
					
			}
			else{
				if(book_copies==0){
					String sql2 = "SELECT * FROM RENTAL_HISTORY where resource_id = ?";
					List rentHistory = jdbcTemplate.query(sql2,
							new Object[] {book.getResourceId()}, new BeanPropertyRowMapper(RentalHistory.class));
					if(!rentHistory.isEmpty()){
						history = (RentalHistory) rentHistory.get(0);
						if(history.getChecked_in().before(checkedOut)){
							String sql4 = "SELECT * FROM WAITLIST_PUBLICATIONS where resource_id = ?";
							List waitList = jdbcTemplate.query(sql4,
									new Object[] {book.getResourceId()}, new BeanPropertyRowMapper(WaitListPublications.class));
							if(waitList.isEmpty()){
							
							//if waitlist is not empty check the queue and send the notification to user
							String sql3 = "UPDATE Books SET copies = ?";
							jdbcTemplate.update(sql3,(book_copies+1));
							flag = true;
							}
									
						}
						
					}
					
				}
			}
			
	}
		
		return flag;

		
	}

	 @Override
	public int addToWaitList(int res_id,String unityId,Date checkedOutDate) {
		 int waitListed = 0;
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String checkedOut = format.format(checkedOutDate);
		 
		 jdbcTemplate = new JdbcTemplate(dataSource);
		 String sql1 = "SELECT * FROM WAITLIST_PUBLICATIONS where resource_id = ? and unityId = ?";
		 List<WaitListPublications> waitList = jdbcTemplate.query(sql1,
					new Object[] {new Integer(res_id),unityId}, new BeanPropertyRowMapper<WaitListPublications>(WaitListPublications.class));
		 if(waitList.isEmpty()){
			 String sql2 = "INSERT INTO WAITLIST_PUBLICATIONS(resource_id,unityId,entered_date) values(?,?,TO_DATE(?,'yyyy-mm-dd hh24:mi:ss'))";
			 waitListed = jdbcTemplate.update(sql2, res_id,unityId,checkedOut);
			 }
		 return waitListed;
			 
		
	}

	@Override
	public boolean validateReturnDate(Date checkoutDate,Date checkinDate,Patrons p) {
		boolean flag = false;
		Date today = new Date();
		
		List<Students> students = null;
		
		if(checkinDate.before(checkoutDate)||today.after(checkoutDate)||today.after(checkinDate))
			return false;
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		
		studentDao = (StudentDao) context.getBean("studentDao");
		
		long milliseconds_1 = checkinDate.getTime();
		long milliseconds_2 = checkoutDate.getTime();
		long diffInMilis = milliseconds_1 - milliseconds_2;
		
		
		long diffInMinute = diffInMilis / (60 * 1000);
		long diffInHour = diffInMilis / (60 * 60 * 1000);
		long diffInDays = diffInMilis / (24 * 60 * 60 * 1000);
		
		students = studentDao.getStudent(p.getUnityid());
		
		
		if(!students.isEmpty()){

		if(diffInDays==14){
			if(diffInHour==336){
				if(diffInMinute==20160){
					flag = true;
				}
			}
			
		}
		else
			if(diffInDays<14)
				flag = true;
		}
		else{
			if(diffInDays==30){
				if(diffInHour==720){
					if(diffInMinute==43200){
						flag = true;
					}
				}
				
			}
			else
				if(diffInDays<30)
					flag = true;
			
		}
		
		return flag;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean checkoutBook(Date checkedOut,Date checkedIn,int res_id, String unityId) {
		int copies = 0;
		Books book = null;
		boolean checkOut = true;
		RentalHistory rental_history = null;
		
		Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String checkedOutDate = format.format(checkedOut);
		String checkedInDate = format.format(checkedIn);
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "SELECT * FROM RENTAL_HISTORY WHERE RESOURCE_ID = ? AND UNITYID = ?";
		List history = jdbcTemplate.query(sql,
				new Object[] {res_id,unityId}, new BeanPropertyRowMapper(RentalHistory.class));
		if(!history.isEmpty()){
			for(int i = 0 ;i<=history.size()-1;i++){
				rental_history = (RentalHistory) history.get(i);
				if(rental_history.getChecked_in()==null)
					{
					checkOut = false;
					break;
					}
			}
			
		}
		
	    if(checkOut){
		String sql1 = "INSERT INTO RENTAL_HISTORY (checked_out,due_date,resource_id,unityId) values(TO_DATE(?,'yyyy-MM-dd hh24:mi:ss'),TO_DATE(?,'yyyy-MM-dd hh24:mi:ss'),?,?)"; 
		jdbcTemplate.update(sql1,checkedOutDate,checkedInDate,res_id,unityId);
        String sql2 = "SELECT * FROM BOOKS WHERE RESOURCE_ID = ? ";
        
        
		List books =  jdbcTemplate.query(sql2,
				new Object[] {res_id}, new BeanPropertyRowMapper(Books.class));
		if(!books.isEmpty())
		{
			book = (Books) books.get(0);
			copies = book.getCopies() - 1;
		String sql3 = "UPDATE BOOKS SET copies = ? where resource_id = ?";
		jdbcTemplate.update(sql3, new Integer(copies),new Integer(res_id));
		
			
		}
	    }	
		
		return checkOut;
		
	}

	@Override
	 public boolean checkifEnrolled(String unityId, int courseId) {
		List<Courses> enrolled = null;
		boolean flag = false;
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * from enrolled_courses where cid = ? and std_unityId = ?";
		enrolled = jdbcTemplate.query(sql,
				new Object[] {new Integer(courseId),unityId}, new BeanPropertyRowMapper<Courses>(Courses.class));
		if(!enrolled.isEmpty())
			flag = true;
		return flag;
	}

	

@Override
public int checkifReserved(int res_id) {
	
	jdbcTemplate = new JdbcTemplate(dataSource);
	ReservedBooks reserved = null;
	
	int courseId = 0;
	String sql = "SELECT * FROM RESERVED_BOOKS WHERE RESOURCE_ID = ?";
	List<ReservedBooks> reservedBooks = jdbcTemplate.query(sql,
			new Object[] {res_id}, new BeanPropertyRowMapper<ReservedBooks>(ReservedBooks.class));
	if(!reservedBooks.isEmpty()){
		reserved = reservedBooks.get(0);
		
		courseId = reserved.getCid();
	}
	return courseId;
}

@Override
public boolean validateReturnDateReserved(Date checkedOut, Date checkedIn) {
	boolean flag = false;
	
	long milliseconds_1 = checkedIn.getTime();
	long milliseconds_2 = checkedOut.getTime();
	long diffInMilis = milliseconds_1 - milliseconds_2;
	
	
	long diffInMinute = diffInMilis / (60 * 1000);
	long diffInHour = diffInMilis / (60 * 60 * 1000);
	long diffInDays = diffInMilis / (24 * 60 * 60 * 1000);
	if(diffInDays==0){
		if(diffInHour<=4){
			
				flag = true;
			
			
		}
	}
	return flag;
}

@Override
public int checkinBook(int resource_id, Patrons p) {
	jdbcTemplate = new JdbcTemplate(dataSource);
	WaitListPublications wait = null;
	int checkin = 0;
	
	Date today = new Date();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	String todayDate = format.format(today);
	
	
	String sql = "SELECT * FROM RENTAL_HISTORY WHERE resource_id = ? and unityId = ? and checked_in IS NULL";
	List<RentalHistory> rental = (List<RentalHistory>)jdbcTemplate.query(sql,new Object[] {new Integer(resource_id),p.getUnityid()}, new BeanPropertyRowMapper<RentalHistory>(RentalHistory.class));
	if(!rental.isEmpty()){
	
	sql = "UPDATE RENTAL_HISTORY set checked_in = TO_DATE(?,'yyyy-mm-dd hh24:mi:ss') where resource_id = ? and unityId = ?";
	checkin = jdbcTemplate.update(sql,todayDate,resource_id,p.getUnityid());
	
	sql = "UPDATE books set copies = (copies + 1) where resource_id = ?";
	jdbcTemplate.update(sql, resource_id);
	
	sql = "SELECT * FROM WAITLIST_PUBLICATIONS where resource_id = ? order by entered_date";
	List<WaitListPublications> waitlist = (List<WaitListPublications>)jdbcTemplate.query(sql,new Object[] {new Integer(resource_id)}, new BeanPropertyRowMapper<WaitListPublications>(WaitListPublications.class));
	if(!waitlist.isEmpty()){
		for(int i = 0;i<waitlist.size();i++){
			wait = waitlist.get(i);
			sql = "SELECT * from Faculty where unityId = ?";
			List<Faculty> faculty = (List<Faculty>)jdbcTemplate.query(sql,new Object[] {wait.getUnityId()}, new BeanPropertyRowMapper<Faculty>(Faculty.class));
			String message = "The item you requested is now available to check-out";
			if(!faculty.isEmpty()){
				String sqlNotify = "INSERT INTO Notifications Values(?,?,?,sysdate)";
				jdbcTemplate.update(sqlNotify,resource_id, faculty.get(0).getFacUnityid(),message);
			}
			else{
				String sqlNotify = "INSERT INTO Notifications Values(?,?,?,sysdate)";
				jdbcTemplate.update(sqlNotify,resource_id, waitlist.get(0).getUnityId(),message);
			}
		}
	}
	
}
	return checkin;
}

@Override
public int renewBook(int resource_id, String unityid, Date renewalDate) {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String renewDate = format.format(renewalDate);
	
	jdbcTemplate = new JdbcTemplate(dataSource);
	String sql = "UPDATE Rental_History set due_date = TO_DATE(?,'yyyy-mm-dd hh24:mi:ss') where resource_id = ? and unityId = ?";
	
	return jdbcTemplate.update(sql,renewDate,resource_id,unityid);
}
}
