package com.seminarioUMG.seminario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="alumno")
public class Alumno { 

	
	@Id
	@GeneratedValue
	@Column (name = "nocarnet")
	private Integer NoCarnet;
	@Column (name = "nombres")
	private String nombres;
	@Column (name = "apellidos")
	private String apellidos;
	@Column (name = "correo")
	private String correo;
	public Integer getNoCarnet() {
		return NoCarnet;
	}
	public void setNoCarnet(Integer noCarnet) {
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
	public Alumno(Integer noCarnet, String nombres, String apellidos, String correo) {
		super();
		NoCarnet = noCarnet;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
	}
	public Alumno() {
		
	}
	
	
	
		
}
