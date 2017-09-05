package com.seminarioUMG.seminario.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.seminarioUMG.seminario.model.AsignacionCursos;
import com.seminarioUMG.seminario.model.CardexTesoreria;
import com.seminarioUMG.seminario.services.AsignacionService;

@Repository
public class AsignacionDao implements AsignacionService {

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(Iterable<AsignacionCursos> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AsignacionCursos> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AsignacionCursos> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AsignacionCursos> findAll(Iterable<Serializable> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AsignacionCursos> List<S> findAll(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AsignacionCursos> List<S> findAll(Example<S> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AsignacionCursos getOne(Serializable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AsignacionCursos> List<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AsignacionCursos> S saveAndFlush(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AsignacionCursos> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

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
	public void delete(AsignacionCursos arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends AsignacionCursos> arg0) {
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
	public AsignacionCursos findOne(Serializable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AsignacionCursos> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AsignacionCursos> long count(Example<S> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends AsignacionCursos> boolean exists(Example<S> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends AsignacionCursos> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AsignacionCursos> S findOne(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AsignacionCursos DeleteByCOrrelativo(String correlativo) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
