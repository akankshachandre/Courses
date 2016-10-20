package edu.ncsu.csc540.model;

import java.sql.Date;

public class WaitListPublications implements java.io.Serializable {
	private int resource_id;
	private String unityId;
	private Date enteredDate;
	public int getResource_id() {
		return resource_id;
	}
	public void setResource_id(int resource_id) {
		this.resource_id = resource_id;
	}
	public String getUnityId() {
		return unityId;
	}
	public void setUnityId(String unityId) {
		this.unityId = unityId;
	}
	public Date getEnteredDate() {
		return enteredDate;
	}
	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}
	
	

}
