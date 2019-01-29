package business.model;

import java.io.Serializable;
import java.util.Date;

public class Fine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6844050325684705118L;
	private double amount;
	private Date datePaid;
	
	
	
	public Fine(double amount, Date datePaid) {
		this.amount = amount;
		this.datePaid = datePaid;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDatePaid() {
		return datePaid;
	}
	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
	}

	
}
