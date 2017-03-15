package com.kstrata.apps.hrm.bean.employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.richfaces.event.ItemChangeEvent;

import com.kstrata.apps.hrm.bean.common.BaseBean;
import com.kstrata.apps.hrm.bean.util.HRMUtil;

@ManagedBean
@ViewScoped
public class EmployeeManagementBean  extends BaseBean {
	
	private String selectedTab = "EmployeeDetails";
	
	private boolean hasWarns = false;
	private boolean hasErrors = false;
	
	public void tabChange(ItemChangeEvent event) {
		if (selectedTab.equalsIgnoreCase("EmployeeDetails")) {
			EmployeeDetailsBean employeeDetailsBean = (EmployeeDetailsBean) HRMUtil.getFacesContext().getApplication().evaluateExpressionGet(HRMUtil.getFacesContext(), "#{leaveListingBean}",EmployeeDetailsBean.class);
			employeeDetailsBean.init();
		}
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

	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}

}
