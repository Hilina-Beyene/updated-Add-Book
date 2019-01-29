package business.model;

import java.io.Serializable;
import java.util.List;

public class Author extends Person implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4943938113363294457L;
	private String credentials;
	private String shortBio;
	private String phoneNumber;
	private String email;
	
	
	/*public Author(int id, String firstName, String lastName, Account account, 
			List<Role> role, Address address) {
		super(id, firstName, lastName, null,  null, address);
		// TODO Auto-generated constructor stub
	}*/
	public Author(String firstName, String lastName,Address address, String phoneNumber, String email) {
		super(0, firstName, lastName, null,  null, address);
		// TODO Auto-generated constructor stub
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCredentials() {
		return credentials;
	}
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	public String getShortBio() {
		return shortBio;
	}
	public void setShortBio(String shortBio) {
		this.shortBio = shortBio;
	}
}
