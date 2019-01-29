package business.model;

import java.io.Serializable;

public class BookCopy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3969315852691755681L;
	private Book book;
	private String uniqueID;
	private boolean isBorrowed;
	
	public BookCopy(String uniqueID,Book book) {
		this.uniqueID = uniqueID;
		this.book = book;
		this.isBorrowed = false;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getUniqueId() {
		return uniqueID;
	}
	/*public void setUniqueId(String uniqueID) {
		this.uniqueID = uniqueID;
	}*/
	public boolean isBorrowed() {
		return isBorrowed;
	}
	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
	
	@Override
	public String toString() {
		System.out.println(book);
		return "[ " + uniqueID + ", " + isBorrowed  + " ]";
	}

}
