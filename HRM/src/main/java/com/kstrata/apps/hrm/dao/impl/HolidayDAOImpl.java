package com.kstrata.apps.hrm.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kstrata.apps.hrm.dao.HolidayDAO;
import com.kstrata.apps.hrm.model.Holiday;

@Repository
public class HolidayDAOImpl extends BaseDAOImpl implements HolidayDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public Holiday getHolidayById(Long id) {
		Query query = getEntityManager().createNamedQuery("Holiday.findById");
		query.setParameter("id", id);
		
		List<Holiday> holidays = query.getResultList();
		if(!holidays.isEmpty()) {
			return holidays.get(0);
		}
		return null;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public boolean validateDate(java.util.Date date)
	{
		Query query=getEntityManager().createNamedQuery("Holiday.validateDate");
		query.setParameter("date", date);
		List<Holiday> holidays = query.getResultList();
		
		if(!holidays.isEmpty()) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Holiday> getHolidaysList() {
		Query query = getEntityManager().createNamedQuery("Holiday.findAll");
		return query.getResultList();
	}

	@Override
	public void update(Holiday holiday) {
		getEntityManager().merge(holiday);
	}

	@Override
	public void deleteHoliday(Holiday holiday) {
		Holiday temp = getHolidayById(holiday.getId());
		if (temp != null) {
			getEntityManager().remove(temp);		
		}
	}

	@Override
	public void save(Holiday holiday) {
		getEntityManager().persist(holiday);
	}

}
