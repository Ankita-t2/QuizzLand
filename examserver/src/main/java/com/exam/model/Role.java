package com.exam.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="roles")
public class Role {
	
	@Id
	private Long RoleId;
	private String RoleName;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	@JsonIgnore
	private Set<UserRole> userRoles=new HashSet<>();
	
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public Role() {
		
	}
	public Role(Long roleId, String roleName) {
		super();
		RoleId = roleId;
		RoleName = roleName;
	}
	public Long getRoleId() {
		return RoleId;
	}
	public void setRoleId(Long roleId) {
		RoleId = roleId;
	}
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	
	

}
