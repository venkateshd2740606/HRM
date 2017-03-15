package com.kstrata.apps.hrm.bean.attendance;

import static com.kstrata.apps.hrm.bean.util.HRMConstants.AVAILABLE;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.INVALID_HOURS;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.PLEASE_SELECT_DATE;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.PUBLIC_HOLIDAY;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.SATURDAY;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.SAVED_SUCCESSFULLY;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.SUNDAY;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.WEEKDAY;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.WEEKEND;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kstrata.apps.hrm.bean.common.BaseBean;
import com.kstrata.apps.hrm.bean.util.HRMConstants;
import com.kstrata.apps.hrm.bean.util.HRMUtil;
import com.kstrata.apps.hrm.model.Attendance;
import com.kstrata.apps.hrm.model.Employee;
import com.kstrata.apps.hrm.model.ProjectConstant;
import com.kstrata.apps.hrm.service.AttendanceService;
import com.kstrata.apps.hrm.service.EmployeeService;
import com.kstrata.apps.hrm.service.ProjectConstantService;

@ManagedBean
@SessionScoped
public class AttendanceBean extends BaseBean {
	
	private static final Logger LOG = LoggerFactory.getLogger(AttendanceBean.class);

	@Autowired 
	private EmployeeService employeeService;
	
	@Autowired
	private ProjectConstantService projectConstantService;
	
	@Autowired
	private AttendanceService attendanceService;
	
	private List<Integer> hoursList;
	private List<Integer> minutesList;
	private String selectedStatus;
	private Set<String> attendanceStatusList;
	private Date selectedDate;
	private List<String> days;
	private String weekDay;
	private String comments;
	
	private List<Employee> employees;
	
	private List<Attendance> attendances;
	
	private boolean hasWarns = false;
	private boolean hasErrors = false;	
	
	private boolean submitDisabled;
	
	public boolean isSubmitDisabled() {
		return submitDisabled;
	}

	public void setSubmitDisabled(boolean submitDisabled) {
		this.submitDisabled = submitDisabled;
	}

	@PostConstruct
	@Override
	public void init() {
		prepareAttendanceStatuList();
		prepareHours();
		prepareMinutes();
		prepareDays();
		loadAllEmployees();
		prepareAttedances();
		prepareDefaultData();
	}
	
	private void prepareDefaultData() {
		selectedDate = new Date();
		updateStatus();
	}
	
	private void prepareAttedances() {
		attendances = new ArrayList<Attendance>();
		for (Employee employee : employees) {
			Attendance attendance = new Attendance();
			attendance.setEmployee(employee);
			attendances.add(attendance);
		}
	}

	private void loadAllEmployees() {
		employees = employeeService.getAllEmployees();
	}
	
	public void saveAll() {
		List<String> messages = validate();
		if (!messages.isEmpty()) {
			setHasErrors(true);
			renderMessages(messages, FacesMessage.SEVERITY_ERROR);
		} else {
			updateSubmitStatus("Y");
			setSubmitDisabled(true);
			attendanceService.save(attendances);	
			renderMessage(SAVED_SUCCESSFULLY, FacesMessage.SEVERITY_INFO);
		}
	}
	
	private void updateSubmitStatus(String submitStatus) {
		for (Attendance attendance : attendances) {
				attendance.setSubmitStatus(submitStatus);
		}
	}
	private void updateAttendancesWithStatus() {
		for (Attendance attendance : attendances) {
			attendance.setStatus(selectedStatus);
		}
	}
	public void updateStatus() {
		if (dateCheck()) {
			attendances = attendanceService.getAttendancesByDate(new SimpleDateFormat("dd/MM/yyyy").format(selectedDate));
			if (attendances.isEmpty()) {
				updateSubmitStatus("Y");
				setSubmitDisabled(false);
				prepareAttedances();
				Calendar cal = Calendar.getInstance();
				cal.setTime(selectedDate);
				if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) || (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
					selectedStatus = WEEKEND;
					resetAttendances();
				} else {
					boolean isPublicHoliday = attendanceService.findHolidayByDate(selectedDate);
					if (isPublicHoliday) {
						selectedStatus = PUBLIC_HOLIDAY;
						resetAttendances();
					} else {
						selectedStatus = AVAILABLE;
					}
				}
				updateAttendancesWithStatus();
			} else {
				updateSubmitStatus("N");
				setSubmitDisabled(true);
			}
		}
	}	
	
	private boolean dateCheck() {
		boolean flag = true;
		if (selectedDate == null) {
			flag = false;
			renderMessage(PLEASE_SELECT_DATE, FacesMessage.SEVERITY_ERROR);
		}
		return flag;
	}
	
	public void unSubmit() {
		setSubmitDisabled(false);
		updateSubmitStatus("N");
		renderMessage("Un Submitted Successfully", FacesMessage.SEVERITY_INFO);
	}
	
	public void updateHours(Attendance attendance) {
		try {
			if (selectedDate != null) {
				String dateString = getDateString();
				
				String inTimeTimestampString = dateString + " " + attendance.getOrgInHour() + ":" + attendance.getOrgInMinute() + ":" + "00";
				String OutTimeTimestampString = dateString + " " + attendance.getOrgOutHour() + ":" + attendance.getOrgOutMinute() + ":" + "00";
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				
				Long inTime = dateFormat.parse(inTimeTimestampString).getTime();
				Long outTime = dateFormat.parse(OutTimeTimestampString).getTime();
				long diff = outTime - inTime;
				if (diff > 0) {
					attendance.setInTime(new Timestamp(inTime));
					attendance.setOutTime(new Timestamp(outTime));
					attendance.setHours(HRMUtil.getHourMinFormat(diff));
					setHasErrors(false);
				} else {
					setHasErrors(true);
					renderMessage("End time should greather than start date. Please check start and date end", FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception in updateHours()", e);
		}
	}
	
	public void resetAttendances() {
		for (Attendance attendance : attendances) {
			resetAttendance(attendance);
		}
	}
	
	
	public void resetAttendance(Attendance attendance) {
		attendance.setInHour(0);
		attendance.setInMinute(0);
		attendance.setOutHour(0);
		attendance.setOutMinute(0);
		attendance.setHours(new BigDecimal(0));
		attendance.setSubmitStatus("N");
	}
	
	private String getDateString() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(selectedDate);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(selectedDate);
	}
	
	private List<String> validate() {
		List<String> messages = new ArrayList<String>();
		if (selectedDate == null) {
			messages.add(PLEASE_SELECT_DATE);
			return messages;
		}
		for (Attendance attendance : attendances) {
			try {
				attendance.setSubmitStatus("Y");
				updateHours(attendance);
				attendance.setDate(new SimpleDateFormat("dd/MM/yyyy").format(selectedDate));
				
				if (attendance.getHours().doubleValue() < 0.0) {
					messages.add(attendance.getEmployee().getEmpFirstName() + " - " + INVALID_HOURS);
				}
			} catch (Exception e) {
				LOG.error("Exception in validate()", e);
			}
			
		}
		return messages;
	}

	public void prepareAttendanceStatuList() {
		attendanceStatusList = new TreeSet<String>();
		List<ProjectConstant> projectConstants = projectConstantService.getProjectConstantsByType(HRMConstants.ATTENDANCE_STATUS);
		for (ProjectConstant projectConstant : projectConstants) {
			attendanceStatusList.add(projectConstant.getShortName());
		}
	}
	
	public void prepareHours() {
		hoursList = new ArrayList<Integer>();
		for (int i = 0; i <= 24; i++) {
			hoursList.add(i);
		}
	}
	
	public void prepareMinutes() {
		minutesList = new ArrayList<Integer>();
		for (int i = 0; i <= 60; i++) {
			minutesList.add(i);
		}
	}
	
	public void prepareDays() {
		days = new ArrayList<String>();
		days.add(WEEKDAY);
		days.add(PUBLIC_HOLIDAY);
		days.add(SATURDAY);
		days.add(SUNDAY);
	}
	
	public List<Integer> getHoursList() {
		return hoursList;
	}
	public void setHoursList(List<Integer> hoursList) {
		this.hoursList = hoursList;
	}
	public List<Integer> getMinutesList() {
		return minutesList;
	}
	public void setMinutesList(List<Integer> minutesList) {
		this.minutesList = minutesList;
	}
	public String getSelectedStatus() {
		return selectedStatus;
	}
	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
	}
	public Set<String> getAttendanceStatusList() {
		return attendanceStatusList;
	}
	public void setAttendanceStatusList(Set<String> attendanceStatusList) {
		this.attendanceStatusList = attendanceStatusList;
	}
	public Date getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}
	public List<String> getDays() {
		return days;
	}
	public void setDays(List<String> days) {
		this.days = days;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDayy(String weekDay) {
		this.weekDay = weekDay;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	@Override
	public boolean isHasWarns() {
		return hasWarns;
	}

	@Override
	public void setHasWarns(boolean hasWarns) {
		this.hasWarns = hasWarns;
	}

	@Override
	public boolean isHasErrors() {
		return hasErrors;
	}

	@Override
	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}
	
}