package edu.ncsu.csc540.model;

// Generated Oct 22, 2015 4:43:38 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

/**
 * EnrolledCoursesId generated by hbm2java
 */
public class EnrolledCoursesId implements java.io.Serializable {

	private BigDecimal cid;
	private String stdUnityid;

	public EnrolledCoursesId() {
	}

	public EnrolledCoursesId(BigDecimal cid, String stdUnityid) {
		this.cid = cid;
		this.stdUnityid = stdUnityid;
	}

	public BigDecimal getCid() {
		return this.cid;
	}

	public void setCid(BigDecimal cid) {
		this.cid = cid;
	}

	public String getStdUnityid() {
		return this.stdUnityid;
	}

	public void setStdUnityid(String stdUnityid) {
		this.stdUnityid = stdUnityid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EnrolledCoursesId))
			return false;
		EnrolledCoursesId castOther = (EnrolledCoursesId) other;

		return ((this.getCid() == castOther.getCid()) || (this.getCid() != null
				&& castOther.getCid() != null && this.getCid().equals(
				castOther.getCid())))
				&& ((this.getStdUnityid() == castOther.getStdUnityid()) || (this
						.getStdUnityid() != null
						&& castOther.getStdUnityid() != null && this
						.getStdUnityid().equals(castOther.getStdUnityid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37
				* result
				+ (getStdUnityid() == null ? 0 : this.getStdUnityid()
						.hashCode());
		return result;
	}

}
