package com.seminarioUMG.seminario.services;

import java.io.Serializable;
import java.util.List;

import org.aspectj.weaver.tools.Trace;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.Curso;

@Service
@EnableJpaRepositories
@Qualifier("CursosService")
public interface CursosService extends JpaRepository<Curso, Serializable> {
	
	
	public final static String GET_NAME_CURSOS = "select distinct(cu.nombre)  from cursos cu ;";
	public final static String GET_SECCION_CURSOS = "select cu.seccion from cursos cu where cu.nombre = :nombre";
	public final static String GET_ID_CURSO = "select cu.id_curso from cursos cu where nombre = :nombre and seccion = :seccion";
	
	
	

	 
	
	
	
	@Query(value = GET_NAME_CURSOS , nativeQuery=true)   
	List<String> listByName(); 
	
	
	
	@Query(value = GET_SECCION_CURSOS , nativeQuery=true)   
	List<String> getSeccion(@Param("nombre") String nombre); 
	
	@Query(value = GET_ID_CURSO , nativeQuery=true)   
	String getCourse(@Param("nombre") String nombre,@Param("seccion") String seccion); 
	
	
	@Query("select c from Curso c where c.catedratico.IdCatedratico = ?1")
	List<Curso> findByCatedratico(Integer carnet);
	


}
