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
import com.seminarioUMG.seminario.model.Curso;
import com.seminarioUMG.seminario.model.Qr;
import com.seminarioUMG.seminario.model.Rol;
import com.seminarioUMG.seminario.model.User;
import com.seminarioUMG.seminario.model.UserPermission;
import com.seminarioUMG.seminario.resultmodels.Password;
import com.seminarioUMG.seminario.services.AlumnoService;
import com.seminarioUMG.seminario.services.AsignacionService;
import com.seminarioUMG.seminario.services.CursosService;
import com.seminarioUMG.seminario.services.QrService;
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
	@Autowired
	@Qualifier("CursosService")
	CursosService cursoService;
	@Autowired
	AsignacionService asignacionService;
	@Autowired
	QrService qrService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	


    @PostMapping(value = "/addalumno")
    public ResponseEntity<Alumno> AgregarAlumno(@RequestBody Alumno alumno)  {    
    	try
    	{
    		 alumnoService.save(alumno);
    		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    		
    	}catch(Exception e) {
    		return new ResponseEntity<Alumno>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    
    @PostMapping(value = "/changepassword")
    public String ChangePassword(@RequestBody Password password)  {
    	
    String respuesta;
    		try {
    			
    			User user = userRepo.findOneByUsername(password.getNocarnet());
    			
    			if (passwordEncoder.matches(password.getActualpass(), user.getPassword())) {
    				System.out.println(password.getConfirmpass());
    				System.out.println(password.getNewpass());		
    				if(password.getNewpass().equals(password.getConfirmpass()) ) {
    		
    					user.setPassword(passwordEncoder.encode(password.getConfirmpass()));
    					userRepo.save(user);
    					respuesta = "Password Actualizado correctamente";
    				}else {
    					
    					respuesta = "Nueva Password y Confirmacion no coinciden";
    					 
    				}
    				
    			} else {
    				respuesta = "Password anterior no es correcto";
    				
    			}
				
			} catch (Exception e) {
				respuesta = "Error al cambiar password";
			}
    	
    	
    	
    	
		return respuesta;     
    	
    }
    
    
    
    @PostMapping(value = "/updatealumno/{nocarnet}")
    public String  UpdateAlumno(@PathVariable String nocarnet,@RequestParam String nombres ,@RequestParam String apellido , @RequestParam String correo)  {    
    	try
    	{
    		Alumno alumno = alumnoService.findOne(nocarnet);
    		
    		if(alumno != null) 
    		alumno.setApellidos(apellido);
    		alumno.setNombres(nombres);
    		alumno.setCorreo(correo);
    		 alumnoService.save(alumno);
    		 alumnoService.save(alumno);
    		return "OK";
    		
    	}catch(Exception e) {
    		return "Error Actualizando Datos";
    	}
    }
    

    @GetMapping(value = "/getqrbyid")
    public String getQrById(@RequestParam String nocarnet){
    	
    	
    	Qr qr = qrService.getOne(nocarnet);
    	if(qr != null) {
    		
    		String url =" http://34.233.183.228:8080/seminario/codigosqr/"+nocarnet+".png"; 
    		return url;
    	}else {
    		return "no se encuentra la imagen";
    	}

    	
    }
    
    @PostMapping(value = "/asignarcurso")
    public ResponseEntity<String> AsignarCurso(@RequestParam String nocarnet, @RequestParam String idCurso){

    	AsignacionCursos asignacion = new AsignacionCursos();
    	
    	Curso curso = cursoService.findOne(idCurso);
    	Alumno alumno = alumnoService.findOne(nocarnet);
    	asignacion.setCurso(curso);
    	asignacion.setAlumno(alumno);    	
    	asignacionService.save(asignacion);

    	return null;
    }
    
    
    @PostMapping(value = "/deleteasignacion")
    public  String QuitarAsignacion(@RequestParam Integer correlativo){
  	
    	
    	try
    	{
    		asignacionService.delete(correlativo);
    		return "Eliminado";
    	}
    		
    catch(Exception e) {
		return "No Se encontro la asignacion";
	}
    	
    }
    
    @GetMapping(value = "/{nocarnet}")
    public ResponseEntity<List<Alumno>> GetAlumnoByCarnet(@PathVariable String nocarnet)  {
    	
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
    public ResponseEntity<List<Alumno>> TodosLosAlumnos() throws IOException  {    
    	try
    	{
    		List<Alumno> Alumnos = (List<Alumno>) alumnoService.findAll();
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
    public String CompradeTicket(@RequestParam String nocarnet, @RequestParam String correo) throws IOException  {    
    	String apellido = null;
    	Alumno alumno = alumnoService.findOne(nocarnet); 
    	User user = new User();
    
    	if (alumno != null) {
    		apellido = 	alumno.getApellidos();
        	alumno.setCorreo(correo);
        	alumnoService.save(alumno);
        	
        	CardexTesoreria tesoreria = new CardexTesoreria();
        	UserPermission userrole = new UserPermission();
        	Rol rol = new Rol();
        	rol.setCodigoRol(1);
        	rol.setDescripcion("ROLE_ALUMNO");
        	tesoreria.setDescripcion("Entrada vendida a: "+alumno.getNombres()+" "+ alumno.getApellidos());
        	Date date = Calendar.getInstance().getTime();
        	tesoreria.setFecha(date);
        	tesoreria.setMonto(80.00);
        	tesoreria.setTipo((long)1);
        	
        	
         	
        	try
        	{
        		String passw = pass.genrar();
        		System.out.println(passwordEncoder.encode(passw));
        		generador.inicioQr(alumno.getApellidos(),alumno.getCorreo(), alumno.getNoCarnet());
        		mailer.executeMail(nocarnet,passw );
        		user.setUsername(alumno.getNoCarnet());
        		
        		user.setPassword(passwordEncoder.encode(passw));
        		userRepo.save(user);
        		tesoreriaService.save(tesoreria);
        		userrole.setRoles(rol);
        		userrole.setUsername(user);
        	}catch(Exception e) {
        		 e.printStackTrace(); 
        	} 
        		 
    	}
    	
    	
    	
    	
    	
		return "Realizado"	;
    	
    }

    
    
    @PostMapping(value = "/reloadmail")
    public String ReenvioPassword(@RequestParam String nocarnet) throws IOException  {    
    

    	if (qrService.findOne(nocarnet) != null) {
    		
    		Alumno alumno = alumnoService.findOne(nocarnet);
    		
    		User user = userRepo.findOneByUsername(alumno.getNoCarnet());
        	try
        	{
        		String passw = pass.genrar();
        		
        		mailer.executeMail(nocarnet,passw );
        		user.setPassword(passwordEncoder.encode(passw));
        		userRepo.save(user);
   
        	
        	}catch(Exception e) {
        		 e.printStackTrace(); 
        	}
        		 
    	}
    	
    	
    	
    	
    	
		return "Realizado"	;
    	
    }
    
    
}
