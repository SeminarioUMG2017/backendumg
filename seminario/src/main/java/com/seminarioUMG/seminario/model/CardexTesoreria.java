package com.seminarioUMG.seminario.model;

import java.sql.Timestamp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import javax.persistence.GenerationType;


@Entity
@Table(name="cardex_tesoreria")
public class CardexTesoreria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cardex")
	private Long idCardex;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name="tipo")
	private Long tipo;
	
	@Column(name="fecha")
	@Type(type="date")
	private Date fecha;
	@Column(name = "monto")
	private Double monto;
	public Long getIdCardex() {
		return idCardex;
	}
	public void setIdCardex(Long idCardex) {
		this.idCardex = idCardex;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getTipo() {
		return tipo;
	}
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public CardexTesoreria(Long idCardex, String descripcion, Long tipo,
			Date fecha, Double monto) {
		super();
		this.idCardex = idCardex;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.fecha = fecha;
		this.monto = monto;
	}
	public CardexTesoreria() {
	
	}
	
	



	

}
