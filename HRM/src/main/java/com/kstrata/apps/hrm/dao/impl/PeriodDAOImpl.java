package com.kstrata.apps.hrm.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kstrata.apps.hrm.dao.PeriodDAO;
import com.kstrata.apps.hrm.model.Period;

/**
 * A data access object (DAO) providing persistence and search support for
 * Period entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.kstrata.apps.tsm.business.dao.entity.Period
 * @author MyEclipse Persistence Tools
 */
public class PeriodDAOImpl extends BaseDAOImpl implements PeriodDAO, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PeriodDAOImpl.class);
	// property constants

	@Override
	public void savePeriod(Period period) {
		getEntityManager().merge(period);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Period getPeriodById(Integer periodId) {
		Query query = getEntityManager().createQuery("select o from Period o where o.id = :periodId");
		query.setParameter("periodId", periodId.toString());
		List<Period> periods = query.getResultList();
		if (!periods.isEmpty()) {
			return periods.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Period getPeriod(Date date) {
		log.debug("finding period for a given date");
		Query query = getEntityManager().createQuery("SELECT * FROM Period WHERE :date BETWEEN dateFrom AND dateTo");
		query.setParameter("date", date);
		List<Period> periods = query.getResultList();
		if (!periods.isEmpty()) {
			return periods.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Period getPeriod(Integer periodId) {
		Query query = getEntityManager().createQuery("select * FROM Period WHERE periodId=" + periodId);
		List<Period> periods = query.getResultList();
		if (!periods.isEmpty()) {
			return periods.get(0);
		}
		return null;
	}
	
	@Override
	public Integer getCountOfHolidaysByPeriod(Period period) {
		Query query = getEntityManager().createQuery("SELECT COUNT(H.HOLIDAY_ID) FROM HOLIDAYS H, PERIOD P WHERE " +
			"H.HOLIDAY_DATE >= P.DATE_FROM AND H.HOLIDAY_DATE <= P.DATE_TO " +
			"AND P.PERIOD_ID=" + period.getPeriodId());
		return ((Long) query.getResultList().get(0)).intValue() + 2;
	}

}