package  com.seminarioUMG.seminario.controllers;

import java.io.IOException;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.methods.GeneradorQr;
import com.seminarioUMG.seminario.methods.Mailer;
import com.seminarioUMG.seminario.methods.PasswordGenerator;
import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.AsignacionCursos;
import com.seminarioUMG.seminario.model.CardexTesoreria;
import com.seminarioUMG.seminario.model.User;
import com.seminarioUMG.seminario.services.AlumnoService;
import com.seminarioUMG.seminario.services.TesoreriaService;
import com.seminarioUMG.seminario.services.UserRepository;

@RestController
@RequestMapping(value = "/alumno")
public class AlumnoController {
	
	@Autowired
	AlumnoService alumnoService; 
	@Autowired
	Mailer mailer;
	@Autowired
	PasswordGenerator pass ;
	@Autowired 
	GeneradorQr generador;
	@Autowired
	UserRepository userRepo;
	@Autowired
	TesoreriaService tesoreriaService;
	


    @PostMapping(value = "/addalumno")
    public ResponseEntity<Alumno> addalumno(@RequestBody Alumno alumno)  {    
    
    	
    	try
    	{
    		Alumno save = alumnoService.save(alumno);
    		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    		
    	}catch(Exception e) {
    		return new ResponseEntity<Alumno>(HttpStatus.BAD_REQUEST);
    	}
    }
    

    @GetMapping(value = "/alumnoslogin")
    public ResponseEntity<Alumno> validateAlumno(){
    	return null;
    }
    
    @GetMapping(value = "/{nocarnet}")
    public ResponseEntity<List<Alumno>> getAlumnoandAsignaciones(@PathVariable String nocarnet)  {
    	
    	try
    	{
    		List<Alumno> alumno =  alumnoService.findBylikeCarnet(nocarnet);
    		return new ResponseEntity<List<Alumno>>(alumno, HttpStatus.OK);
    	}
    		
    catch(Exception e) {
		return new ResponseEntity<List<Alumno>>(HttpStatus.BAD_REQUEST);
	}
 
    	
    }
    


    @GetMapping(value = "/getallalumnos")
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

    
    @PostMapping(value = "/adduseralumno")
    public String createalumnoyser(@RequestParam String nocarnet, @RequestParam String correo) throws IOException  {    
    	String apellido = null;
    	Alumno alumno = alumnoService.findOne(nocarnet); 
    	User user = new User();
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	if (alumno != null) {
    		apellido = 	alumno.getApellidos();
        	alumno.setCorreo(correo);
        	alumnoService.save(alumno);
        	
        	CardexTesoreria tesoreria = new CardexTesoreria();
        	tesoreria.setDescripcion("Entrada vendida a: "+alumno.getNombres()+" "+ alumno.getApellidos());
        	Date date = Calendar.getInstance().getTime();
        	tesoreria.setFecha(date);
        	tesoreria.setMonto(80.00);
        	tesoreria.setTipo((long)1);
        	
        	
         	
        	try
        	{
        		String passw = pass.genrar();
        		System.out.println(passw);
        		generador.inicioQr(alumno.getApellidos(),alumno.getCorreo(), alumno.getNoCarnet());
        		mailer.executeMail(nocarnet,passw );
        		user.setUsername(alumno.getNoCarnet());
        		
        		user.setPassword(passwordEncoder.encode(passw));
        		user.isEnabled();
        		userRepo.save(user);
        		tesoreriaService.save(tesoreria);
        	}catch(Exception e) {
        		 e.printStackTrace(); 
        	}
        		
    	}
    	
    	
    	
    	
    	
		return apellido	;
    	
    }

    
    
}
