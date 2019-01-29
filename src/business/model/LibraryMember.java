package business.model;

import java.io.Serializable;

public class LibraryMember extends Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7950058660651825776L;
	private String phoneNumber;
	private CheckoutRecord checkoutRecord;
	
	
	public LibraryMember( String phoneNumber) {
		super(ROLETYPE.LIBRAY_MEMBER);
		this.phoneNumber = phoneNumber;
		this.checkoutRecord = new CheckoutRecord();
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
	}
	
}
