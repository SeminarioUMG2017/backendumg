package com.seminarioUMG.seminario.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.AsignacionCursos;
import com.seminarioUMG.seminario.model.CardexTesoreria;

@Service
@Qualifier("AsignacionService")
@EnableJpaRepositories
public interface AsignacionService extends JpaRepository<AsignacionCursos, Serializable> {
	public final static String DELETE_ASIGNACION = "  DELETE FROM asignacion_cursos WHERE correlativo = :correlativo"; 


	@Query(value = DELETE_ASIGNACION , nativeQuery = true)
	AsignacionCursos DeleteByCOrrelativo(@Param("correlativo") String correlativo); 
	
	
}
