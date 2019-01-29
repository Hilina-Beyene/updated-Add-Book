package business.model;

import java.io.Serializable;

public class Librarian extends Role implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7097507251056954697L;

	public Librarian() {
		super(ROLETYPE.LIBRARIAN);
	}

}
