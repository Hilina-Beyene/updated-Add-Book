package dataaccess.tables;

import javafx.beans.property.SimpleStringProperty;

public class BookTable {
	private  SimpleStringProperty title;
	private  SimpleStringProperty isbn;
	private  Double price;
	private  SimpleStringProperty firstName;
	private  SimpleStringProperty lastName;
	private  SimpleStringProperty phoneNum;
	private  SimpleStringProperty email;
	private  String bookCopyId;
	
	public BookTable(String titleValue, String isbnValue, double priceValue,
			String firstNameValue, String lastNameValue, String phoneNumValue, String emailValue , String bookCopyId) {
		this.title = new SimpleStringProperty(titleValue);
		this.isbn = new SimpleStringProperty(isbnValue);
		this.price = priceValue;
		this.firstName = new SimpleStringProperty(firstNameValue);
		this.lastName = new SimpleStringProperty(lastNameValue);
		this.phoneNum = new SimpleStringProperty(phoneNumValue);
		this.email = new SimpleStringProperty(emailValue);
		this.bookCopyId = bookCopyId;
	}

	public String getBookCopyId() {
		return this.bookCopyId;
	}
	public String getTitle() {
		return this.title.get();
	}

	public  void setTitle(String value) {
		this.title.set(value);
	}

	public String getIsbn() {
		return this.isbn.get();
	}

	public void setIsbn(String value) {
		this.isbn.set(value);
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(double value) {
		this.price = value;
	}

	public String getFirstName() {
		return this.firstName.get();
	}

	public void setFirstName(String value) {
		this.firstName.set(value);
	}

	public String getLastName() {
		return this.lastName.get();
	}

	public void setLastName(String value) {
		this.lastName.set(value);
	}
	
	
	
}
