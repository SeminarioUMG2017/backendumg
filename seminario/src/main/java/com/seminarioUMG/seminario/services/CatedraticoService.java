package com.seminarioUMG.seminario.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seminarioUMG.seminario.model.Catedratico;
import com.seminarioUMG.seminario.model.Curso;
import com.seminarioUMG.seminario.resultmodels.ResultadoAlumnos;

public interface CatedraticoService extends JpaRepository<Catedratico, Serializable>{

	public final static String GET_CORSES_BY_CATEDRATICO = "Select cu FROM Curso cu WHERE cu.catedratico.IdCatedratico = :idCatedratico";

	
	
	
	@Query(value = GET_CORSES_BY_CATEDRATICO)
	List<Curso> getCourses( @Param("idCatedratico") Integer idCatedratico);  
	
	
}
