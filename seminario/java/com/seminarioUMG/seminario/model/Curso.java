package com.seminarioUMG.seminario.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "cursos")
public class Curso {
	


	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2") 
	@Column (name = "id_curso")
	private String idCurso;
	@Column (name = "seccion")
	private String seccion;
	@Column (name = "nombre")
	private String nombre;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_catedratico")
	@JsonBackReference
	private Catedratico catedratico;
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "curso")
		private List<AsignacionCursos> asignaciones;
	
	
	
	public String getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Catedratico getCatedratico() {
		return catedratico;
	}
	public void setCatedratico(Catedratico catedratico) {
		this.catedratico = catedratico;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public Curso(String idCurso, String seccion, String nombre, Catedratico catedratico) {
		super();
		this.idCurso = idCurso;
		this.seccion = seccion;
		this.nombre = nombre;
		this.catedratico = catedratico;
	}
	public Curso() {
	}

	
	

	
}
