package dataaccess.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import business.model.Author;
import business.model.Book;
import business.model.Address;
import business.model.BookCopy;

public class TestClass  {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		List<Book> bookList = new ArrayList<>();
		List<Author> auth1 = new ArrayList<>();
		List<Author> auth2 = new ArrayList<>();
		Book rootBook = new Book();
		Address a1Address = new Address("Iowa", "Fairfield", "55756", "1000 N 4th street");
		Address a2Address = new Address("Iowa", "Iowa City", "53641", "1000 S 5th street");
		Author a1 = new Author("Tom", "Anderson",a1Address,"123","example@gmail.com");
		Author a2 = new Author("Jesse", "Okuma", a2Address,"123","example@gmail.com");
		auth1.add(a1);
		auth1.add(a2);
		Address a3Address = new Address("Chicago", "Chicago", "53641", "1000 W 6th street");
		Author a3 = new Author("Jesse", "Okuma", a3Address,"123","example@gmail.com");
		auth2.add(a3);
		String uniqueID1 = UUID.randomUUID().toString();
		String uniqueID2 = UUID.randomUUID().toString();
		Book b1 = new Book("Java","978-3-16-148410-0", 456.3,21, auth1);
		Book b2 = new Book( "PHP","639-3-16-126489-1", 350.3,7, auth2);
		BookCopy b1c1 = new BookCopy(uniqueID1, b1);
		BookCopy b2c2 = new BookCopy(uniqueID2, b2);
		rootBook.addBookCopy(b1c1);
		rootBook.addBookCopy(b2c2);
		bookList.add(rootBook);
		rootBook.addBookData(bookList);
		List<Book> lookup = rootBook.lookupBookData();
		System.out.println("lookup  "+lookup);
		List<BookCopy> lookupCopy = null;
		
		for(Book x: lookup) {
			lookupCopy = x.getCopies();
			for(BookCopy y: lookupCopy)
				System.out.println("loop   "+y);
		}
		
		System.out.println("\n\nEdit Id1: ");
		System.out.println(rootBook.editBookData(uniqueID1));
		
		Book b3 = new Book( "C++","639-3-16-126489-1", 350.3,21, auth2);
		b2c2.setBook(b3);
		
		rootBook.updateBookData(b2c2);
		System.out.println("\n\nEdit Id2: ");
		System.out.println(rootBook.editBookData(uniqueID2));
		System.out.println("\n\nAfter Update");
		
		lookup = rootBook.lookupBookData();
		System.out.println("book lookup"+ lookup);
		for(Book x: lookup) {
			lookupCopy = x.getCopies();
			for(BookCopy y: lookupCopy)
				System.out.println("loop  "+y);
		}
		
		System.out.println("\n\nsearch \"978-3-16-148410-0\": ");
		System.out.println(rootBook.search("978-3-16-148410-0"));
		BookCopy editSearch = rootBook.editSearch("adf7be70-c9da-4e35-b815-74d1a466fef7sq");
		System.out.println("editSearch "+ editSearch);
		
		
		/*System.out.println("\n\n\nTest ... ");
		Book test = new Book(); 
		List<Book> loopupOne = test.lookupBookData();
		List<BookCopy> lookupCopyOne = new ArrayList<>();
		for(Book x: loopupOne) {
			lookupCopyOne = x.getCopies();
			for(BookCopy y: lookupCopyOne)
				System.out.println("book copy "+y);
		}*/
		/*Book rootBook = new Book();
		System.out.println("In Main ");
		BookCopy editSearch = rootBook.editSearch("adf7be70-c9da-4e35-b815-74d1a466fef7sq");
		System.out.println("editSearch "+ editSearch);
		
		List<Book> lookup  = rootBook.lookupBookData();
		System.out.println("Look up "+rootBook.lookupBookData());
		List<BookCopy> lookupCopy = new ArrayList<>();
		for(Book x: lookup) {
			lookupCopy = x.getCopies();
			for(BookCopy y: lookupCopy)
				System.out.println("book copy "+y);
		}*/
		
				
	}

}
