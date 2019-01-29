package business.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3048530525391135058L;
	private LibraryMember member;
	private List<CheckoutEntry> entries;
	
	public CheckoutRecord(LibraryMember member) {
		super();
		this.setMember(member);
		this.setEntries(new ArrayList<CheckoutEntry>());
	}

	public CheckoutRecord() {
	}

	public LibraryMember getMember() {
		return member;
	}

	public void setMember(LibraryMember member) {
		this.member = member;
	}

	public List<CheckoutEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<CheckoutEntry> entries) {
		this.entries = entries;
	}

}
