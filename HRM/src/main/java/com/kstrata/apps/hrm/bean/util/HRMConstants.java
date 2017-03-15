package com.kstrata.apps.hrm.bean.util;

public final class HRMConstants {
	
	public static final double AL_LEAVE_MIN_BAL = -5.0;
	public static final double HD_LEAVE_BAL = 0.5;
	public static final double FD_LEAVE_BAL = 1.0;

	public static final String LOGGED_USER = "loggedUser";
	
	//Project Constant types
	public static final String LEAVE = "LEAVE";
	public static final String STATUS = "STATUS";
	public static final String ATTENDANCE_STATUS = "ATTENDANCE STATUS";
	
	//Leave
	public static final String PENDING = "PENDING";
	public static final String APPROVED = "APPROVED";
	public static final String DECLINED = "DECLINED";
	public static final String APPROVE = "Approve";
	public static final String DECLINE = "Decline";
	
	public static final String AL = "AL";
	public static final String HDAL = "HDAL";
	public static final String SL = "SL";
	public static final String HDSL = "HDSL";
	
	//holidays
	public static final String WEEKDAY = "Weekday";
	public static final String WEEKEND = "Weekend";
	public static final String AVAILABLE = "Available";
	public static final String PUBLIC_HOLIDAY = "Public Holiday";
	public static final String SATURDAY = "Saturday";
	public static final String SUNDAY = "Sunday";
	
	
	//Messages
	public static final String PLEASE_SELECT = "Please Select";
	public static final String PLEASE_SELECT_STATUS = "Please Select Status";
	public static final String UPDATED_SUCCESSFULLY = "Updated Successfully";
	public static final String SAVED_SUCCESSFULLY = "Saved Successfully";
	public static final String DELETED_SUCCESSFULLY="Leave Record deleted!";
	
	public static final String START_DATE_CAN_NOT_BE_NULL = "Start date can not be null";
	public static final String END_DATE_CAN_NOT_BE_NULL = "End date can not be null";
	public static final String COMMENTS_CAN_NOT_BE_NULL = "Comments can not be null";
	public static final String INVALID_DATE_SELECTION = "Invalid date selection";
	public static final String REASON_EMPTY="Reason is a Mandatory field!";
	public static final String INVALID_HOURS = "Invalid Hours";
	public static final String PLEASE_SELECT_DATE = "Please select date";
	
	private HRMConstants() {
		
	}
	
}
