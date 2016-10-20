package edu.ncsu.csc540.model;

// Generated Oct 22, 2015 4:43:38 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

/**
 * ConferenceProceedingsId generated by hbm2java
 */
public class ConferenceProceedingsId implements java.io.Serializable {

	private String confNum;
	private BigDecimal resourceId;

	public ConferenceProceedingsId() {
	}

	public ConferenceProceedingsId(String confNum, BigDecimal resourceId) {
		this.confNum = confNum;
		this.resourceId = resourceId;
	}

	public String getConfNum() {
		return this.confNum;
	}

	public void setConfNum(String confNum) {
		this.confNum = confNum;
	}

	public BigDecimal getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(BigDecimal resourceId) {
		this.resourceId = resourceId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ConferenceProceedingsId))
			return false;
		ConferenceProceedingsId castOther = (ConferenceProceedingsId) other;

		return ((this.getConfNum() == castOther.getConfNum()) || (this
				.getConfNum() != null && castOther.getConfNum() != null && this
				.getConfNum().equals(castOther.getConfNum())))
				&& ((this.getResourceId() == castOther.getResourceId()) || (this
						.getResourceId() != null
						&& castOther.getResourceId() != null && this
						.getResourceId().equals(castOther.getResourceId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getConfNum() == null ? 0 : this.getConfNum().hashCode());
		result = 37
				* result
				+ (getResourceId() == null ? 0 : this.getResourceId()
						.hashCode());
		return result;
	}

}
