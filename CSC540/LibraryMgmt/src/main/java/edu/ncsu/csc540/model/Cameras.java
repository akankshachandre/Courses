package edu.ncsu.csc540.model;

// Generated Oct 22, 2015 4:43:38 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

/**
 * Cameras generated by hbm2java
 */
public class Cameras implements java.io.Serializable {

	private String cameraId;
	private int resourceId;
	private String make;
	private String model;
	private String lensConfig;
	private String availableMemory;

	public Cameras() {
	}

	public Cameras(String cameraId, int resourceId) {
		this.cameraId = cameraId;
		this.resourceId = resourceId;
	}

	public Cameras(String cameraId, int resourceId, String make,
			String model, String lensConfig, String availableMemory) {
		this.cameraId = cameraId;
		this.resourceId = resourceId;
		this.make = make;
		this.model = model;
		this.lensConfig = lensConfig;
		this.availableMemory = availableMemory;
	}

	public String getCameraId() {
		return this.cameraId;
	}

	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}

	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLensConfig() {
		return this.lensConfig;
	}

	public void setLensConfig(String lensConfig) {
		this.lensConfig = lensConfig;
	}

	public String getAvailableMemory() {
		return this.availableMemory;
	}

	public void setAvailableMemory(String availableMemory) {
		this.availableMemory = availableMemory;
	}

}
