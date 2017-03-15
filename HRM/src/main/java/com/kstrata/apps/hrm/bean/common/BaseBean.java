package com.kstrata.apps.hrm.bean.common;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kstrata.apps.hrm.bean.util.HRMConstants;
import com.kstrata.apps.hrm.bean.util.HRMUtil;
import com.kstrata.apps.hrm.model.Employee;
import com.kstrata.apps.hrm.model.EmployeeLeave;

@Component
@SessionScoped
@ManagedBean(name="BaseBean")
public class BaseBean {
	
	private boolean hasWarns = false;
	private boolean hasErrors = false;
	private String role;
	
	private Employee employee;
	
	private EmployeeLeave employeeLeave;
	
	private String selectedEmpl;
	
	
	public String getSelectedEmpl() {
		return selectedEmpl;
	}

	public void setSelectedEmpl(String selectedEmpl) {
		this.selectedEmpl = selectedEmpl;
	}

	public EmployeeLeave getEmployeeLeave() {
		return employeeLeave;
	}

	public void setEmployeeLeave(EmployeeLeave employeeLeave) {
		this.employeeLeave = employeeLeave;
	}

	@Autowired
	private HRMHelper hrmHelper;
	
	@Autowired
	private NavigationHelper navigationHelper;
	
	@PostConstruct
	public void init() {
		Object obj = HRMUtil.getObjectFromSession(HRMConstants.LOGGED_USER);
		if (obj == null) {
			navigationHelper.navigateTo("/hrm/login.jsf");	
		} else {
			Employee emp = (Employee) obj;
			role = emp.getRole().getRoleName();
			employee = hrmHelper.updateEmployeeAnnualLeaveBal(emp);
			
			String accessURL = HRMUtil.getAccessedURL();
			if (!HRMUtil.isAdmin() && (/*accessURL.contains("holidays") || */accessURL.contains("attendance"))) {
				navigationHelper.navigateTo("/hrm/userDeniedException.jsf");	
			}
		}
	}
	
	public void renderMessages(List<String> messages, Severity severity) {
		for (String message : messages) {
			renderMessage(message, severity);
		}
	}
	
	public void renderMessage(String message, Severity severity) {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public boolean isHasWarns() {
		return hasWarns;
	}

	public void setHasWarns(boolean hasWarns) {
		this.hasWarns = hasWarns;
	}

	public boolean isHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public HRMHelper getHrmHelper() {
		return hrmHelper;
	}

	public void setHrmHelper(HRMHelper hrmHelper) {
		this.hrmHelper = hrmHelper;
	}

	public NavigationHelper getNavigationHelper() {
		return navigationHelper;
	}

	public void setNavigationHelper(NavigationHelper navigationHelper) {
		this.navigationHelper = navigationHelper;
	}
	
}
