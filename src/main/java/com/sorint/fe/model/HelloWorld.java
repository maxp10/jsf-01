package com.sorint.fe.model;

import javax.faces.bean.ManagedBean;

import com.sorint.primefaces.utility.ServiceIp;

@ManagedBean
public class HelloWorld {

	private String firstName = "";
	private String lastName = "";
	private String ip = "";

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String showGreeting() {
		return "Search " + firstName + " " + lastName;
	}

	public String getIp1() {
		ServiceIp serviceIp = new ServiceIp();
		return serviceIp.ip();
	}

	public String getIp() {
		return ip+"IP: "+getIp1();
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
