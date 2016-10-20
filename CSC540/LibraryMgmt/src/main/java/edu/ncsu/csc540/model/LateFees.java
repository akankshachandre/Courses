package edu.ncsu.csc540.model;

public class LateFees implements java.io.Serializable{
	private int fees;
	private String unityId;
	private int resource_Id;

	
	
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public String getUnityId() {
		return unityId;
	}
	public void setUnityId(String unityId) {
		this.unityId = unityId;
	}
	public int getResource_Id() {
		return resource_Id;
	}
	public void setResource_Id(int resource_Id) {
		this.resource_Id = resource_Id;
	}

}
