package com.kstrata.apps.hrm.bean.holidays;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.kstrata.apps.hrm.bean.common.BaseBean;
import com.kstrata.apps.hrm.model.Holiday;
import com.kstrata.apps.hrm.service.HolidayService;

@ManagedBean
@SessionScoped
public class HolidayListingBean extends BaseBean {
	
	List<Holiday> holidaysList;
	
	@Autowired
	private HolidayService holidayService;
	
	@PostConstruct
	public void init() {
		holidaysList = new ArrayList<>();
		loadHolidays();
	}	
	
	public void loadHolidays(){
		holidaysList = holidayService.getHolidaysList();
		for (Holiday holiday : holidaysList) {
			holiday.setEditable(false);
		}
	}
	
	public void editHoliday(Holiday holiday) {
		holiday.setEditable(true);
	}
	
	public void updateHoliday(Holiday holiday) {
		holidayService.update(holiday);
		loadHolidays();
	}
	public void cancelHoliday(Holiday holiday) {
		holiday.setEditable(false);
	}
	
	public void deleteHoliday(Holiday holiday) {
		holidayService.deleteHoliday(holiday);
		loadHolidays();
	}
	
	public List<Holiday> getHolidaysList() {
		return holidaysList;
	}

	public void setHolidaysList(List<Holiday> holidaysList) {
		this.holidaysList = holidaysList;
	}
}
