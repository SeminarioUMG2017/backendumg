package com.seminarioUMG.seminario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Rol.listar",
				query="SELECT r FROM Rol r "),
	@NamedQuery(name="Rol.findByName",
				query="SELECT r FROM Rol r WHERE r.descripcion = :rolName")
})
@Table(name="rol")
public class Rol {
	
	@Id
	@Column(name="codigo_rol")
	private Integer codigoRol;
	
	@Column(name="descripcion")
	private String descripcion;

	public Integer getCodigoRol() {
		return codigoRol;
	}

	public void setCodigoRol(Integer codigoRol) {
		this.codigoRol = codigoRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
