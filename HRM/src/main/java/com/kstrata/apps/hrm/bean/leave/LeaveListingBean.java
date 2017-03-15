package com.kstrata.apps.hrm.bean.leave;

import static com.kstrata.apps.hrm.bean.util.HRMConstants.AL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.AL_LEAVE_MIN_BAL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.APPROVE;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.APPROVED;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.DECLINE;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.DECLINED;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.FD_LEAVE_BAL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.HDAL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.HDSL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.HD_LEAVE_BAL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.LEAVE;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.PENDING;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.PLEASE_SELECT;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.PLEASE_SELECT_STATUS;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.SL;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.UPDATED_SUCCESSFULLY;
import static com.kstrata.apps.hrm.bean.util.HRMConstants.DELETED_SUCCESSFULLY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

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
public class LeaveListingBean extends BaseBean {
	
	private Employee employee;
	
	private Employee selectedEmp;
	
	@Autowired
	private LeaveService leaveService;
	
	@Autowired 
	private EmployeeService employeeService;
	
	@Autowired
	private ProjectConstantService projectConstantService;
	
	private List<EmployeeLeave> pendingAllEmployeeLeaves;
	
	private List<EmployeeLeave> pendingEmployeeLeaves;
	private List<EmployeeLeave> grantedEmployeeLeaves;
	private String selectedStatusType;
	private List<String> statusTypes;
	private Map<String, String> statusTypesMap = new HashMap<String, String>();
	private String comments;
	
	private String selectedEmployee;
	private List<String> employeesForSelect;
	private Map<String, Long> employeesMap = new HashMap<String, Long>();
	
	private Map<String, ProjectConstant> projectConstantsMap = new HashMap<String, ProjectConstant>();
	
	private String selectedRadio = "All Employees";
	
	private Map<String, ProjectConstant> statusProjectConstantsMap;
	
	@ManagedProperty(value="#{BaseBean}")
	private BaseBean baseBean;
	
	
	@PostConstruct
	@Override
	public void init() {
		employee = (Employee) HRMUtil.getObjectFromSession(HRMConstants.LOGGED_USER);
		prepareStatusTypes();
		prepareEmployees();
		loadLeaveStatusTypes();
		prepareEmployeeCurrentLeaves(employee);
		prepareEmployeePassedLeaves(employee);
		loadLeaveTypes();
		loadData();
	}
	
	public void loadAllPendingLeaves() {
		pendingAllEmployeeLeaves = new ArrayList<EmployeeLeave>();
		List<EmployeeLeave> allEmpLeaves = leaveService.getEmployeeLeavesByStatus(PENDING);
		pendingAllEmployeeLeaves.addAll(allEmpLeaves);
	}
	
	public void loadData() {
		if ("All Employees".equalsIgnoreCase(selectedRadio)) {
			loadAllPendingLeaves();
		} else {
			pendingAllEmployeeLeaves.clear();
			loadEmployeesLeaves();
		}
	}
	
	private void prepareEmployees() {
		employeesForSelect = new ArrayList<String>();
		employeesForSelect.add("--Please Select Employee--");
		List<Employee> employees = employeeService.getAllEmployees();
		//List<String> statusList =  Arrays.asList(DECLINED, APPROVED);
		for (Employee emp : employees) {
			/*List<EmployeeLeave> leaves =  leaveService.getLeavesByEmployee(emp, statusList);
			
			ProjectConstant p = projectConstantsMap.get(SL);
			for (EmployeeLeave leave : leaves) {
				String reason = new String(leave.getReason()).toLowerCase();
				if (reason != null && (reason.contains("sick"))) {
					leave.setLeaveProjectConstant(p);
					leaveService.updateEmployeeLeave(leave);
				}
			}
			*/
			
			
			
			
			String item = emp.getEmpId() + " - " + emp.getEmpFirstName() + " " + emp.getEmpLastName();
			employeesForSelect.add(item);
			employeesMap.put(item, emp.getEmpId());
		}
	}
	
	public void loadEmployeesLeaves() {		
		loadEmployee();
		if(selectedEmp==null)
		{
			pendingEmployeeLeaves=null;
			grantedEmployeeLeaves=null;
			baseBean.setSelectedEmpl("--Please Select Employee--");
			baseBean.setEmployeeLeave(null);
			return;
		}
		baseBean.setSelectedEmpl(selectedEmployee);
		if (selectedEmp != null && selectedEmp.getEmpId() != null) {
			prepareEmployeeCurrentLeaves(selectedEmp);
			prepareEmployeePassedLeaves(selectedEmp);
		}
	}
	
	private void loadEmployee() {
		selectedEmp = employeeService.getEmployeeById(employeesMap.get(selectedEmployee));
	}
	
	public void changeEmployee(Employee employee) {
		this.employee = employee;
		prepareEmployeeCurrentLeaves(employee);
		prepareEmployeePassedLeaves(employee);
	}

	private void prepareEmployeePassedLeaves(Employee emp) {
		grantedEmployeeLeaves = new ArrayList<EmployeeLeave>();
		//List<String> statusList =  Arrays.asList(DECLINED, APPROVED);
		grantedEmployeeLeaves.addAll(leaveService.getEmployeePassedLeaves(emp));
	}

	private void prepareEmployeeCurrentLeaves(Employee emp) {
		pendingEmployeeLeaves = new ArrayList<EmployeeLeave>();
		///List<String> statusList =  Arrays.asList(PENDING);
		pendingEmployeeLeaves.addAll(leaveService.getEmployeeCurrentLeaves(emp));
	}

	private void prepareStatusTypes() {
		statusTypes = new ArrayList<String>();
		statusTypes.add(PLEASE_SELECT);
		statusTypes.add(APPROVE);
		statusTypes.add(DECLINE);
		statusTypesMap.put(APPROVE, APPROVED);
		statusTypesMap.put(DECLINE, DECLINED);
	}
	
	private void loadLeaveStatusTypes() {
		statusProjectConstantsMap = new HashMap<String, ProjectConstant>();
		List<ProjectConstant> projectConstants = projectConstantService.getProjectConstantsByType(HRMConstants.STATUS);
		for (ProjectConstant projectConstant : projectConstants) {
			statusProjectConstantsMap.put(projectConstant.getName(), projectConstant);
		}
	}
	
	public void editEmployeeLeave(EmployeeLeave employeeLeave, Employee employee) {
		employeeLeave.setStatusProjectConstant(statusProjectConstantsMap.get(PENDING));
		employeeLeave.setDateStart(employeeLeave.getStartDate());
		employeeLeave.setDateEnd(employeeLeave.getEndDate());
		employeeLeave.setEmployee(employee);
		baseBean.setEmployeeLeave(employeeLeave);
		
		leaveService.updateEmployeeLeave(employeeLeave);
	}
	
	public void deleteEmployeeLeave(EmployeeLeave employeeLeave, String leaveString) {
	
		leaveService.deleteEmployeeLeave(employeeLeave);
		
		if(leaveString.equals("Passed"))
			grantedEmployeeLeaves.remove(employeeLeave);
		else if(leaveString.equals("Current"))
			pendingEmployeeLeaves.remove(employeeLeave);

		renderMessage(DELETED_SUCCESSFULLY, FacesMessage.SEVERITY_INFO);
	}
	
	public void updateEmployeeLeave(EmployeeLeave employeeLeave, Employee employee) {
		List<String> messages = validateLeave(employeeLeave, employee);
		
		if (messages.isEmpty()) {
			setHasErrors(false);
			employeeLeave.setStatusProjectConstant(statusProjectConstantsMap.get(statusTypesMap.get(employeeLeave.getMgrStatus())));
			if (!StringUtils.isEmpty(comments)) {
				employeeLeave.setComments(comments.getBytes());
			}
			leaveService.updateEmployeeLeave(employeeLeave);
			employeeService.saveEmployee(employee);
			renderMessage(UPDATED_SUCCESSFULLY, FacesMessage.SEVERITY_INFO);
			if ("All Employees".equalsIgnoreCase(selectedRadio)) {
				loadAllPendingLeaves();
			} else {
				loadEmployee();
				prepareEmployeeCurrentLeaves(employee);
				prepareEmployeePassedLeaves(employee);
			}
			
		} else {
			setHasErrors(true);
			renderMessages(messages, FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public BaseBean getBaseBean() {
		return baseBean;
	}

	public void setBaseBean(BaseBean baseBean) {
		this.baseBean = baseBean;
	}

	private List<String> validateLeave(EmployeeLeave employeeLeave, Employee employee) {
		List<String> facesMessages = new ArrayList<String>();
		String status = statusTypesMap.get(employeeLeave.getMgrStatus());
		
		if (status != null) {
			ProjectConstant leaveProjectConstant = employeeLeave.getLeaveProjectConstant();
			String leaveType = leaveProjectConstant.getShortName();
			
			if (LEAVE.equalsIgnoreCase(leaveProjectConstant.getType()) && APPROVED.equalsIgnoreCase(status)) {
				double sickLeaveBal = employee.getSickLeaveBal();
				double alLeaveDays = 0.0;
				double hdalLeaveDays = 0.0;
				if (employeeLeave.getDays() >= AL_LEAVE_MIN_BAL && AL.equalsIgnoreCase(leaveType)) {
					alLeaveDays = employeeLeave.getDays();
				} else if (employeeLeave.getDays() >= AL_LEAVE_MIN_BAL && HDAL.equalsIgnoreCase(leaveType)) {
					hdalLeaveDays = HD_LEAVE_BAL;
				} else if (sickLeaveBal >= employeeLeave.getDays() && SL.equalsIgnoreCase(leaveType)) {
					employee.setSickLeaveBal(sickLeaveBal - employeeLeave.getDays()); 
				} else if (sickLeaveBal >= HD_LEAVE_BAL && HDSL.equalsIgnoreCase(leaveType)) {
					employee.setSickLeaveBal(sickLeaveBal - HD_LEAVE_BAL); 
				} else if (new Double(sickLeaveBal).compareTo(new Double(0.0)) == 0) {
					if (SL.equalsIgnoreCase(leaveType)) {
						alLeaveDays = FD_LEAVE_BAL;
						employeeLeave.setLeaveProjectConstant(projectConstantsMap.get(AL));
					} else if (HDSL.equalsIgnoreCase(leaveType)) {
						hdalLeaveDays = HD_LEAVE_BAL;
						employeeLeave.setLeaveProjectConstant(projectConstantsMap.get(HDAL));
					}
				} else if (new Double(sickLeaveBal).compareTo(new Double(HD_LEAVE_BAL)) == 0 && SL.equalsIgnoreCase(leaveType)) {
					alLeaveDays = FD_LEAVE_BAL;
					employeeLeave.setLeaveProjectConstant(projectConstantsMap.get(AL));
				}
				
				double balance = getHrmHelper().getEmployeeAnnualBal(employee, alLeaveDays, hdalLeaveDays);
				employee.setAnnualLeaveBal(balance);
			}
		} else {
			facesMessages.add(PLEASE_SELECT_STATUS);
		}
		
		return facesMessages;
		
	}
	
	public void editEmployeeLeave(EmployeeLeave employeeLeave) {
		employeeLeave.setEditMode(true);
	}
	
	private void loadLeaveTypes() {
		List<ProjectConstant> projectConstants = projectConstantService.getProjectConstantsByType(HRMConstants.LEAVE);
		for (ProjectConstant projectConstant : projectConstants) {
			projectConstantsMap.put(projectConstant.getShortName(), projectConstant);
		}
	}
	
	public String navigateToApplyLeave() {
		return "navigateToApplyLeave";
	}

	public String getSelectedStatusType() {
		return selectedStatusType;
	}

	public void setSelectedStatusType(String selectedStatusType) {
		this.selectedStatusType = selectedStatusType;
	}

	public List<String> getStatusTypes() {
		return statusTypes;
	}

	public void setStatusTypes(List<String> statusTypes) {
		this.statusTypes = statusTypes;
	}

	public List<EmployeeLeave> getPendingEmployeeLeaves() {
		return pendingEmployeeLeaves;
	}

	public void setPendingEmployeeLeaves(List<EmployeeLeave> pendingEmployeeLeaves) {
		this.pendingEmployeeLeaves = pendingEmployeeLeaves;
	}

	public List<EmployeeLeave> getGrantedEmployeeLeaves() {
		return grantedEmployeeLeaves;
	}

	public void setGrantedEmployeeLeaves(List<EmployeeLeave> grantedEmployeeLeaves) {
		this.grantedEmployeeLeaves = grantedEmployeeLeaves;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public Employee getSelectedEmp() {
		return selectedEmp;
	}

	public void setSelectedEmp(Employee selectedEmp) {
		this.selectedEmp = selectedEmp;
	}

	public List<EmployeeLeave> getPendingAllEmployeeLeaves() {
		return pendingAllEmployeeLeaves;
	}

	public void setPendingAllEmployeeLeaves(List<EmployeeLeave> pendingAllEmployeeLeaves) {
		this.pendingAllEmployeeLeaves = pendingAllEmployeeLeaves;
	}

	public String getSelectedRadio() {
		return selectedRadio;
	}

	public void setSelectedRadio(String selectedRadio) {
		this.selectedRadio = selectedRadio;
	}
}
