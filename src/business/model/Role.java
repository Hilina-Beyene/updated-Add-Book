package business.model;

import java.io.Serializable;

public abstract class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3654097422444727290L;

	
	private ROLETYPE roleValue;
	
	public Role(ROLETYPE roleValue) {
		this.roleValue = roleValue;
	}
	
	public ROLETYPE getRoleValue() {
		return roleValue;
	}
	public void setRoleValue(ROLETYPE roleValue) {
		this.roleValue = roleValue;
	}
	
}
