package edu.ncsu.csc540.model;

// Generated Oct 22, 2015 4:43:38 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

/**
 * RoomsId generated by hbm2java
 */
public class RoomsId implements java.io.Serializable {

	private BigDecimal roomNumber;
	private BigDecimal resId;

	public RoomsId() {
	}

	public RoomsId(BigDecimal roomNumber, BigDecimal resId) {
		this.roomNumber = roomNumber;
		this.resId = resId;
	}

	public BigDecimal getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(BigDecimal roomNumber) {
		this.roomNumber = roomNumber;
	}

	public BigDecimal getResId() {
		return this.resId;
	}

	public void setResId(BigDecimal resId) {
		this.resId = resId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RoomsId))
			return false;
		RoomsId castOther = (RoomsId) other;

		return ((this.getRoomNumber() == castOther.getRoomNumber()) || (this
				.getRoomNumber() != null && castOther.getRoomNumber() != null && this
				.getRoomNumber().equals(castOther.getRoomNumber())))
				&& ((this.getResId() == castOther.getResId()) || (this
						.getResId() != null && castOther.getResId() != null && this
						.getResId().equals(castOther.getResId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getRoomNumber() == null ? 0 : this.getRoomNumber()
						.hashCode());
		result = 37 * result
				+ (getResId() == null ? 0 : this.getResId().hashCode());
		return result;
	}

}
