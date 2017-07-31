package com.seminarioUMG.seminario.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminarioUMG.seminario.model.CardexTesoreria;



public interface TesoreriaService extends JpaRepository<CardexTesoreria, Serializable>{
	
	List<CardexTesoreria> findByTipo(Serializable arg0);

}
