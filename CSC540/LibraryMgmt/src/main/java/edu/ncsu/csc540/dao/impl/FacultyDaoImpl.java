package edu.ncsu.csc540.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.ncsu.csc540.dao.FacultyDao;
import edu.ncsu.csc540.model.Books;
import edu.ncsu.csc540.model.Courses;
import edu.ncsu.csc540.model.Faculty;
import edu.ncsu.csc540.model.Patrons;
import edu.ncsu.csc540.model.ReservedBooks;

@Repository("facultyDao")
public class FacultyDaoImpl implements FacultyDao {
	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Faculty> getFaculty(String fac_unityId) {
		String sql = "SELECT * FROM Faculty WHERE fac_unityid = ?";

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Faculty> faculty = (List<Faculty>) jdbcTemplate.query(sql,
				new Object[] { fac_unityId }, new BeanPropertyRowMapper(
						Faculty.class));

		return faculty;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateFaculty(Patrons p) {
		String sqlPatron = "UPDATE Patrons set nationality = ?, password = ? WHERE unityid = ?";

		jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sqlPatron,
				new Object[] { p.getNationality(), p.getPassword(),
						p.getUnityid() });
	}

	@Override
	public boolean checkifValidReservation(Date reservedStartDate, Date reservedEndDate) {
		boolean checkValidRes = false;
		
		long milliseconds_1 = reservedStartDate.getTime();
		long milliseconds_2 = reservedEndDate.getTime();
		
		long diffInMilis = milliseconds_2 - milliseconds_1;
		
		long diffInMinute = diffInMilis / (60 * 1000);
		long diffInHour = diffInMilis / (60 * 60 * 1000);
		long diffInDays = diffInMilis / (24 * 60 * 60 * 1000);	
		
		if(diffInDays<120){
			checkValidRes = true;
		}
		
		else
			if(diffInDays==120){
				if(diffInHour==2880)
					if(diffInMinute==172800)
						checkValidRes = true;
			}
			
		
		
		return checkValidRes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean reserveBook(int book_ResourceID, Patrons patrons, Date reservedStartDate, Date reservedEndDate) {
		boolean reserved = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDate = format.format(reservedStartDate);
		String endDate = format.format(reservedEndDate);
		
		
		String sql = "SELECT * FROM Books where resource_id = ?";
		Books book = null;
		Courses course = null;
		
		List<Books> books = jdbcTemplate.query(sql,
				new Object[] {new Integer(book_ResourceID)}, new BeanPropertyRowMapper(
						Books.class));
		book = (Books) books.get(0);
		
		sql = "SELECT * From Courses where book_name = ? and fac_unityid = ?";
		List<Courses> courses = jdbcTemplate.query(sql,
				new Object[] {book.getTitle(),patrons.getUnityid()}, new BeanPropertyRowMapper(
						Courses.class));
		if(!courses.isEmpty()){
		course = (Courses) courses.get(0);
		}
		else{
			return false;
		}
		
		
		
		
		sql = "INSERT INTO RESERVED_BOOKS "
				+ "(ISBN, RESOURCE_ID, FAC_UNITYID, CID, RESERVEDDURATIONFROM, RESERVEDDURATIONTO) "
				+ "VALUES(?, ?, ?, ?, TO_DATE(?,'yyyy-mm-dd hh24:mi:ss'), "
				+ "TO_DATE(?,'yyyy-mm-dd hh24:mi:ss'))";

		jdbcTemplate = new JdbcTemplate(dataSource);
		int rentalHistory = jdbcTemplate.update(sql, book.getIsbn(),
				new Integer(book_ResourceID), patrons.getUnityid(),
				new Integer(course.getCid()), startDate, endDate);
		if (rentalHistory != 0) {
			return true;
		}
		return false;
	}
}