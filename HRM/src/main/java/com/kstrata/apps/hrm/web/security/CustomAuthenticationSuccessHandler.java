package com.kstrata.apps.hrm.web.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.kstrata.apps.hrm.bean.util.HRMConstants;
import com.kstrata.apps.hrm.model.AuthenticationUserDetails;
import com.kstrata.apps.hrm.model.Employee;


public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler  {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private boolean isPathBasedOnRole;
	
	private String defaultTargetUrl;
	
	private Map<String, String> userRoleTargetUrlMap = new HashMap<String, String>();
	
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest req,
			final HttpServletResponse res, final Authentication auth) throws IOException,
			ServletException, AccessDeniedException {
		
			constructEmployee(req);
			String targetUrl = getDefaultTargetUrl();
			getRedirectStrategy().sendRedirect(req, res, targetUrl);
			clearAuthenticationAttributes(req);
	}
	
	private void constructEmployee(final HttpServletRequest req) {
		Object obj = (SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
		if (obj != null) {
			AuthenticationUserDetails authenticationUserDetails = (AuthenticationUserDetails) obj;
			Employee employee = authenticationUserDetails.getEmployee();
			req.getSession().setAttribute(HRMConstants.LOGGED_USER, employee);
		}
	}
	
    protected void clearAuthenticationAttributes(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
	 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

	/** 
	 * @return the isPathBasedOnRole
	 */
	public boolean isPathBasedOnRole() {
		return isPathBasedOnRole;
	}

	/**
	 * @param isPathBasedOnRole the isPathBasedOnRole to set
	 */
	public void setPathBasedOnRole(boolean isPathBasedOnRole) {
		this.isPathBasedOnRole = isPathBasedOnRole;
	}


	/**
	 * @return the userRoleTargetUrlMap
	 */
	public Map<String, String> getUserRoleTargetUrlMap() {
		return userRoleTargetUrlMap;
	}

	/**
	 * @param userRoleTargetUrlMap the userRoleTargetUrlMap to set
	 */
	public void setUserRoleTargetUrlMap(Map<String, String> userRoleTargetUrlMap) {
		this.userRoleTargetUrlMap = userRoleTargetUrlMap;
	}

	public String getDefaultTargetUrl() {
		return defaultTargetUrl;
	}

	public void setDefaultTargetUrl(String defaultTargetUrl) {
		this.defaultTargetUrl = defaultTargetUrl;
	}

	}
	
