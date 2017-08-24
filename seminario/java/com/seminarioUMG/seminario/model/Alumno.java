package com.seminarioUMG.seminario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name ="alumno")
public class Alumno { 

	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2") 
	@Column (name = "nocarnet")
	private String NoCarnet;
	@Column (name = "nombres")
	private String nombres;
	@Column (name = "apellidos")
	private String apellidos;
	@Column (name = "correo")
	private String correo;
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
	public Alumno(String noCarnet, String nombres, String apellidos, String correo) {
		super();
		NoCarnet = noCarnet;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
	}
	public Alumno() {

	}
	
	
	
	
	
		
}
