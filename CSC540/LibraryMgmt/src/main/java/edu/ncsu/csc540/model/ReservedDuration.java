package edu.ncsu.csc540.model;

// Generated Oct 22, 2015 4:43:38 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ReservedDuration generated by hbm2java
 */
public class ReservedDuration implements java.io.Serializable {

	private BigDecimal reservedDurationId;
	private Date reservedFrom;
	private Date reservedTo;
	private Set<ReservedBooks> reservedBookses = new HashSet<ReservedBooks>(0);

	public ReservedDuration() {
	}

	public ReservedDuration(BigDecimal reservedDurationId) {
		this.reservedDurationId = reservedDurationId;
	}

	public ReservedDuration(BigDecimal reservedDurationId, Date reservedFrom,
			Date reservedTo, Set<ReservedBooks> reservedBookses) {
		this.reservedDurationId = reservedDurationId;
		this.reservedFrom = reservedFrom;
		this.reservedTo = reservedTo;
		this.reservedBookses = reservedBookses;
	}

	public BigDecimal getReservedDurationId() {
		return this.reservedDurationId;
	}

	public void setReservedDurationId(BigDecimal reservedDurationId) {
		this.reservedDurationId = reservedDurationId;
	}

	public Date getReservedFrom() {
		return this.reservedFrom;
	}

	public void setReservedFrom(Date reservedFrom) {
		this.reservedFrom = reservedFrom;
	}

	public Date getReservedTo() {
		return this.reservedTo;
	}

	public void setReservedTo(Date reservedTo) {
		this.reservedTo = reservedTo;
	}

	public Set<ReservedBooks> getReservedBookses() {
		return this.reservedBookses;
	}

	public void setReservedBookses(Set<ReservedBooks> reservedBookses) {
		this.reservedBookses = reservedBookses;
	}

}