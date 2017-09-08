package com.seminarioUMG.seminario.resultmodels;

public class ResultadoAlumnos {
	private String nocarnet;
	private String nombres;
	private String apellidos;
	private String correo;
	
	public String getCarnet() {
		return nocarnet;
	}
	public void setCarnet(String carnet) {
		this.nocarnet = carnet;
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
	public ResultadoAlumnos(String nocarnet, String nombres, String apellidos, String correo) {
		super();
		this.nocarnet = nocarnet;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
	}
	public ResultadoAlumnos() {
	}
	
	

}
