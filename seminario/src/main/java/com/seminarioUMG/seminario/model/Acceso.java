package com.seminarioUMG.seminario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Acceso.listarPorUsuario",
				query="SELECT a FROM Acceso a WHERE a.username.username = :username "),
	@NamedQuery(name="Acceso.correlativo",
				query="SELECT MAX(a.correlativo) FROM Acceso a ")
})
@Table(name="acceso")
public class Acceso {
	
	@Id
	@Column(name="correlativo")
	private Integer correlativo;
	
	@ManyToOne
	@JoinColumn(name="codigo_usuario")
	private User username;
	
	@ManyToOne
	@JoinColumn(name="codigo_rol")
	private Rol codigoRol;

	public Integer getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}

	public Rol getCodigoRol() {
		return codigoRol;
	}

	public void setCodigoRol(Rol codigoRol) {
		this.codigoRol = codigoRol;
	}

	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
	}

	
	
}
