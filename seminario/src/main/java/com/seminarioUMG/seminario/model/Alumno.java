package com.seminarioUMG.seminario.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name ="alumno")
public class Alumno { 

	
	@Id
	@Column (name = "nocarnet")
	private String NoCarnet;
	@Column (name = "nombres")
	private String nombres;
	@Column (name = "apellidos")
	private String apellidos;
	@Column (name = "correo")
	private String correo;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "alumno")
	@JsonManagedReference
	private Set<AsignacionCursos> asignaciones;
	@Column(name = "entrada_pagada")
	private Integer pagado;
	
	public Integer getPagado() {
		return pagado;
	}
	public void setPagado(Integer pagado) {
		this.pagado = pagado;
	}
	public String getNoCarnet() {
		return NoCarnet;
	}
	public void setNoCarnet(String noCarnet) {
		NoCarnet = noCarnet;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Set<AsignacionCursos> getAsignaciones() {
		return asignaciones;
	}
	public void setAsignaciones(Set<AsignacionCursos> asignaciones) {
		this.asignaciones = asignaciones;
	}
	
	public Alumno(String noCarnet, String nombres, String apellidos, String correo, Set<AsignacionCursos> asignaciones,
			Integer pagado) {
		super();
		NoCarnet = noCarnet;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.asignaciones = asignaciones;
		this.pagado = pagado;
	}
	public Alumno() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
		
}
