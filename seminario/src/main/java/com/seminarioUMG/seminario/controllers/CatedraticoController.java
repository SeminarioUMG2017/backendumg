package com.seminarioUMG.seminario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.services.AlumnoService;

@RestController
@RequestMapping(value = "/catedratico")
public class CatedraticoController {
	@Autowired
	AlumnoService alumnoService; 
	
	
    @GetMapping(value = "/{idCurso}")
    public ResponseEntity<List<Alumno>> getAlumnoandAsignaciones(@PathVariable String idCurso)  {
    	
    	try
    	{
    		List<Alumno> alumno =  alumnoService.findByCourse(idCurso);
    		return new ResponseEntity<List<Alumno>>(alumno, HttpStatus.OK);
    	}
    		
    catch(Exception e) {
		return new ResponseEntity<List<Alumno>>(HttpStatus.BAD_REQUEST);
	}
 
    	
    }

}
