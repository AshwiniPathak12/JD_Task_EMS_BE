package com.ems.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {

	@Id
	private long roleid;
	private String rolename;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	private Set<UserRole> userRoles = new HashSet<>();

	public Set<UserRole> getUserRole() {
		return userRoles;
	}

	public void setUserRole(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Role() {
	}

	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public Role(long roleid, String rolename) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
