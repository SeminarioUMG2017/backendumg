package com.seminarioUMG.seminario.controllers;

import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.AsignacionCursos;
import com.seminarioUMG.seminario.resultmodels.ResultadoAlumnos;
import com.seminarioUMG.seminario.services.AlumnoService;
import com.seminarioUMG.seminario.services.CursosService;


@RestController
@RequestMapping(value = "/curso")
public class CursoController {

	@Autowired
	@Qualifier("CursosService")
	CursosService cursoService;
	@Autowired
	@Qualifier("AlumnoService")
	AlumnoService alumnoService;
	
	 @GetMapping(value = "/listcursos")
    public ResponseEntity<List<String>> listCursos()  {    
    
    	List <String>  NameCursos = new ArrayList<String>();
    	try
    	{
    	
    		NameCursos = cursoService.listByName();
    		
    		
    		return new ResponseEntity<List<String>>(NameCursos, HttpStatus.OK);
    		
    	}catch(Exception e) {
    		return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
    	}
    	}
	 
	 
	 @GetMapping(value = "/secciones/{nombre}")
	    public ResponseEntity<List<String>> getSecciones(@PathVariable String nombre)  {    
	
	    	List <String>  Secciones = new ArrayList<String>();
	   	 System.out.println(nombre);
	    
	    	
	    		Secciones = cursoService.getSeccion(nombre);
	    		
	    		
	    		
	    		return new ResponseEntity<List<String>>(Secciones, HttpStatus.OK);
	    		
	    	
	    }
	
	
	 
	 @GetMapping(value = "/secciones/{nombre}/{seccion}")
	    public ResponseEntity<String> getCurso(@PathVariable String nombre, @PathVariable String seccion)  {    
	
	   String idCurso = cursoService.getCourse(nombre, seccion);

	    		
	    		return new ResponseEntity<String>(idCurso, HttpStatus.OK);
	    		
	    	
	    }
	 @GetMapping(value="/getEstudiantes/{codigoCurso}")
	 public ResponseEntity<List<ResultadoAlumnos>> getEstudiantesCurso(@PathVariable("codigoCurso") String codigoCurso)
	 {
		 try {
			
			 List<ResultadoAlumnos> alumnos = alumnoService.findByCourseAlumno(codigoCurso);
			 System.out.println(alumnos.size());
			 return new ResponseEntity<List<ResultadoAlumnos>>(alumnos,HttpStatus.OK);
		 }catch(Exception e) {
			return new ResponseEntity<List<ResultadoAlumnos>>(HttpStatus.BAD_REQUEST); 
		 }
	 }

}
