package com.seminarioUMG.seminario.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.AsignacionCursos;

@Service
@Qualifier("AsignacionService")
@EnableJpaRepositories
public interface AsignacionService extends JpaRepository<AsignacionCursos, Serializable> {

}
