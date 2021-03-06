package edu.ncsu.csc540.model;

// Generated Oct 22, 2015 4:43:38 PM by Hibernate Tools 3.4.0.CR1

/**
 * AuthorJournalsId generated by hbm2java
 */
public class AuthorJournalsId implements java.io.Serializable {

	private String name;
	private String issn;

	public AuthorJournalsId() {
	}

	public AuthorJournalsId(String name, String issn) {
		this.name = name;
		this.issn = issn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIssn() {
		return this.issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AuthorJournalsId))
			return false;
		AuthorJournalsId castOther = (AuthorJournalsId) other;

		return ((this.getName() == castOther.getName()) || (this.getName() != null
				&& castOther.getName() != null && this.getName().equals(
				castOther.getName())))
				&& ((this.getIssn() == castOther.getIssn()) || (this.getIssn() != null
						&& castOther.getIssn() != null && this.getIssn()
						.equals(castOther.getIssn())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getIssn() == null ? 0 : this.getIssn().hashCode());
		return result;
	}

}
