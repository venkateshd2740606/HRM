package com.kstrata.apps.hrm.service;

import java.util.Date;
import java.util.List;

import com.kstrata.apps.hrm.model.Holiday;

public interface HolidayService {
	public Holiday getHolidayById(Long id);
	public List<Holiday> getHolidaysList();
	public void update(Holiday holiday);
	public void deleteHoliday(Holiday holiday);
	public boolean validateDate(Date date);
	public void save(Holiday holiday);

}
