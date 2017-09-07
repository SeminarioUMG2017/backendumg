package com.seminarioUMG.seminario.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "userole")
public class UserPermission {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_correlativo")
	private Integer correlativo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username")
	private User username;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_rol")
	private Rol roles;
	public UserPermission(Integer correlativo, User username, Rol roles) {
		super();
		this.correlativo = correlativo;
		this.username = username;
		this.roles = roles;
	}
	public UserPermission() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}
	public User getUsername() {
		return username;
	}
	public void setUsername(User username) {
		this.username = username;
	}
	public Rol getRoles() {
		return roles;
	}
	public void setRoles(Rol roles) {
		this.roles = roles;
	}
	
	
	
	
	
	

}
