package com.seminarioUMG.seminario.services;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminarioUMG.seminario.model.Qr;

public interface QrService extends JpaRepository<Qr, Serializable>{
	
	Qr findOne(Serializable arg0);
	Qr findByCadena(String cadena);
	Qr findByCarnet(String carnet);

}
