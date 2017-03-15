package com.kstrata.apps.hrm.bean.leave;

import static com.kstrata.apps.hrm.bean.util.HRMConstants.AL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.AL_LEAVE_MIN_BAL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.END_DATE_CAN_NOT_BE_NULL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.FD_LEAVE_BAL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.HDAL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.HDSL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.HD_LEAVE_BAL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.INVALID_DATE_SELECTION;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.INVALID_HOURS;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.PENDING;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.SAVED_SUCCESSFULLY;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.SL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.START_DATE_CAN_NOT_BE_NULL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.REASON_EMPTY;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.DELETED_SUCCESSFULLY;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kstrata.apps.hrm.bean.common.BaseBean;
import com.kstrata.apps.hrm.bean.util.HRMConstants;
import com.kstrata.apps.hrm.bean.util.HRMUtil;
import com.kstrata.apps.hrm.model.Employee;
import com.kstrata.apps.hrm.model.EmployeeLeave;
import com.kstrata.apps.hrm.model.ProjectConstant;
import com.kstrata.apps.hrm.service.EmployeeService;
import com.kstrata.apps.hrm.service.LeaveService;
import com.kstrata.apps.hrm.service.ProjectConstantService;

@ManagedBean
@SessionScoped
public class ApplyLeaveBean extends BaseBean {
	
	private static final Logger LOG = LoggerFactory.getLogger(ApplyLeaveBean.class);
	
	@Autowired
	private LeaveService leaveService;
	
	@Autowired 
	private EmployeeService employeeService;
	
	@Autowired
	private ProjectConstantService projectConstantService;
	
	private List<EmployeeLeave> employeeLeaves;	

	private EmployeeLeave employeeLeave;
	private String selectedLeaveType;
	private Map<Long, ProjectConstant> leaveProjectConstantsMap;
	
	private Map<String, ProjectConstant> statusProjectConstantsMap;
	
	private List<FacesMessage> messages = new ArrayList<FacesMessage>();
	private String reason;
	
	private Employee employee;
	
	private Employee selectedEmp;

	private double days;
	
	private String selectedEmployee;
	private List<String> employeesForSelect;
	private Map<String, Long> employeesMap = new HashMap<String, Long>();
	
	@ManagedProperty(value="#{BaseBean}")
	private BaseBean baseBean;
	
	@PostConstruct
	@Override
	public void init() {
		employee = (Employee) HRMUtil.getObjectFromSession(HRMConstants.LOGGED_USER);	
		if(baseBean.getSelectedEmpl()!=null)		
		{
			selectedEmployee=baseBean.getSelectedEmpl();
			if(!baseBean.getSelectedEmpl().equals("--Please Select Employee--"))
				loadEmployee();
		}		
		loadLeaveTypes();
		loadLeaveStatusTypes();
		prepareEmployees();	
		
		if(baseBean.getEmployeeLeave()==null)		
		{
			employeeLeave = new EmployeeLeave();
			days = 0.0;
			reason = null;
		}
		else
		{
			employeeLeave = baseBean.getEmployeeLeave();
			days = employeeLeave.getDays();
			reason=employeeLeave.getReasonString();
			System.out.println(selectedEmployee);
			Employee emp=baseBean.getEmployeeLeave().getEmployee();
			selectedEmployee = emp.getEmpId() + " - " + emp.getEmpFirstName() + " " + emp.getEmpLastName();
		}

		/*if (HRMUtil.isAdmin()) {
			prepareAllEmployeeLeaves();
		} else {
			prepareEmployeeLeaves();
		}*/
	}
	
	public BaseBean getBaseBean() {
		return baseBean;
	}

	public void setBaseBean(BaseBean baseBean) {
		this.baseBean = baseBean;
	}

	private void prepareEmployees() {
		employeesForSelect = new ArrayList<String>();
		employeesForSelect.add("--Please Select Employee--");
		List<Employee> employees = employeeService.getAllEmployees();
		for (Employee emp : employees) {
			String item = emp.getEmpId() + " - " + emp.getEmpFirstName() + " " + emp.getEmpLastName();
			employeesForSelect.add(item);
			employeesMap.put(item, emp.getEmpId());
		}
	}
	
	public void loadEmployee() {
		selectedEmp = employeeService.getEmployeeById(employeesMap.get(selectedEmployee));
		prepareEmployeeLeaves();
	}
	
	private void loadLeaveStatusTypes() {
		statusProjectConstantsMap = new HashMap<String, ProjectConstant>();
		List<ProjectConstant> projectConstants = projectConstantService.getProjectConstantsByType(HRMConstants.STATUS);
		for (ProjectConstant projectConstant : projectConstants) {
			statusProjectConstantsMap.put(projectConstant.getName(), projectConstant);
		}
	}
	
	private void loadLeaveTypes() {
		leaveProjectConstantsMap = new HashMap<Long, ProjectConstant>();
		List<ProjectConstant> projectConstants = projectConstantService.getProjectConstantsByType(HRMConstants.LEAVE);
		for (ProjectConstant projectConstant : projectConstants) {
			leaveProjectConstantsMap.put(projectConstant.getId(), projectConstant);
		}
	}
	
	public void editEmployeeLeave(Long leaveId) {
		employeeLeave = leaveService.getEmployeeLeaveById(leaveId);
		employeeLeave.setDateStart(new Date(employeeLeave.getStartDate().getTime()));
		employeeLeave.setDateEnd(new Date(employeeLeave.getEndDate().getTime()));
		selectedLeaveType = employeeLeave.getLeaveProjectConstant().getId().toString();
		days = employeeLeave.getDays();
		reason = employeeLeave.getReasonString();
		if (HRMUtil.isAdmin()) {
			Employee emp = employeeLeave.getEmployee();
			selectedEmployee = emp.getEmpId() + " - " + emp.getEmpFirstName() + " " + emp.getEmpLastName();
			loadEmployee();
		}
	}

	public void save() {
		List<String> facesMessages = validate();
		if (!facesMessages.isEmpty()) {
			setHasErrors(true);
			renderMessages(facesMessages, FacesMessage.SEVERITY_ERROR);
		} else {
			setHasErrors(false);
			employeeLeave.setLeaveProjectConstant(leaveProjectConstantsMap.get(Long.parseLong(selectedLeaveType)));
			if (HRMUtil.isAdmin()) {
				employeeLeave.setEmployee(selectedEmp);
				employeeLeave.setDays(days);
				renderMessage("Applied employee leave", FacesMessage.SEVERITY_WARN);
			} else {
				employeeLeave.setEmployee(employee);
			}
			employeeLeave.setStatusProjectConstant(statusProjectConstantsMap.get(PENDING));
			employeeLeave.setReason(reason.getBytes());
			employeeLeave.setStartDate(new Timestamp(employeeLeave.getDateStart().getTime()));
			employeeLeave.setEndDate(new Timestamp(employeeLeave.getDateEnd().getTime()));
			leaveService.saveOrUpdate(employeeLeave);
			renderMessage(SAVED_SUCCESSFULLY, FacesMessage.SEVERITY_INFO);
			prepareEmployeeLeaves();
			resetFields();
		}
	}
	
	public void resetFields() {
		employeeLeave = new EmployeeLeave();
		days = 0.0;
		reason = null;
	}

	public String navigateToLeaveListing() {
		return "navigateToLeaveListing";
	}
	
	public void updateDays() {
		ProjectConstant projectConstant = leaveProjectConstantsMap.get(Long.parseLong(selectedLeaveType));
		String leaveType = projectConstant.getShortName();
		Date start = employeeLeave.getDateStart();
		Date end = employeeLeave.getDateEnd();
		
		if (start != null && end != null) {
			if ((SL.equalsIgnoreCase(leaveType)) || (AL.equalsIgnoreCase(leaveType))) {
				days = HRMUtil.daysBetween(start, end);
				if (days >= 0) {
					days = days + 1;
				}
			} else {
				days = 0.5;
			}
		}
	}
	
	public void showEndDate() {
		Date start = employeeLeave.getDateStart();
		if (start != null) {
			try {
				List<Date> dates = new ArrayList<Date>();
				Calendar c = Calendar.getInstance();
				c.setTime(employeeLeave.getDateStart());
				dates.add(c.getTime());
				c.add(Calendar.DATE, 1);
			}
			catch (Exception e) {
				LOG.error("Exception in showEndDate() ", e);
			}
				
		}
		
	}
	
	public void deleteEmployeeLeave(EmployeeLeave employeeLeave) {
		leaveService.deleteEmployeeLeave(employeeLeave);
		renderMessage(DELETED_SUCCESSFULLY, FacesMessage.SEVERITY_INFO);
		resetFields();
		prepareEmployeeLeaves();
	}
	
	private void prepareEmployeeLeaves() {
		List<String> statusList =  Arrays.asList(PENDING);
		if(selectedEmp!=null)
		{
			List<EmployeeLeave> list = leaveService.getLeavesByEmployee(selectedEmp, statusList);
			employeeLeaves =list;
			if(list.isEmpty())
			employeeLeaves=null;
		}
		else
			employeeLeaves = leaveService.getLeavesByEmployee(employee, statusList);
	}
	
	private void prepareAllEmployeeLeaves() {
		employeeLeaves = leaveService.getEmployeeLeavesByStatus(PENDING);
	}
	


	private List<String> validate() {
		List<String> facesMessages = new ArrayList<String>();
		Date start = employeeLeave.getDateStart();
		Date end = employeeLeave.getDateEnd();
		if(employeeLeaves!=null)
		for (EmployeeLeave empLeave : employeeLeaves) {
			String empDetails = empLeave.getEmployee().getEmpId() + " - " + 
					empLeave.getEmployee().getEmpFirstName() + " " + empLeave.getEmployee().getEmpLastName();
			if(empDetails.equals(selectedEmployee))
			{
				Date listStartDate = empLeave.getStartDate();
				Date listEndDate = empLeave.getEndDate();
				
				if(listStartDate.compareTo(start)==0 || listEndDate.compareTo(end)==0)
				{
					facesMessages.add("Leave already applied on entered date");
					return facesMessages;
				}
				if(listStartDate.compareTo(start)>0 && listEndDate.compareTo(end)<0)
				{
					facesMessages.add("Leave already applied on some of the entered dates");
					return facesMessages;
				}
				break;
			}
			
		}
		
		
		ProjectConstant projectConstant = leaveProjectConstantsMap.get(Long.parseLong(selectedLeaveType));
		String leaveType = projectConstant.getShortName();
		
		if (start == null) {
			facesMessages.add(START_DATE_CAN_NOT_BE_NULL);
			return facesMessages;
		} 
		if (end == null) {
			facesMessages.add(END_DATE_CAN_NOT_BE_NULL);
			return facesMessages;
		}
		if (reason == null || reason.isEmpty()) {
			facesMessages.add(REASON_EMPTY);
			return facesMessages;
		}
		
		double noOfDays = HRMUtil.daysBetween(start, end) + 1;
		if( noOfDays <= 0)
		{
			facesMessages.add("Select appropriate Start and End dates");
			return facesMessages;
		}
		double sickLeaveBal = employee.getSickLeaveBal();
		double alLeaveDays = 0.0;
		double hdalLeaveDays = 0.0;
		
		if (!HRMUtil.isAdmin()) {
			if (SL.equalsIgnoreCase(leaveType)) {
				if (noOfDays >= FD_LEAVE_BAL) {
					if (sickLeaveBal >= noOfDays) {
						employeeLeave.setDays(noOfDays);
					} else {
						facesMessages.add("Your sick leave balance is " + employee.getSickLeaveBal().doubleValue() + " . Please check your leaves");
					}
				} else {
					facesMessages.add(INVALID_DATE_SELECTION);
				}
			} else if (HDSL.equalsIgnoreCase(leaveType)) {
				if (noOfDays == 1.0) {
					if (sickLeaveBal >= HD_LEAVE_BAL) {
						employeeLeave.setDays(HD_LEAVE_BAL);
					} else {
						facesMessages.add("Your sick leave balance is " + employee.getSickLeaveBal().doubleValue() + " . Please check your leaves");
					}
				} else {
					facesMessages.add(INVALID_HOURS);
				}
			} else if (AL.equalsIgnoreCase(leaveType)) {
				if (noOfDays >= FD_LEAVE_BAL) {
					if (employee.getAnnualLeaveBal().doubleValue() >= AL_LEAVE_MIN_BAL) {
						alLeaveDays = noOfDays;
						employeeLeave.setDays(noOfDays);
					} else {
						facesMessages.add("Your annual leave balance is " + AL_LEAVE_MIN_BAL + " . Please check your leaves");
					}
				} else {
					facesMessages.add(INVALID_DATE_SELECTION);
				}
			}  else if (HDAL.equalsIgnoreCase(leaveType)) {
				if (noOfDays == 1.0) {
					if (employee.getAnnualLeaveBal().doubleValue() >= AL_LEAVE_MIN_BAL) {
						hdalLeaveDays = HD_LEAVE_BAL;
						employeeLeave.setDays(HD_LEAVE_BAL);
					} else {
						facesMessages.add("Your annual leave balance is " + AL_LEAVE_MIN_BAL + " . Please check your leaves");
					}
				} else {
					facesMessages.add(INVALID_HOURS);
				}
			}
			
			if (AL.equalsIgnoreCase(leaveType) || HDAL.equalsIgnoreCase(leaveType)) {
				double balance = getHrmHelper().getEmployeeAnnualBal(employee, alLeaveDays, hdalLeaveDays);
				if (balance >= AL_LEAVE_MIN_BAL) {
					employee.setAnnualLeaveBal(balance);
				} else {
					facesMessages.add("Annual Leave Balance is reached to " + AL_LEAVE_MIN_BAL + ".");
				}
			}
		} else {
			if (selectedEmp == null) {
				facesMessages.add("Selected Employee can not be null");
			} else if (AL.equalsIgnoreCase(leaveType) || HDAL.equalsIgnoreCase(leaveType)) {
				double balance = getHrmHelper().getEmployeeAnnualBal(employee, alLeaveDays, hdalLeaveDays);
				selectedEmp.setAnnualLeaveBal(balance);
			}
		}
		
		return facesMessages;
		
	}


	public List<EmployeeLeave> getEmployeeLeaves() {
		return employeeLeaves;
	}

	public void setEmployeeLeaves(List<EmployeeLeave> employeeLeaves) {
		this.employeeLeaves = employeeLeaves;
	}

	public EmployeeLeave getEmployeeLeave() {
		return employeeLeave;
	}

	public void setEmployeeLeave(EmployeeLeave employeeLeave) {
		this.employeeLeave = employeeLeave;
	}

	public String getSelectedLeaveType() {
		return selectedLeaveType;
	}

	public void setSelectedLeaveType(String selectedLeaveType) {
		this.selectedLeaveType = selectedLeaveType;
	}

	public List<FacesMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<FacesMessage> messages) {
		this.messages = messages;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public Employee getEmployee() {
		if (null == employee) {
			employee = new Employee();
		}
		return employee;
	}

	@Override
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Map<Long, ProjectConstant> getLeaveProjectConstantsMap() {
		return leaveProjectConstantsMap;
	}

	public void setLeaveProjectConstantsMap(Map<Long, ProjectConstant> leaveProjectConstantsMap) {
		this.leaveProjectConstantsMap = leaveProjectConstantsMap;
	}

	public Map<String, ProjectConstant> getStatusProjectConstantsMap() {
		return statusProjectConstantsMap;
	}

	public void setStatusProjectConstantsMap(Map<String, ProjectConstant> statusProjectConstantsMap) {
		this.statusProjectConstantsMap = statusProjectConstantsMap;
	}

	public double getDays() {
		return days;
	}

	public void setDays(double days) {
		this.days = days;
	}

	public String getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(String selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public List<String> getEmployeesForSelect() {
		return employeesForSelect;
	}

	public void setEmployeesForSelect(List<String> employeesForSelect) {
		this.employeesForSelect = employeesForSelect;
	}

	public Map<String, Long> getEmployeesMap() {
		return employeesMap;
	}

	public void setEmployeesMap(Map<String, Long> employeesMap) {
		this.employeesMap = employeesMap;
	}

	public Employee getSelectedEmp() {
		return selectedEmp;
	}

	public void setSelectedEmp(Employee selectedEmp) {
		this.selectedEmp = selectedEmp;
	}

}
