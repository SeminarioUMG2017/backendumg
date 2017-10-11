package com.seminarioUMG.seminario.resultmodels;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import com.seminarioUMG.seminario.model.AsignacionCursos;
import com.seminarioUMG.seminario.model.Curso;



public class ResultadoCatedratico {
	
	
	private Integer noCarnet;
	private String nombres;
	private String apellidos; 
	private String correo;
	private List<List<Map<String,Curso>>> asignaciones;
	
	public Integer getNoCarnet() {
		return noCarnet;
	}
	public void setNoCarnet(Integer noCarnet) {
		this.noCarnet = noCarnet;
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
	public List<List<Map<String, Curso>>> getAsignaciones() {
		return asignaciones;
	}
	public void setAsignaciones(List<List<Map<String, Curso>>> asignaciones) {
		this.asignaciones = asignaciones;
	}

	
	
	
	
	
	
	
	
	

	
	
	

}
