package com.ems.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.micrometer.core.lang.NonNull;

@JsonIgnoreProperties({ "hibernateLazyInitializer" }) // because error while fetch data by id
@Entity
@Table
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true)
	private int Employee_ID;
	@NonNull
	private String Employee_Name;
	private String DOJ;
	private String Total_Experience;
	private String Reporting_Manager;
	private String role;

	private @NonNull String email;
	private @NonNull String password;
	private boolean active;

	Employee() {

	}

	public Employee(int employee_ID, String employee_Name, String dOJ, String total_Experience,
			String reporting_Manager, String role, String email, String password) {
		super();
		Employee_ID = employee_ID;
		Employee_Name = employee_Name;
		DOJ = dOJ;
		Total_Experience = total_Experience;
		Reporting_Manager = reporting_Manager;
		this.role = role;
		this.email = email;
		this.password = password;
		this.active = active;
	}

	public int getEmployee_ID() {
		return Employee_ID;
	}

	public void setEmployee_ID(int employee_ID) {
		Employee_ID = employee_ID;
	}

	public String getEmployee_Name() {
		return Employee_Name;
	}

	public void setEmployee_Name(String employee_Name) {
		Employee_Name = employee_Name;
	}

	public String getDOJ() {
		return DOJ;
	}

	public void setDOJ(String dOJ) {
		DOJ = dOJ;
	}

	public String getTotal_Experience() {
		return Total_Experience;
	}

	public void setTotal_Experience(String total_Experience) {
		Total_Experience = total_Experience;
	}

	public String getReporting_Manager() {
		return Reporting_Manager;
	}

	public void setReporting_Manager(String reporting_Manager) {
		Reporting_Manager = reporting_Manager;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Employee [Employee_ID=" + Employee_ID + ", Employee_Name=" + Employee_Name + ", DOJ=" + DOJ
				+ ", Total_Experience=" + Total_Experience + ", Reporting_Manager=" + Reporting_Manager + ", role="
				+ role + ", email=" + email + ", password=" + password + ", active=" + active + "]";
	}



}
