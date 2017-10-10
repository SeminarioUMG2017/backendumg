package com.seminarioUMG.seminario.resultmodels;

import java.util.List;

import javax.persistence.Column;

import com.seminarioUMG.seminario.model.Curso;



public class ResultadoCatedratico {
	
	
	private Integer IdCatedratico;
	private String nombres;
	private String apellidos; 
	private String correo;
	private List<Curso> cursos;
	public Integer getIdCatedratico() {
		return IdCatedratico;
	}
	public void setIdCatedratico(Integer idCatedratico) {
		IdCatedratico = idCatedratico;
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
	public List<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	
	
	
	
	
	
	
	

	
	
	

}
