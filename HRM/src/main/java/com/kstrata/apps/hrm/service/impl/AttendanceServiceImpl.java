package com.kstrata.apps.hrm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kstrata.apps.hrm.dao.AttendanceDAO;
import com.kstrata.apps.hrm.model.Attendance;
import com.kstrata.apps.hrm.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceDAO attendanceDAO;
	
	
	@Override
	@Transactional(readOnly = false)
	public void save(List<Attendance> attendances) {
		for (Attendance attendance : attendances) {
			if (attendance.getId() != null) {
				attendanceDAO.update(attendance);
			} else {
				attendanceDAO.save(attendance);
			}
		}
	}


	@Override
	public List<Attendance> getAttendancesByDate(String selectedDate) {
		return attendanceDAO.getAttendancesByDate(selectedDate);
	}


	@Override
	public boolean findHolidayByDate(Date selectedDate) {
		return attendanceDAO.findHolidayByDate(selectedDate);
	}
	
}
