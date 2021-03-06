package edu.ncsu.csc540.model;

// Generated Oct 22, 2015 4:43:38 PM by Hibernate Tools 3.4.0.CR1


import java.util.HashSet;
import java.util.Set;

/**
 * Resources generated by hbm2java
 */
public class Resources implements java.io.Serializable {

	private int resource_id;
	private String host_library;
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private Set<RentalHistory> rentalHistories = new HashSet<RentalHistory>(0);
	private Set<ConferenceProceedings> conferenceProceedingses = new HashSet<ConferenceProceedings>(
			0);
	private Set<Rooms> roomses = new HashSet<Rooms>(0);
	private Books books;
	private Set<Cameras> camerases = new HashSet<Cameras>(0);
	private Set<Journals> journalses = new HashSet<Journals>(0);

	public Resources() {
	}

	public Resources(int resourceId) {
		this.resource_id = resourceId;
	}

	public Resources(int resourceId, String hostLibrary,
			Set<RentalHistory> rentalHistories,
			Set<ConferenceProceedings> conferenceProceedingses,
			Set<Rooms> roomses, Books books, Set<Cameras> camerases,
			Set<Journals> journalses) {
		this.resource_id = resourceId;
		this.host_library = hostLibrary;
		this.rentalHistories = rentalHistories;
		this.conferenceProceedingses = conferenceProceedingses;
		this.roomses = roomses;
		this.books = books;
		this.camerases = camerases;
		this.journalses = journalses;
	}

	public int getResourceId() {
		return this.resource_id;
	}

	public void setResourceId(int resourceId) {
		this.resource_id = resourceId;
	}

	public String getHostLibrary() {
		return this.host_library;
	}

	public void setHostLibrary(String hostLibrary) {
		this.host_library = hostLibrary;
	}

	public Set<RentalHistory> getRentalHistories() {
		return this.rentalHistories;
	}

	public void setRentalHistories(Set<RentalHistory> rentalHistories) {
		this.rentalHistories = rentalHistories;
	}

	public Set<ConferenceProceedings> getConferenceProceedingses() {
		return this.conferenceProceedingses;
	}

	public void setConferenceProceedingses(
			Set<ConferenceProceedings> conferenceProceedingses) {
		this.conferenceProceedingses = conferenceProceedingses;
	}

	public Set<Rooms> getRoomses() {
		return this.roomses;
	}

	public void setRoomses(Set<Rooms> roomses) {
		this.roomses = roomses;
	}

	public Books getBooks() {
		return this.books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	public Set<Cameras> getCamerases() {
		return this.camerases;
	}

	public void setCamerases(Set<Cameras> camerases) {
		this.camerases = camerases;
	}

	public Set<Journals> getJournalses() {
		return this.journalses;
	}

	public void setJournalses(Set<Journals> journalses) {
		this.journalses = journalses;
	}

}
