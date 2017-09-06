package com.seminarioUMG.seminario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.Curso;
import com.seminarioUMG.seminario.services.AlumnoService;
import com.seminarioUMG.seminario.services.CursosService;

@RestController
@RequestMapping(value = "/catedratico")
public class CatedraticoController {
	@Autowired
	AlumnoService alumnoService; 
	@Autowired 
	@Qualifier("CursosService")
	CursosService cursoService;
	
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
    @GetMapping(value ="/getCursosCatedratico/{carnet}")
    public ResponseEntity<List<Curso>> getCursosCatedratico(@PathVariable("carnet") String catedratico)
    {
    	System.out.println("Carnet catedratico "+catedratico);
    	try
    	{
    		Integer idCatedratico = Integer.parseInt(catedratico);
    	List<Curso> cursos = cursoService.findByCatedratico(idCatedratico);
    	return new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
    	}catch(Exception e)
    	{
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }

}
