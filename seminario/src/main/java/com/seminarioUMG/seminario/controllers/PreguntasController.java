package com.seminarioUMG.seminario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.model.Catedratico;
import com.seminarioUMG.seminario.model.PreguntasFrecuentes;
import com.seminarioUMG.seminario.services.CursosService;
import com.seminarioUMG.seminario.services.PreguntasService;

@RestController
@RequestMapping(value = "/preguntas")
public class PreguntasController {

	@Autowired
	@Qualifier("PreguntasService")
	PreguntasService preguntasService;
	
	
	@PostMapping(value = "/addQuestion")
	    public ResponseEntity<PreguntasFrecuentes> AgregarPregunta(@RequestBody PreguntasFrecuentes pregunta)  {    
	    	try
	    	{
	    		 preguntasService.save(pregunta);
	    		return new ResponseEntity<PreguntasFrecuentes>(pregunta, HttpStatus.OK);
	    		
	    	}catch(Exception e) {
	    		return new ResponseEntity<PreguntasFrecuentes>(HttpStatus.BAD_REQUEST);
	    	}
	    }

	
	@GetMapping(value = "/allQuestion")
    public ResponseEntity<List<PreguntasFrecuentes>> getAllPreguntas()  {
    	
    	try
    	{
    		List<PreguntasFrecuentes> preguntas =  preguntasService.findAll();
    	
    		return new ResponseEntity<List<PreguntasFrecuentes>>(preguntas, HttpStatus.OK);

    	}
    		
    catch(Exception e) {
    	System.out.println(e);
		return new ResponseEntity<List<PreguntasFrecuentes>>(HttpStatus.BAD_REQUEST);
	}
	
	
}
	
}
