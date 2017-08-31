package com.seminarioUMG.seminario.services;



import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.AsignacionCursos;


@Service
@EnableJpaRepositories
public interface AlumnoService extends JpaRepository<Alumno, Serializable>{
	



	
	public final static String GET_ASIGNACIONES_BY_ALUMNO = "  Select al FROM Alumno al   WHERE al.NoCarnet = :nocarnet";
	public final static String GET_ALUMNOS_BY_COURSE = "select a from Alumno a  where a.NoCarnet = :idCurso";
	public final static String GET_COURSE_BY_ALUMNO = "select al.nocarnet nocarnet , al.apellidos apellidos , al.correo correo , al.nombres nombres from cursos cu inner join asignacion_cursos asig on cu.id_curso = asig.id_curso INNER JOIN alumno al on al.nocarnet = asig.nocarnet where cu.id_curso = :idCurso";


	@Query(GET_ASIGNACIONES_BY_ALUMNO)   
	List<Alumno> findBylikeCarnet(@Param("nocarnet") String nocarnet); 
	

	
	
	@Query(value = GET_COURSE_BY_ALUMNO , nativeQuery = true)
	List<Alumno> findByCourse(@Param("idCurso") String idCurso);
		
	
	




}
