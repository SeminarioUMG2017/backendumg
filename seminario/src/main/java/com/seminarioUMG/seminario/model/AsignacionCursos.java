package com.seminarioUMG.seminario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name ="asignacion_cursos")

public class AsignacionCursos {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column (name = "correlativo")
		private Integer Correlativo;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "id_curso")
//		@JsonBackReference
		private Curso curso;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "nocarnet") 
		@JsonBackReference
		private Alumno alumno;
		
		
		
	
		public Integer getCorrelativo() {
			return Correlativo;
		}
		public void setCorrelativo(Integer correlativo) {
			Correlativo = correlativo;
		}
		public Curso getCurso() {
			return curso;
		} 
		public void setCurso(Curso curso) {
			this.curso = curso;
		}
		public Alumno getAlumno() {
			return alumno;
		}
		public void setAlumno(Alumno alumno) {
			this.alumno = alumno;
		}
		public AsignacionCursos(Integer correaltivo, Curso curso, Alumno alumno) {
			super();
			Correlativo = correaltivo;
			this.curso = curso;
			this.alumno = alumno;
		}
		public AsignacionCursos() {
		}
		
		
		
		
		
		
	}

	

