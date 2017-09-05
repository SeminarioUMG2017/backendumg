package com.seminarioUMG.seminario.services;



import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Qualifier("AlumnoService")
public interface AlumnoService extends CrudRepository<Alumno, Serializable>{
	



	
	public final static String GET_ASIGNACIONES_BY_ALUMNO = "  Select al FROM Alumno al   WHERE al.NoCarnet = :nocarnet";
	public final static String GET_ALUMNOS_BY_COURSE = "select a from Alumno a  where a.NoCarnet = :idCurso";
	public final static String GET_COURSE_BY_ALUMNO = "select al.nocarnet nocarnet , al.apellidos apellidos , al.correo correo , al.nombres nombres from cursos cu inner join asignacion_cursos asig on cu.id_curso = asig.id_curso INNER JOIN alumno al on al.nocarnet = asig.nocarnet where cu.id_curso = :idCurso";

	public final static String GET_COUNT_ENTRADAS = "SELECT count(*) FROM seminario.qr;";
	
	public final static String GET_COUNT_INGRESOS = "select count(*) FROM qr where ingreso = 1;";
	public final static String GET_COUNT_DIPLOMAS = "select count(*) FROM qr where diploma = 1;";
	public final static String GET_TOTAL_ALUMNOS = "select count(*) FROM alumno;";
	
	
	
	@Query(value = GET_TOTAL_ALUMNOS , nativeQuery = true)
	Long totalAlumnos(); 
	
	@Query(value = GET_COUNT_INGRESOS , nativeQuery = true)
	Long totalIngresos(); 
	
	@Query(value = GET_COUNT_DIPLOMAS , nativeQuery = true)
	Long totalDiplomas(); 
	
	@Query(value = GET_COUNT_ENTRADAS , nativeQuery = true)
	Long totalEntradas(); 
	
	@Query(GET_ASIGNACIONES_BY_ALUMNO)   
	List<Alumno> findBylikeCarnet(@Param("nocarnet") String nocarnet); 
	

	
	
	@Query(value = GET_COURSE_BY_ALUMNO , nativeQuery = true)
	List<Alumno> findByCourse(@Param("idCurso") String idCurso);
		
	
	




}
