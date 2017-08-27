package com.seminarioUMG.seminario.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seminarioUMG.seminario.model.CardexTesoreria;



public interface TesoreriaService extends JpaRepository<CardexTesoreria, Serializable>{
	
	List<CardexTesoreria> findByTipo(Optional<Long> tipo);
	
	@Query("Select ct from CardexTesoreria ct where ct.tipo=?1 and ct.fecha>=?2")
	List<CardexTesoreria> findByTipoAndFechaI(Long tipo, Date fecha);
	
	@Query("Select ct from CardexTesoreria ct where ct.tipo=?1 and ct.fecha > current_date")
	List<CardexTesoreria> findByTipoAndFechaBetweenEst(Optional<Long> tipo);
	
	List<CardexTesoreria> findByTipoAndFechaBetween(Long tipo, Date fechaI, Date fechaF);
	
}