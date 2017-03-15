package com.kstrata.apps.hrm.bean.leave;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.richfaces.event.ItemChangeEvent;

import static com.kstrata.apps.hrm.bean.util.HRMUtil.getFacesContext;

@ManagedBean
@ViewScoped
public class LeaveManagementBean {
	
	private String selectedTab = "LEAVELIST";
	
	public void tabChange(ItemChangeEvent event) {
		if ("LEAVELIST".equalsIgnoreCase(selectedTab)) {
			LeaveListingBean leaveListingBean = (LeaveListingBean) getFacesContext().getApplication().evaluateExpressionGet(getFacesContext(), "#{leaveListingBean}", LeaveListingBean.class);
			leaveListingBean.init();
		} else {
			ApplyLeaveBean applyLeaveBean = (ApplyLeaveBean) getFacesContext().getApplication().evaluateExpressionGet(getFacesContext(), "#{applyLeaveBean}", ApplyLeaveBean.class);
			applyLeaveBean.init();
		}
	}
	
	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}
	
}
