package edu.ncsu.csc540.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.ncsu.csc540.dao.StudentDao;
import edu.ncsu.csc540.model.Patrons;
import edu.ncsu.csc540.model.Students;

@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Students> getStudent(String std_unityId) {
		String sql = "SELECT * FROM Students WHERE std_unityid = ?";

		jdbcTemplate = new JdbcTemplate(dataSource);
		List student = jdbcTemplate.query(sql,
				new Object[] { std_unityId }, new BeanPropertyRowMapper(
						Students.class));

		return student;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean isStudent(String unityId) {
		String sql = "SELECT * FROM Students WHERE std_unityid = ?";
        
		jdbcTemplate = new JdbcTemplate(dataSource);
		List student = jdbcTemplate.query(sql,
				new Object[] { unityId }, new BeanPropertyRowMapper(
						Students.class));
		if (student.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateStudent(Students s, Patrons p) {
		String sqlStudent = "UPDATE Students set city = ?, phone_num = ?, street = ?, postal_code = ?, Alt_phone = ? WHERE std_unityid = ?";
		String sqlPatron = "UPDATE Patrons set nationality = ?, password = ? WHERE unityid = ?";

		jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sqlStudent,
				new Object[] { s.getCity(), String.valueOf(s.getPhoneNum()),
						s.getStreet(), s.getPostalCode(), s.getAltPhone(),
						s.getStdUnityid() });

		jdbcTemplate.update(
				sqlPatron,
				new Object[] { p.getNationality(), p.getPassword(),
						p.getUnityid() });
	}
}
