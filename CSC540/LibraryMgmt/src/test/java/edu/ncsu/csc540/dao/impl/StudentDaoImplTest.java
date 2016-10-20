package edu.ncsu.csc540.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.ncsu.csc540.dao.StudentDao;
import edu.ncsu.csc540.model.Students;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class StudentDaoImplTest {

	@Autowired
	StudentDao studentDao;

	@Test
	public void testGetStudent() {
		List<Students> students = studentDao.getStudent("jpink");
		Students student = students.get(0);
		assertEquals("jpink", student.getStdUnityid());
		assertEquals("S1", student.getStdNumber());
		assertEquals("First Year", student.getStdCat());
		assertEquals("Undergraduate", student.getStdClassification());

		assertNotNull(student.getDob());
	}

}
