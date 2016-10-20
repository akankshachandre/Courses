package edu.ncsu.csc540;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagement {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");

		ProvisionDao provisionDao = (ProvisionDao) context
				.getBean("provisionDao");
		provisionDao.resetDatabase();

		System.out.println("Database is now cleared out.");

		SpringLiquibase liquibase = (SpringLiquibase) context
				.getBean("liquibase");
		try {
			liquibase.setShouldRun(true);
			liquibase.afterPropertiesSet();
		} catch (LiquibaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Program completed");
	}
}
