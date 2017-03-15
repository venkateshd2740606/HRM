package com.kstrata.apps.hrm.bean.common;

/**
 * The Enumeration used as the home page path for 
 * different user roles.
 */
public enum UserHomePath {
	
	/** Main Menu Screen Path. */
	HOME("secure/registrationFormBeanValidation.jsf"),
	
	USER_DENIED("userDeniedException.jsf");
	
	/** The home page path. */
	private String path;
	
	/**
	 * Instantiates a new user home path.
	 *
	 * @param path the home page path
	 */
	private UserHomePath(String path) {
		this.path = path;
		
	}
	
	/**
	 * Gets the home page path.
	 *
	 * @return the home page path
	 */
	public String getPath() {
		return this.path;
	}
	
	
}