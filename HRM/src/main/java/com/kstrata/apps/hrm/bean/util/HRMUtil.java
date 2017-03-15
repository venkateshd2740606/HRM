package com.kstrata.apps.hrm.bean.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.kstrata.apps.hrm.model.Employee;

public class HRMUtil {

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	public static ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}
	
	public static String getAccessedURL() {
		HttpServletRequest request = (HttpServletRequest) getExternalContext().getRequest();
		return request.getRequestURL().toString();
	}
	
	public static Map<String, Object> getSessionMap() {
		return getExternalContext().getSessionMap();
	}
	
	public static void putObjectInSession(String key, Object value) {
		getSessionMap().put(key, value);
	}
	
	public static Object getObjectFromSession(String key) {
		return getSessionMap().get(key);
	}
	
	public static int daysBetween(Date d1, Date d2) {
        return (int)((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	public static double getHours(Date d1, Date d2) {
        return (double)( (d2.getTime() - d1.getTime()) / (60 * 60 * 1000) % 24);
	}
	
	public static BigDecimal getHourMinFormat (Long timeInMilliSec) {
		Long diffHours = new Long(timeInMilliSec / (60 * 60 * 1000));
		Long diffMins = new Long((timeInMilliSec) / (60 * 1000));
		if (Math.abs(diffMins % 60) < 10)
			return new BigDecimal(Math.abs(diffHours) + ".0" + Math.abs(diffMins % 60));
		else
			return new BigDecimal(Math.abs(diffHours) + "." + Math.abs(diffMins % 60));
	}
	
	public static boolean isAdmin() {
		Employee employee = (Employee) HRMUtil.getObjectFromSession(HRMConstants.LOGGED_USER);
		if(employee.getRole().getRoleName().equalsIgnoreCase("admin")) {
			return true;
		}
		return false;
	}
	
	private HRMUtil() {
		
	}
}
