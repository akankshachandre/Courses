package edu.ncsu.csc540.model;

// Generated Oct 22, 2015 4:43:38 PM by Hibernate Tools 3.4.0.CR1

/**
 * EnrolledCourses generated by hbm2java
 */
public class EnrolledCourses implements java.io.Serializable {

	private EnrolledCoursesId id;
	private Students students;

	public EnrolledCourses() {
	}

	public EnrolledCourses(EnrolledCoursesId id, Students students) {
		this.id = id;
		this.students = students;
	}

	public EnrolledCoursesId getId() {
		return this.id;
	}

	public void setId(EnrolledCoursesId id) {
		this.id = id;
	}

	public Students getStudents() {
		return this.students;
	}

	public void setStudents(Students students) {
		this.students = students;
	}

}