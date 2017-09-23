package com.seminarioUMG.seminario.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import com.seminarioUMG.seminario.model.PreguntasFrecuentes;

@Qualifier("PreguntasService")
public interface PreguntasService extends JpaRepository<PreguntasFrecuentes, Serializable>{

}
