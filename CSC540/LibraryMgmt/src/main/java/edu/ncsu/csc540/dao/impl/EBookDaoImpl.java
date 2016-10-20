package edu.ncsu.csc540.dao.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.ncsu.csc540.dao.EBookDao;
import edu.ncsu.csc540.model.Books;
import edu.ncsu.csc540.model.Patrons;
import edu.ncsu.csc540.model.RentalHistory;


@Repository("ebookDao")
public class EBookDaoImpl implements EBookDao {

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Books> getAllEBooks() {
		String sql = "SELECT * FROM Books b where b.type = 'Electronic copy'";

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Books> books = (List<Books>) jdbcTemplate.query(sql,
				new Object[] {}, new BeanPropertyRowMapper(Books.class));

		return books;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean checkoutEBook(Patrons patron, int resource_id, String checked_outDateTime, String dueDateTime) {
		String sqlCheck = "SELECT * FROM rental_history rh WHERE rh.resource_id = ? AND rh.unityId = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<RentalHistory> check = (List<RentalHistory>) jdbcTemplate.query(sqlCheck,
				new Object[] {(Integer)resource_id, patron.getUnityid()}, new BeanPropertyRowMapper(RentalHistory.class));
		if (check.isEmpty()){
			String sql = "INSERT INTO Rental_History (checked_out, due_date, Resource_id, unityId) VALUES (TO_DATE(?,'mm/dd/yyyy hh24:mi:ss'), TO_DATE(?,'mm/dd/yyyy hh24:mi:ss'), ?, ?)";
			jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sql,checked_outDateTime, dueDateTime, resource_id, patron.getUnityid());
			return true;
		}
		else return false;
	}
	

}
