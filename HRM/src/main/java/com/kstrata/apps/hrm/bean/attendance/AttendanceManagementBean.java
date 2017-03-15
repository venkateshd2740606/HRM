package com.kstrata.apps.hrm.bean.attendance;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.richfaces.event.ItemChangeEvent;

import com.kstrata.apps.hrm.bean.common.BaseBean;
import com.kstrata.apps.hrm.bean.util.HRMUtil;

@ManagedBean
@ViewScoped
public class AttendanceManagementBean extends BaseBean {
	
	private String selectedTab = "Add_Attendance";
	
	public void tabChange(ItemChangeEvent event) {
		if ("Add_Attendance".equalsIgnoreCase(selectedTab)) {
			AttendanceBean attendanceBean = (AttendanceBean) HRMUtil.getFacesContext().getApplication().evaluateExpressionGet(HRMUtil.getFacesContext(), "#{leaveListingBean}", AttendanceBean.class);
			attendanceBean.init();
		}
	}
	
	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}

}
