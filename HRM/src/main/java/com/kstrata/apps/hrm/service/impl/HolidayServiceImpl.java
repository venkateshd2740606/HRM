package com.kstrata.apps.hrm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kstrata.apps.hrm.dao.HolidayDAO;
import com.kstrata.apps.hrm.model.Holiday;
import com.kstrata.apps.hrm.service.HolidayService;

@Service
public class HolidayServiceImpl implements HolidayService {
	
	@Autowired
	private HolidayDAO holidayDAO;

	@Override
	public Holiday getHolidayById(Long id) {
		return holidayDAO.getHolidayById(id);
	}

	@Override
	public List<Holiday> getHolidaysList() {
		return holidayDAO.getHolidaysList();
	}	
	
	@Override
	@Transactional(readOnly=false)
	public void update(Holiday holiday) {
		holidayDAO.update(holiday);
	}
	
	@Override
	@Transactional(readOnly=false)
	public void save(Holiday holiday) {
		holidayDAO.save(holiday);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteHoliday(Holiday holiday) {
		holidayDAO.deleteHoliday(holiday);
	}

	@Override
	public boolean validateDate(Date date) {
		return holidayDAO.validateDate(date);
	}

}
