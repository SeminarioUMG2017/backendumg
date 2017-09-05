package com.seminarioUMG.seminario.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.AsignacionCursos;
import com.seminarioUMG.seminario.services.AlumnoService;

@Repository
public  class AlumnoDao implements AlumnoService {


	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Serializable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Alumno arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Alumno> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Serializable arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Alumno> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Alumno> findAll(Iterable<Serializable> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno findOne(Serializable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Alumno> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Alumno> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alumno> findBylikeCarnet(String nocarnet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alumno> findByCourse(String idCurso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long totalAlumnos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long totalIngresos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long totalDiplomas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long totalEntradas() {
		// TODO Auto-generated method stub
		return null;
	} 


	

	
	
	
	
}
