package com.seminarioUMG.seminario.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.AsignacionCursos;

@Service
public interface AlumnoService extends JpaRepository<Alumno, Serializable>{

	
	List<Alumno> findAll();
	Alumno getOne(Serializable arg0);

	 


}
