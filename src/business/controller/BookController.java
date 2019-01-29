package business.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import business.model.Book;
import business.model.BookCopy;
import dataaccess.dao.BookData;
import dataaccess.tables.BookTable;
import business.model.Author;
import business.model.Address;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BookController implements Initializable {
	
	@FXML private TableView<BookTable> table = new TableView<BookTable>();
	@FXML private TableColumn<BookTable, String> titleView;
	@FXML private TableColumn<BookTable, String> isbnView;
	@FXML private TableColumn<BookTable, Double> priceView;
	@FXML private TableColumn<BookTable, String> authorFirstNameView;
	@FXML private TableColumn<BookTable, String> authorLastNameView;
	@FXML private TableColumn<BookTable, String> phoneNumberView;
	@FXML private TableColumn<BookTable, String> emailView;
	@FXML private TextField titleField;
	@FXML private TextField isbnField;
	@FXML private TextField priceField;
	@FXML private TextField limitField;
	@FXML private TextField searchField;
	@FXML private TextField firstNameField;
	@FXML private TextField lastNameField;
	@FXML private TextField stateField;
	@FXML private TextField cityField;
	@FXML private TextField zipField;
	@FXML private TextField streetField;
	@FXML private TextField phoneNumField;
	@FXML private TextField emailField;
	
	private  Book rootBook;
	private  static List<Book> bookList;
	private  Book book;
	private  BookCopy bookCopy;
	private  List<Author> authors;
	private  Address address;
	private  boolean editIsOn;
	private String unique;
	
	public BookController() throws IOException{
		authors = new ArrayList<>();
		bookList = new ArrayList<>();
	}
	
	@FXML public void newBookButton() throws IOException {
		rootBook = new Book();
		titleField.requestFocus();
	}
	
	private void compulsoryFields(String value) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(value);
        alert.showAndWait();
	}
	
	private ObservableList<BookTable> showTableData() throws ClassNotFoundException, IOException {
	   ObservableList<BookTable> bookTables = FXCollections.observableArrayList();
	   List<Book> books = rootBook.lookupBookData();
	   List<BookCopy> bookCopy = new ArrayList<>();
	   for(Book x: books) {
		   bookCopy = x.getCopies();
		   for(BookCopy y: bookCopy) {
			   bookTables.add(new BookTable(y.getBook().getTitle(),
					   						y.getBook().getISBNnumber(),
					   						y.getBook().getPrice(),
					   						y.getBook().getAuthors().get(0).getFirstName(),
					   						y.getBook().getAuthors().get(0).getLastName(),
					   						y.getBook().getAuthors().get(0).getPhoneNumber(),
					   						y.getBook().getAuthors().get(0).getEmail(),
					   						y.getUniqueId()));
		   }
	   }
	   
	   return bookTables;
	}
	
	private boolean isAnyTextFieldEmpty() {
		
		if(firstNameField.getText().equals("")	||
			lastNameField.getText().equals("")	||
			stateField.getText().equals("")	||
			cityField.getText().equals("")	||
			zipField.getText().equals("")	||
			streetField.getText().equals("")	||
			phoneNumField.getText().equals("")	||
			emailField.getText().equals("") ||
			titleField.getText().equals("") ||
			isbnField.getText().equals("")	||
			priceField.getText().equals("") ||
			limitField.getText().equals("")) {
			return true;
		}
		else return false;
	}
	
	
	@FXML public void editBookButton(ActionEvent event) throws IOException, ClassNotFoundException {
		
		if (table.getSelectionModel().getSelectedItem() != null) {
			String priceString = "";
			String limitString = "";
	        BookTable selectedPerson = table.getSelectionModel().getSelectedItem();
	        BookCopy bookCopy = search(selectedPerson.getBookCopyId());
	        unique = bookCopy.getUniqueId();
	        titleField.setText(bookCopy.getBook().getTitle());
	        isbnField.setText(bookCopy.getBook().getISBNnumber());
	        priceField.setText(priceString + bookCopy.getBook().getPrice());
	        limitField.setText(limitString + bookCopy.getBook().getLimit());
	        
	        firstNameField.setText(bookCopy.getBook().getAuthors().get(0).getFirstName());
			lastNameField.setText(bookCopy.getBook().getAuthors().get(0).getLastName());
			stateField.setText(bookCopy.getBook().getAuthors().get(0).getAddress().getState());
			cityField.setText(bookCopy.getBook().getAuthors().get(0).getAddress().getCity());
			zipField.setText(bookCopy.getBook().getAuthors().get(0).getAddress().getZip());
			streetField.setText(bookCopy.getBook().getAuthors().get(0).getAddress().getStreet());
			phoneNumField.setText(bookCopy.getBook().getAuthors().get(0).getPhoneNumber());
			emailField.setText(bookCopy.getBook().getAuthors().get(0).getEmail());
	        editIsOn = true;
			
	    }
	}
	
	@FXML public void clearButton() {
		titleField.setText("");
		isbnField.setText("");
		priceField.setText("");
		limitField.setText("");
		searchField.setText("");
		firstNameField.setText("");
		lastNameField.setText("");
		stateField.setText("");
		cityField.setText("");
		zipField.setText("");
		streetField.setText("");
		phoneNumField.setText("");
		emailField.setText("");
	}
	
	private BookCopy search(String value) throws ClassNotFoundException, IOException {
		BookCopy editBookCopy = null;
		if(!value.equals("") || value != null) {
			editBookCopy = rootBook.editSearch(value);
		}
		return editBookCopy;
	}
	
	@FXML public void searchButton() throws ClassNotFoundException, IOException {
		String value = searchField.getText();
		searchField.setText("");
		List<BookCopy>  bookCopies = null;
		
		if(value.equals("")) {
			initializeCommonMethod(showTableData());
		}
		
		else if(value != null) {
			bookCopies = rootBook.search(value);
			if(bookCopies != null)
				initializeCommonMethod(showSearchResult(bookCopies));
		}
	}

	
	@FXML public void saveBookButton() throws IOException, ClassNotFoundException {
		if(authors.size() == 0) {
			compulsoryFields("Please provide author's information!");
             
             return;
		}
		
		String uniqueID = UUID.randomUUID().toString();
		double price; 
		int limit;
		try {
			 price = Double.parseDouble(priceField.getText());
			 limit = Integer.parseInt(limitField.getText());
		}catch(NumberFormatException e) {
            compulsoryFields("Price and Limit can only accept number!");
            return;
		}
		
		List<Book> previousData = rootBook.lookupBookData();
		
		if(previousData != null) {
			bookList.clear();
			bookList.addAll(previousData);
		}
		
		book = new Book(titleField.getText(), isbnField.getText(), price, limit, authors);
		bookCopy = new BookCopy(uniqueID, book);
		
		rootBook.addBookCopy(bookCopy);
		bookList.add(rootBook);
		rootBook.addBookData(bookList);
		
		clearButton();
		initializeCommonMethod(showTableData());
	}
	
	@FXML public void saveAuthorButton() {
		if(isAnyTextFieldEmpty()) {
			compulsoryFields("All fields are compulsory!");
            return;
		}
		
		address = new Address(stateField.getText(),cityField.getText(),zipField.getText(),streetField.getText());
		Author author = new Author(firstNameField.getText(), lastNameField.getText(), address, phoneNumField.getText(),emailField.getText());
		authors.add(author);
		firstNameField.setText("");
		lastNameField.setText("");
		stateField.setText("");
		cityField.setText("");
		zipField.setText("");
		streetField.setText("");
		phoneNumField.setText("");
		emailField.setText("");
	}
	
	@FXML public void updateBookButton() throws ClassNotFoundException, IOException {
		if(editIsOn) {
			
			if(authors.size() != 0) {
				double price; 
				int limit;
				try {
					 price = Double.parseDouble(priceField.getText());
					 limit = Integer.parseInt(limitField.getText());
				}catch(NumberFormatException e) {
		            compulsoryFields("Price and Limit can only accept number!");
		            return;
				}
				book = new Book(titleField.getText(), isbnField.getText(), price, limit, authors);
				bookCopy = new BookCopy(unique, book);
				
				Book tempRootBook = new Book();
				tempRootBook.updateBookData(bookCopy);
				editIsOn = false;
				clearButton();
			}
			else {
				compulsoryFields("PLease Provide Author's information!");
			}
			initializeCommonMethod(showTableData());
			
		}
	}
	
	private ObservableList<BookTable> showSearchResult(List<BookCopy> bookCopies) {
		ObservableList<BookTable> bookTables = FXCollections.observableArrayList();
	   for(BookCopy y: bookCopies) {
		   bookTables.add(new BookTable(y.getBook().getTitle(),
				   						y.getBook().getISBNnumber(),
				   						y.getBook().getPrice(),
				   						y.getBook().getAuthors().get(0).getFirstName(),
				   						y.getBook().getAuthors().get(0).getLastName(),
				   						y.getBook().getAuthors().get(0).getPhoneNumber(),
				   						y.getBook().getAuthors().get(0).getEmail(),
				   						y.getUniqueId()));
	   }
	   
	   return bookTables;
	}
	
	private void initializeCommonMethod(ObservableList<BookTable> value) {
		
		table.setEditable(false);
		titleView.setCellValueFactory(new PropertyValueFactory<BookTable,String>("title"));
		isbnView.setCellValueFactory(new PropertyValueFactory<BookTable,String>("isbn"));
		priceView.setCellValueFactory(new PropertyValueFactory<BookTable,Double>("price"));
		authorFirstNameView.setCellValueFactory(new PropertyValueFactory<BookTable,String>("firstName"));
		authorLastNameView.setCellValueFactory(new PropertyValueFactory<BookTable,String>("lastName"));
		phoneNumberView.setCellValueFactory(new PropertyValueFactory<BookTable,String>("phoneNum"));
		emailView.setCellValueFactory(new PropertyValueFactory<BookTable,String>("email"));
		table.setItems(value);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			rootBook = new Book();
			initializeCommonMethod(showTableData());
			
		}catch(IOException io) {}
		catch(ClassNotFoundException cn) {}
		catch(NullPointerException ex) {}
		
	}

}
