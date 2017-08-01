package com.seminarioUMG.seminario.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminarioUMG.seminario.model.CardexTesoreria;



public interface TesoreriaService extends JpaRepository<CardexTesoreria, Serializable>{
	
	List<CardexTesoreria> findByTipo(Optional<Long> tipo);
	List<CardexTesoreria> findByClase(Optional<Long> clase);
	List<CardexTesoreria> findByTipoAndFecha(Optional<Long> tipo,Date fecha);
}
