package dataaccess.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import business.model.*;

public class BookDAO {

	public static BookDAO bookDao = new BookDAO();
	/*public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+File.pathSeparator+"dataaccess"+File.pathSeparator+"person.txt";*/

	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
				+"\\src\\dataaccess\\book.txt";
	
	ObjectOutputStream output;
	ObjectInputStream input;
	
	public void connectWrite(){
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(
					OUTPUT_DIR);
			output = new ObjectOutputStream(fileOutputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	
	public void connectRead() throws ClassNotFoundException{
     		try {
		   FileInputStream fileInputStream = new FileInputStream(new File(
						OUTPUT_DIR));
			input = new ObjectInputStream(fileInputStream);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void add(List<Book> books) {
			
			try {
				connectWrite();
			    output.writeObject(books);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		public List<Book> getAllBooks() throws ClassNotFoundException {
			List<Book> bList =new ArrayList<>();
			ArrayList<Book> list =new ArrayList<>();
			try {
				connectRead();
			     list=(ArrayList<Book>) input.readObject();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			for(int i=0;i<list.size();i++){
	        	bList.add(list.get(i));
	        }
			return bList;
		}
		
		public Book update(Book book) {
			String isbnNum = book.getISBNnumber();
			try {
				List<Book> list = getAllBooks();
				Book existingPerson = get(isbnNum);
				list.add(list.indexOf(existingPerson), book);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return book;
		}


		public Book get(String id) {
			Optional<Book> book;
			try {
				List<Book> list = getAllBooks();
			
				for (Book b : list) {
					if (b.getISBNnumber().equals(id)) {
						return b;
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}


		public static void main(String[] args) throws ClassNotFoundException{
			List<Book> list = new ArrayList<>();
			
			List<Author> authors = new ArrayList<Author>();
			authors.add(new Author("test", "test", null));
			authors.add(new Author("test1", "test1", null));
			authors.add(new Author("test2", "test2", null));
			
			Book b1 = new Book("123", "javaEE", 0, 21, authors);
			Book b2 = new Book("124", "big data", 0, 21, authors);
			Book b3 = new Book("125", "algo", 0, 21, authors);
			
			List<BookCopy> copies = new ArrayList<BookCopy>();
			copies.add(new BookCopy("12340", b1));
			copies.add(new BookCopy("12341", b1));
			copies.add(new BookCopy("12342", b1));
			b1.setCopies(copies);
			
			copies = new ArrayList<BookCopy>();
			copies.add(new BookCopy("12343", b2));
			copies.add(new BookCopy("12344", b2));
			copies.add(new BookCopy("12345", b2));
			b2.setCopies(copies);
			
			copies = new ArrayList<BookCopy>();
			copies.add(new BookCopy("12346", b3));
			copies.add(new BookCopy("12347", b3));
			copies.add(new BookCopy("12348", b3));
			b3.setCopies(copies);
			
			list.add(b1);
			list.add(b2);
			list.add(b3);
			
			bookDao.add(list);
			List<Book> readList;
			readList = bookDao.getAllBooks();
			if(!readList.isEmpty()){
				for(Book book :readList){
					System.out.println(book);
				}
			}

		}
}


