package business.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2008534799479046665L;
	
	private int id;
	private String firstName;
	private String lastName;
	private Account account; 
	private List<Role> roles;
	private Address address;

	public Person(int id, String firstName, String lastName, Role role, Address address) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.account = null;
		roles = new ArrayList<Role>();
		roles.add(role);
		this.address = address;
	} 
	
	public Person(int id, String firstName, String lastName,  Address address) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.account = null;
		this.address = address;
	} 
	
	public Person(int id, String firstName, String lastName, Account account, 
			List<Role> role, Address address) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.setAccount(account);
		roles = new ArrayList<Role>(); 
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object obj) {
		return (this.firstName.equals(((Person) obj).firstName)
				&& this.lastName.equals(((Person) obj).lastName) );
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRole(Role role) {
	   roles.add(role);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
