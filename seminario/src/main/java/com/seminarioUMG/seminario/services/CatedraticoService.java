package com.seminarioUMG.seminario.services;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminarioUMG.seminario.model.Catedratico;

public interface CatedraticoService extends JpaRepository<Catedratico, Serializable>{

	
}
