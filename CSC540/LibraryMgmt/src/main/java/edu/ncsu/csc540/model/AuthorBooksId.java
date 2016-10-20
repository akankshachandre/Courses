package edu.ncsu.csc540.model;

// Generated Oct 22, 2015 4:43:38 PM by Hibernate Tools 3.4.0.CR1

/**
 * AuthorBooksId generated by hbm2java
 */
public class AuthorBooksId implements java.io.Serializable {

	private String name;
	private String isbn;

	public AuthorBooksId() {
	}

	public AuthorBooksId(String name, String isbn) {
		this.name = name;
		this.isbn = isbn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AuthorBooksId))
			return false;
		AuthorBooksId castOther = (AuthorBooksId) other;

		return ((this.getName() == castOther.getName()) || (this.getName() != null
				&& castOther.getName() != null && this.getName().equals(
				castOther.getName())))
				&& ((this.getIsbn() == castOther.getIsbn()) || (this.getIsbn() != null
						&& castOther.getIsbn() != null && this.getIsbn()
						.equals(castOther.getIsbn())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getIsbn() == null ? 0 : this.getIsbn().hashCode());
		return result;
	}

}