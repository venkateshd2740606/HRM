package com.kstrata.apps.hrm.bean.holidays;

import static com.kstrata.apps.hrm.bean.util.HRMConstants.PLEASE_SELECT_DATE;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.SAVED_SUCCESSFULLY;

import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.kstrata.apps.hrm.bean.common.BaseBean;
import com.kstrata.apps.hrm.model.Holiday;
import com.kstrata.apps.hrm.service.HolidayService;

@ManagedBean
@SessionScoped
public class AddHolidayBean extends BaseBean {
	
	@Autowired
	private HolidayService holidayService;	
	
	private Holiday holiday;
	
	@PostConstruct
	public void init() {
		holiday = new Holiday();
	}
	
	public void save() {
		List<String> facesMessages = validate();
		if (!facesMessages.isEmpty()) {
			setHasErrors(true);
			renderMessages(facesMessages, FacesMessage.SEVERITY_ERROR);
		} else {
			setHasErrors(false);
			holiday.setStatus("Full Day");
			holidayService.save(holiday);
			renderMessage(SAVED_SUCCESSFULLY, FacesMessage.SEVERITY_INFO);			
			resetFields();
		}
	}
	
	public void resetFields() {
		holiday = new Holiday();	
	}	

	private List<String> validate() {
		List<String> facesMessages = new ArrayList<String>();
		Date holidayDate = holiday.getDate();
		String holidayName = holiday.getName();		
		
		if(holidayName == null || holidayName == "") {
			facesMessages.add("Please enter Holiday name!");
			return facesMessages;
		}
		
		if(holidayDate == null)
		{
			facesMessages.add(PLEASE_SELECT_DATE);
			return facesMessages;
		}
		
		boolean isHolidayExist = holidayService.validateDate(holidayDate);
		if(isHolidayExist) {
			facesMessages.add("Duplicate holiday! Entered date is an existing in holidays");	
		}
		
		return facesMessages;		
	}

	public Holiday getHoliday() {
		return holiday;
	}

	public void setHoliday(Holiday holiday) {
		this.holiday = holiday;
	}

}
