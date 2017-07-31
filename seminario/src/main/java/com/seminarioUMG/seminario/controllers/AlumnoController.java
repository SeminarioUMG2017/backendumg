package  com.seminarioUMG.seminario.controllers;

import java.io.IOException;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.methods.Mailer;
import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.AsignacionCursos;
import com.seminarioUMG.seminario.services.AlumnoService;






@RestController
@RequestMapping(value = "/alumno")
public class AlumnoController {
	
	@Autowired
	AlumnoService alumnoService;
	

	
	

    @PostMapping(value = "/addalumno")
    public ResponseEntity<Alumno> addalumno(@RequestBody Alumno alumno)  {    
    
    	
    	try
    	{
    		alumnoService.save(alumno);
    		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    		
    	}catch(Exception e) {
    		return new ResponseEntity<Alumno>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    
    @GetMapping(value = "/mailer")
    public void mail(@RequestBody Alumno alumno) throws IOException  {    


    }
    
    
    @GetMapping(value = "/alumnos/{nocarnet}")
    public ResponseEntity<Alumno> getAlumnobyCurso(@PathVariable Integer nocarnet)  {
    	
    	
    	try
    	{
    		Alumno alumno =  alumnoService.findOne(nocarnet);
    		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    		
    	}catch(Exception e) {
    		return new ResponseEntity<Alumno>(HttpStatus.BAD_REQUEST);
    	}
    	
    	
    }
    

    @GetMapping(value = "/getalumnos")
    public ResponseEntity<List<Alumno>> getalumnos() throws IOException  {    
    	try
    	{
    		List<Alumno> Alumnos = alumnoService.findAll();
    		if(Alumnos.size()==0)
    		{
    			return new ResponseEntity<List<Alumno>>(HttpStatus.NO_CONTENT);
    		}
    		else
    		{
    			return new ResponseEntity<List<Alumno>>(Alumnos, HttpStatus.OK);
    		}
    		
    	}catch(Exception e) {
    		return new ResponseEntity<List<Alumno>>(HttpStatus.BAD_REQUEST);
    	}
    	
    		
		
		
    
    	
    }

}
