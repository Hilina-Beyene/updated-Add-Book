package business.model;

import java.io.Serializable;
import java.util.Date;

public class CheckoutEntry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1397816220934444605L;
	private BookCopy copy;
	private Date dateofCheckout;
	private Date dueDate;
	private Fine fine;
	
	public CheckoutEntry(BookCopy copy, Date dateofCheckout, Date dueDate) {
		this.copy = copy;
		this.dateofCheckout = dateofCheckout;
		this.dueDate = dueDate;
		this.fine = new Fine(0.0, null);
	}
	
	public BookCopy getCopy() {
		return copy;
	}
	public void setCopy(BookCopy copy) {
		this.copy = copy;
	}
	public Date getDateofCheckout() {
		return dateofCheckout;
	}
	public void setDateofCheckout(Date dateofCheckout) {
		this.dateofCheckout = dateofCheckout;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Fine getFine() {
		return fine;
	}
	public void setFine(Fine fine) {
		this.fine = fine;
	}
}
