package com.kstrata.apps.hrm.dao;

import java.util.Date;
import java.util.List;

import com.kstrata.apps.hrm.model.Attendance;

public interface AttendanceDAO {

	public void save(Attendance attendance);

	public List<Attendance> getAttendancesByDate(String selectedDate);

	void update(Attendance attendance);

	public boolean findHolidayByDate(Date selectedDate);
	
}
