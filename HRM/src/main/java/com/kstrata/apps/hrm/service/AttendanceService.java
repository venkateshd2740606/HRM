package com.kstrata.apps.hrm.service;

import java.util.Date;
import java.util.List;

import com.kstrata.apps.hrm.model.Attendance;

public interface AttendanceService {

	public void save(List<Attendance> attendance);

	public List<Attendance> getAttendancesByDate(String selectedDate);

	public boolean findHolidayByDate(Date selectedDate);
	
}
