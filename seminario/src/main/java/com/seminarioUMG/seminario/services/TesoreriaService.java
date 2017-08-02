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
	List<CardexTesoreria> findByClase(Optional<Long> clase);
	
	@Query("Select ct from CardexTesoreria ct where ct.tipo=?1 and ct.fecha in current_date")
	List<CardexTesoreria> findByTipoAndFechaBetween(Optional<Long> tipo, String fechaConvertidaI);
	
	@Query("Select ct from CardexTesoreria ct where ct.tipo=?1 and ct.fecha > current_date")
	List<CardexTesoreria> findByTipoAndFechaBetweenEst(Optional<Long> tipo);
	
}
