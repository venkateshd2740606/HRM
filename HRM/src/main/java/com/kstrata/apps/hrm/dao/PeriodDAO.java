package com.kstrata.apps.hrm.dao;

import java.util.Date;

import com.kstrata.apps.hrm.model.Period;

public interface PeriodDAO {

	public void savePeriod(final Period period);

	public Period getPeriodById(Integer id);

	public Period getPeriod(Date date);

	public Integer getCountOfHolidaysByPeriod(Period period);
	
}
