package com.seminarioUMG.seminario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="preguntas_frecuentes")
public class PreguntasFrecuentes {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pregunta")
	private Integer id;
	@Column(name="pregunta")
	private String pregunta;
	@Column(name="respuesta")
	private String respuesta;
	public PreguntasFrecuentes() {
	}
	
	public PreguntasFrecuentes(Integer id, String pregunta, String respuesta) {
		super();
		this.id = id;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
}
