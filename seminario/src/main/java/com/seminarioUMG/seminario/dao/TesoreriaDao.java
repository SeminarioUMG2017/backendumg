package com.seminarioUMG.seminario.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.seminarioUMG.seminario.model.CardexTesoreria;
import com.seminarioUMG.seminario.services.TesoreriaService;

public class TesoreriaDao implements TesoreriaService {

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(Iterable<CardexTesoreria> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CardexTesoreria> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CardexTesoreria> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CardexTesoreria> findAll(Iterable<Serializable> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CardexTesoreria> List<S> findAll(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CardexTesoreria> List<S> findAll(Example<S> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CardexTesoreria getOne(Serializable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CardexTesoreria> List<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CardexTesoreria> S saveAndFlush(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CardexTesoreria> findAll(Pageable arg0) {
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
	public void delete(CardexTesoreria arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends CardexTesoreria> arg0) {
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
	public CardexTesoreria findOne(Serializable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CardexTesoreria> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CardexTesoreria> long count(Example<S> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends CardexTesoreria> boolean exists(Example<S> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends CardexTesoreria> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CardexTesoreria> S findOne(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<CardexTesoreria> findByClase(Optional<Long> clase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CardexTesoreria> findByTipo(Optional<Long> tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<CardexTesoreria> findByTipoAndFechaBetweenEst(Optional<Long> tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CardexTesoreria> findByTipoAndFechaI(Long tipo, Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CardexTesoreria> findByTipoAndFechaBetween(Long tipo, Date fechaI, Date fechaF) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	

}
