package com.kstrata.apps.hrm.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kstrata.apps.hrm.dao.AttendanceDAO;
import com.kstrata.apps.hrm.model.Attendance;

@Repository
public class AttendanceDAOImpl extends BaseDAOImpl implements AttendanceDAO {

	@Override
	public void save(Attendance attendance) {
		getEntityManager().persist(attendance);
		
	}
	
	@Override
	public void update(Attendance attendance) {
		getEntityManager().merge(attendance);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Attendance> getAttendancesByDate(String selectedDate) {
		Query query = getEntityManager().createQuery("select a from Attendance a where a.date= :date");
		query.setParameter("date", selectedDate);
		List<Attendance> attendances = query.getResultList();
		return attendances;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean findHolidayByDate(Date selectedDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String selectedDateString = dateFormat.format(selectedDate);
		Query query = getEntityManager().createNativeQuery("SELECT id FROM holidays WHERE date = '" + selectedDateString + "' ");
		List<Object> holidays = query.getResultList();
		if (!holidays.isEmpty()) {
			return true;
		}
		return false;
	}
	
}
