package edu.ncsu.csc540.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.ncsu.csc540.dao.PatronDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class PatronDaoImplTest {

	@Autowired
	PatronDao patronDao;

	@Test
	public void testLoginSuccsessful() {
		assertNotNull(patronDao.loginSuccsessful("jpink", "jpink"));
	}

	@Test
	public void testLoginNotSuccsessful() {
		assertNull(patronDao.loginSuccsessful("jpink", "bad password"));
	}

}
