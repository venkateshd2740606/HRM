package com.kstrata.apps.hrm.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kstrata.apps.hrm.model.Employee;
import com.kstrata.apps.hrm.model.EmployeeProject;
import com.kstrata.apps.hrm.model.Period;
import com.kstrata.apps.hrm.model.TimeSheet;

public interface TimeSheetDAO {
	
	public TimeSheet saveTimeSheet(TimeSheet transientInstance);

	public void approveTimeSheet(List<EmployeeProject> employeeProjects, Period period);

	public void denyTimeSheet(List<EmployeeProject> employeeProjects, Period period);

	public void deleteTimeSheet(TimeSheet persistentInstance);

	public TimeSheet getTimeSheetById(Integer id);

	public List<TimeSheet> findTimeSheets(List<EmployeeProject> employeeProjects);

	public List<TimeSheet> getTimeSheetsByDateRange(List<Integer> employeeProjectIds, Date startDate, Date endDate);

	public List<TimeSheet> findTimeSheets(List<EmployeeProject> employeeProjects, Integer periodId, String approveStatus);
	
	public List<TimeSheet> findTimeSheets(List<EmployeeProject> employeeProjects, Integer periodId, String approveStatus, String taskType);

	public List<TimeSheet> findTimeSheets(EmployeeProject employeeProjecttemp);

	public List<TimeSheet> findTimeSheets(EmployeeProject employeeProject, Period period);

	public List<TimeSheet> findByTaskDesc(Object taskDesc);

	public List<TimeSheet> findByTotal(Object total);

	public List<TimeSheet> findByStatus(Object status);

	public List<TimeSheet> findByMngrStatus(Object mngrStatus);

	public List<TimeSheet> findAll();

	public TimeSheet merge(TimeSheet detachedInstance);

	public void attachDirty(TimeSheet instance);

	public void attachClean(TimeSheet instance);

	public List<TimeSheet> findTimeSheetsForOverTime(EmployeeProject employeeProjecttemp);

	public List<TimeSheet> findTimeSheets(Integer projectId, Date taskDate);

	public List<TimeSheet> findTimeSheetsForOverTime(EmployeeProject employeeProject, Date taskDate);

	public Map<String, Integer> getCountOfTimeSheetsByPeriod(Period period, Employee employee);

	public boolean getSubmitFlagOfTimeSheetByPeriod(Period period, Employee employee);

	public void submitAllTaskInPeriod(Period otherPeriod, Employee employee);

	public void unSubmitAllTaskInPeriod(Period otherPeriod, Employee employee);

	public void unSubmitAllTaskInPeriod(Period period, List<EmployeeProject> employeeProjects);

	public void submitAllTaskInPeriod(Period period, List<EmployeeProject> employeeProjects);

	public void approvePeriod(Employee employee, Period otherPeriod);

	public void denyPeriod(Employee employee, Period otherPeriod);

}
