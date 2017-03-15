package com.kstrata.apps.hrm.bean.common;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NavigationHelper {
	
	private static final Logger LOG = LoggerFactory.getLogger(NavigationHelper.class);
	
	public String navigateToLeaveManagement() {
		return "navigateToLeaveManagement";
	}
	
	public String navigateToAttendanceManagement() {
		return "navigateToAttendanceManagement";
	}
	
	public String navigateToEmployeeManagement() {
		return "navigateToEmployeeManagement";
	}
	
	public String navigateToHolidayManagement(){
		return "navigateToHolidayManagement";
	}
	
	public void navigateTo(String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (IOException e) {
			LOG.error("Exception in navigateTo() ", e);
		}
	}
}