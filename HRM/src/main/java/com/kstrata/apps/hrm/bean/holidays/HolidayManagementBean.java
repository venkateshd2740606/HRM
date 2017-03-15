package com.kstrata.apps.hrm.bean.holidays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.richfaces.event.ItemChangeEvent;

import static com.kstrata.apps.hrm.bean.util.HRMUtil.getFacesContext;

@ManagedBean
@ViewScoped
public class HolidayManagementBean {
	
	private String selectedTab = "HOLIDAYSLIST";
	
	public void tabChange(ItemChangeEvent event) {
		if ("HOLIDAYSLIST".equalsIgnoreCase(selectedTab)) {
			HolidayListingBean holidayListingBean = (HolidayListingBean) getFacesContext().getApplication().evaluateExpressionGet(getFacesContext(), "#{holidayListingBean}", HolidayListingBean.class);			
			holidayListingBean.init();
		} else {
			AddHolidayBean addHolidayBean = (AddHolidayBean) getFacesContext().getApplication().evaluateExpressionGet(getFacesContext(), "#{addHolidayBean}", AddHolidayBean.class);
			addHolidayBean.init();
		}
	}
	
	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}
	
}
