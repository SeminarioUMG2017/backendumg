package  com.seminarioUMG.seminario.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.methods.Mailer;
import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.AsignacionCursos;
import com.seminarioUMG.seminario.services.AlumnoService;





@RestController
@RequestMapping(value = "/alumno")
public class AlumnoController {
	
	@Autowired
	AlumnoService alumnoService;
	@Autowired
	Mailer mailer;
	
	

    @PostMapping(value = "/addalumno")
    public void addalumno(@RequestBody Alumno alumno)  {    
    
    	alumnoService.save(alumno);
    }
    
    
    @GetMapping(value = "/mailer")
    public void mail() throws IOException  {    
mailer.executeMail();

    }
    
    
    @GetMapping(value = "/alumnos/{nocarnet}")
    public Alumno getAlumnobyCurso(@PathVariable Integer nocarnet)  {
    	Alumno alumno =  alumnoService.findOne(nocarnet);
		return alumno;    
    	
    }
    

    @GetMapping(value = "/getalumnos")
    public List<Alumno> getalumnos() throws IOException  {    
    	List<Alumno> Alumnos = alumnoService.findAll();
    
		return Alumnos;
		
    
    	
    }

}
