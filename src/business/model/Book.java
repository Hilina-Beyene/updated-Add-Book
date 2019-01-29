package business.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dataaccess.dao.BookData;
import javafx.scene.control.Alert;

public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3349822635229618363L;
	private String ISBNnumber;
	private String title;
	private String objectID;
	private double price;
	private int limit;
	private BookData bookData;
	private List<Author> authors;
	private List<BookCopy> copies;
	
	public Book() throws IOException  {
		copies = new ArrayList<>();
		this.bookData = new BookData();
		String uniqueID = UUID.randomUUID().toString();
		objectID = uniqueID;
	}
	
	public Book(String title, String ISBNnumber, double price, int limit, List<Author> authors) {
		this.ISBNnumber = ISBNnumber;
		this.title = title;
		this.price = price;
		this.authors = authors;
		this.limit = limit;
		this.copies = null;
		this.bookData = null;
		this.objectID = null;
	}
	
	/*public Book(String iSBNnumber, String title, boolean isAvailable, List<Author> authors, List<BookCopy> copies) {
		this.ISBNnumber = iSBNnumber;
		this.title = title;
		this.isAvailable = isAvailable;
		this.authors = authors;
		this.copies = copies;
	}*/
	
	public void addBookCopy(BookCopy bookCopy) {
		if(copies != null)
			copies.add(bookCopy);
			
	}
	
	public void addBookData(List<Book> books) throws IOException {
		 
		 if(copies != null) 
			 bookData.addBookData(books);
	}
	
	public void updateBookData(BookCopy bookCopy) throws IOException, ClassNotFoundException {
		if(copies != null)
			bookData.updateBookData(bookCopy);
	}
	
	public BookCopy editBookData(String bookCopyId) throws IOException, ClassNotFoundException {
		return bookData.editBookData(bookCopyId,objectID);
	}
	
	public List<Book> lookupBookData() throws IOException, ClassNotFoundException {
		return bookData.lookupBookData();
	}
	
	public BookCopy editSearch(String search) throws ClassNotFoundException, IOException {
		return bookData.editSearch(search);
	}
	public List<BookCopy> search(String search) throws IOException, ClassNotFoundException{
		return bookData.search(search);
	}
	
	public String getObjectID() {
		return objectID;
	}
	
	public String getISBNnumber() {
		return ISBNnumber;
	}
	public void setISBNnumber(String iSBNnumber) {
		ISBNnumber = iSBNnumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimt(int limit) {
		this.limit = limit;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public List<BookCopy> getCopies() {
		return copies;
	}
	public void setCopies(List<BookCopy> copies) {
		this.copies = copies;
	}
	
	@Override
	public String toString() {
		return "[ " + ISBNnumber + ", " + title +  ", " + price + " ]";
	}
}
