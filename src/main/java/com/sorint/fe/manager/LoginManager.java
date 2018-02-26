package com.sorint.fe.manager;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;

@ManagedBean
@ViewScoped
public class LoginManager {

	private static final String HOME_PAGE = "/";
	private static final String PAGE_AFTER_LOGOUT = HOME_PAGE;

	private static final String SESSION_USER_VARIABLE_NAME = "user";

	private String username;
	private String password;
	private String forwardUrl;
	private String logout;
	private String userString="";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserString() {
		return userString;
	}

	public void setUserString(String userString) {
		this.userString = userString;
	}
	

	public String getLogout() {
		return logout;
	}

	public void setLogout(String logout) {
		this.logout = logout;
	}

	@PostConstruct
	public void init() {
		this.forwardUrl = extractRequestedUrlBeforeLogin();
	}

	private String extractRequestedUrlBeforeLogin() {
		ExternalContext externalContext = externalContext();
		String requestedUrl = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);
		if (requestedUrl == null) {
			return externalContext.getRequestContextPath() + HOME_PAGE;
		}
		String queryString = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);
		return requestedUrl + (queryString == null ? "" : "?" + queryString);
	}

	private ExternalContext externalContext() {
		return facesContext().getExternalContext();
	}

	private FacesContext facesContext() {
		return FacesContext.getCurrentInstance();
	}

	public String login() throws IOException {
		ExternalContext externalContext = externalContext();
		externalContext.redirect(forwardUrl);
		setUserString("Login " + username + " done!");
		return getUserString();
	}

	public void logout() throws IOException {
		ExternalContext externalContext = externalContext();
		externalContext.invalidateSession();
		externalContext.redirect(externalContext.getRequestContextPath() + PAGE_AFTER_LOGOUT);
	}

	public boolean isUserLoggedIn() {
		if (userString.equals("")) return false;
		return false;
	}

	public boolean isUserInRole(String role) {
		FacesContext context = facesContext();
		ExternalContext externalContext = context.getExternalContext();
		return externalContext.isUserInRole(role);
	}

}