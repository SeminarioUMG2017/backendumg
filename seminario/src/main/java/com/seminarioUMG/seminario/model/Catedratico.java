package com.seminarioUMG.seminario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "catedratico")
public class Catedratico {


	@Id
	@GeneratedValue
	@Column (name = "id_catedratico")
	private Integer IdCatedratico;
	@Column (name = "nombres")
	private String nombres;
	@Column (name = "apellidos")
	private String apellidos;
	@Column (name = "correo")
	private String correo;

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
	public Catedratico(Integer idCatedratico, String nombres, String apellidos, String correo) {
		super();
		IdCatedratico = idCatedratico;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
	}
	public Catedratico() {
		
	}


	
	

}
