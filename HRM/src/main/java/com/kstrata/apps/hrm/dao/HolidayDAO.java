package com.kstrata.apps.hrm.dao;

import java.util.Date;
import java.util.List;

import com.kstrata.apps.hrm.model.Holiday;

public interface HolidayDAO {
	
	public List<Holiday> getHolidaysList();
	public Holiday getHolidayById(Long id);
	public void update(Holiday holiday);
	public void deleteHoliday(Holiday holiday);
	public boolean validateDate(Date date);
	public void save(Holiday holiday);	
}
