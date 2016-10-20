package edu.ncsu.csc540.model;

import java.io.Serializable;
import java.util.Date;

public class Notifications implements Serializable {

	private int resource_id;
	private String patron_unityid;
	private String message;
	private Date notification_date;

	public Notifications(int resource_id, String patron_unityid,
			String message, Date notification_date) {
		this.resource_id = resource_id;
		this.patron_unityid = patron_unityid;
		this.message = message;
		this.notification_date = notification_date;
	}

	public Notifications() {
	}

	public int getResource_id() {
		return resource_id;
	}

	public void setResource_id(int resource_id) {
		this.resource_id = resource_id;
	}

	public String getPatron_unityid() {
		return patron_unityid;
	}

	public void setPatron_unityid(String patron_unityid) {
		this.patron_unityid = patron_unityid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getNotification_date() {
		return notification_date;
	}

	public void setNotification_date(Date notification_date) {
		this.notification_date = notification_date;
	}
}
