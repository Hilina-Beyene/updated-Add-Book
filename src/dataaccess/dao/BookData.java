package dataaccess.dao;

import java.io.Serializable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import business.model.Book;
import business.model.BookCopy;
import business.model.Author;

public class BookData implements Serializable{
	private static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+"\\src\\dataaccess\\dao\\BookDataFile.txt";
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	
	public BookData() {}
	
	public void connectWrite(){
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR);
			output = new ObjectOutputStream(fileOutputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	
	public void connectRead() throws ClassNotFoundException{
     		try {
		   FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR));
			input = new ObjectInputStream(fileInputStream);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch(EOFException ex) {
				
			}catch (IOException e) {
				e.printStackTrace();
			}
	}
	public void addBookData(List<Book> book) {
		try {
			connectWrite();
		    output.writeObject(book);
		    output.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*System.out.println("add book "+book.getCopies().get(0));
		fos = new FileOutputStream(OUTPUT_DIR_USER, true);
		oos = new ObjectOutputStream(fos);
		oos.writeObject("Hello");
		fos.close();
		oos.close();*/
	}
	
	public void updateBookData(BookCopy bookCopy) throws IOException, ClassNotFoundException {
		List<Book> updateBook;
		List<Book> books = getFileInList();
		
		updateBook = searchAndUpdate(bookCopy, books);
		addBookData(updateBook);
	}
	
	private List<Book> searchAndUpdate(BookCopy bookCopy, List<Book> books) {
		List<BookCopy> bookCopies = null;
		
		outer: for(int i=0; i<books.size(); i++) {
				bookCopies = books.get(i).getCopies();
				for(int j=0; j<bookCopies.size(); j++) {
					if(bookCopies.get(j).getUniqueId().equals(bookCopy.getUniqueId())) {
						bookCopies.remove(j);
						bookCopies.add(bookCopy);
						books.get(i).setCopies(bookCopies);
						break outer;
					}
				}
		}
		
		return books;
	}
	
	public BookCopy editSearch(String search) throws ClassNotFoundException, IOException {
		BookCopy bookCopy = null;
		List<BookCopy> bookCopies = null;
		
		List<Book> books = getFileInList();
		
		outer: for(Book x: books) {
				bookCopies = x.getCopies();
				
				for(BookCopy y: bookCopies) {
					if(y.getUniqueId().equals(search)) {
						bookCopy = y;
						break outer;
					}
				}
		}
		
		return bookCopy;
	}
	
	public List<BookCopy> search(String search) throws IOException, ClassNotFoundException {
		List<BookCopy> bookCopies = null;
		List<BookCopy> searchValues = new ArrayList<>();
		
		List<Book> books = getFileInList();
		
		for(Book x: books) {
			bookCopies = x.getCopies();
			
			for(BookCopy y: bookCopies) {
				if(y.getBook().getTitle().equals(search)) {
					searchValues.add(y);
				}
				
				else if(y.getBook().getISBNnumber().equals(search)) {
					searchValues.add(y);
				}
				
				else {
					for(Author z: y.getBook().getAuthors()) {
						if(z.getFirstName().equals(search) || z.getLastName().equals(search)) {
							searchValues.add(y);
						}
					}
				}
			}
		}
		
		return searchValues;
	}
	
	public BookCopy editBookData(String bookCopyId, String objId) throws IOException, ClassNotFoundException {
		
		List<Book> books = getFileInList();
		Book bookObject = null;
		BookCopy copy = null;
		
		for(Book bookObj: books) {
			if(bookObj.getObjectID().equals(objId)) {
				bookObject = bookObj;
			}	
		}
		
		if(bookObject != null)
			for(BookCopy x: bookObject.getCopies()) {
				if(x.getUniqueId().equals(bookCopyId)) {
					copy = x;
					break;
				}
			}
		
		return copy;
	} 
	
	public List<Book> lookupBookData() throws IOException, ClassNotFoundException{
		List<Book> books = getFileInList();
		return books;
	}
	
	private List<Book> getFileInList() throws IOException, ClassNotFoundException{
		
		//List<Book> bookObject = new ArrayList<>();
		List<Book> books = new ArrayList<>();
		
		try {
			connectRead();
			books = (ArrayList<Book>) input.readObject();
			System.out.println(books);
		}catch (EOFException ex) {
			
		}catch(NullPointerException ex) {}
		
		try {
			input.close();
		}catch(IOException ex) {
			
		}catch(NullPointerException ex) {}
		
		return books;
	}
}
