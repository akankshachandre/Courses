package edu.ncsu.csc540.model;

// Generated Oct 22, 2015 4:43:38 PM by Hibernate Tools 3.4.0.CR1

/**
 * AuthorConfId generated by hbm2java
 */
public class AuthorConfId implements java.io.Serializable {

	private String name;
	private String confNum;

	public AuthorConfId() {
	}

	public AuthorConfId(String name, String confNum) {
		this.name = name;
		this.confNum = confNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfNum() {
		return this.confNum;
	}

	public void setConfNum(String confNum) {
		this.confNum = confNum;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AuthorConfId))
			return false;
		AuthorConfId castOther = (AuthorConfId) other;

		return ((this.getName() == castOther.getName()) || (this.getName() != null
				&& castOther.getName() != null && this.getName().equals(
				castOther.getName())))
				&& ((this.getConfNum() == castOther.getConfNum()) || (this
						.getConfNum() != null && castOther.getConfNum() != null && this
						.getConfNum().equals(castOther.getConfNum())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getConfNum() == null ? 0 : this.getConfNum().hashCode());
		return result;
	}

}
