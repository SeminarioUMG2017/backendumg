package com.seminarioUMG.seminario.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.AsignacionCursos;

@Service
public interface AlumnoService extends JpaRepository<Alumno, Serializable>{
	
	public final static String GET_ALUMNOS_BY_LIKE = "SELECT al FROM Alumno al WHERE NoCarnet like :nocarnet||'%'";
	public final static String GET_ALUMNOS_BY_COURSE = "select a from Alumno a  where a.NoCarnet = :idCurso";


	@Query(GET_ALUMNOS_BY_LIKE)   
	List<Alumno> findBylikeCarnet(@Param("nocarnet") String nocarnet);
	
	@Query(GET_ALUMNOS_BY_COURSE)
	List<Alumno> findByCourse(@Param("idCurso") String idCurso);
	




}
