package com.seminarioUMG.seminario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
@Entity
@Table(name="qr")
public class Qr {
	
	@Id
	@Column (name = "nocarnet")
	private String NoCarnet;
	@Column (name = "ruta")
	private String ruta;
	@Column (name = "ingreso")
	private boolean ingreso;
	@Column (name = "refaccion")
	private boolean refaccion;
	@Column (name = "diploma")
	private boolean diploma;
	@Column (name = "cadena")
	private String cadena;
	
	
	
	public Qr() {
	}
	
	
	public Qr(String noCarnet, String ruta, boolean ingreso, boolean refaccion, boolean diploma, String cadena) {
		super();
		NoCarnet = noCarnet;
		this.ruta = ruta;
		this.ingreso = ingreso;
		this.refaccion = refaccion;
		this.diploma = diploma;
		this.cadena = cadena;
	}


	public String getNoCarnet() {
		return NoCarnet;
	}


	public void setNoCarnet(String noCarnet) {
		NoCarnet = noCarnet;
	}


	public String getRuta() {
		return ruta;
	}


	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	public boolean isIngreso() {
		return ingreso;
	}


	public void setIngreso(boolean ingreso) {
		this.ingreso = ingreso;
	}


	public boolean isRefaccion() {
		return refaccion;
	}


	public void setRefaccion(boolean refaccion) {
		this.refaccion = refaccion;
	}


	public boolean isDiploma() {
		return diploma;
	}


	public void setDiploma(boolean diploma) {
		this.diploma = diploma;
	}


	public String getCadena() {
		return cadena;
	}


	public void setCadena(String cadena) {
		this.cadena = cadena;
	}


	
}
