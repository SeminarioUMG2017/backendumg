package com.seminarioUMG.seminario.controllers;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.methods.GeneradorQr;
import com.seminarioUMG.seminario.methods.Mailer;
import com.seminarioUMG.seminario.methods.MailerCatedratico;
import com.seminarioUMG.seminario.methods.PasswordGenerator;
import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.CardexTesoreria;
import com.seminarioUMG.seminario.model.Catedratico;
import com.seminarioUMG.seminario.model.Curso;
import com.seminarioUMG.seminario.model.Rol;
import com.seminarioUMG.seminario.model.User;
import com.seminarioUMG.seminario.model.UserPermission;
import com.seminarioUMG.seminario.resultmodels.ResultadoAlumnos;
import com.seminarioUMG.seminario.resultmodels.ResultadoCatedratico;
import com.seminarioUMG.seminario.services.AlumnoService;
import com.seminarioUMG.seminario.services.CatedraticoService;
import com.seminarioUMG.seminario.services.CursosService;
import com.seminarioUMG.seminario.services.TesoreriaService;
import com.seminarioUMG.seminario.services.UserRepository;
import com.seminarioUMG.seminario.services.UserRoleService;

@RestController
@RequestMapping(value = "/catedratico")
public class CatedraticoController {
	@Autowired
	AlumnoService alumnoService; 
	@Autowired 
	@Qualifier("CursosService")
	CursosService cursoService;
	@Autowired 
	UserRoleService userRoleService;
	@Autowired
	CatedraticoService catedraticoService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	PasswordGenerator pass ;
	@Autowired 
	GeneradorQr generador;
	@Autowired
	UserRepository userRepo;
	@Autowired
	TesoreriaService tesoreriaService;
	@Autowired
	MailerCatedratico mailer;
	
    @GetMapping(value = "getAlumnos/{idCurso}")
    public ResponseEntity<List<ResultadoAlumnos>> getAlumnoandAsignaciones(@PathVariable String idCurso)  {
    	
    	try
    	{
    		List<ResultadoAlumnos> alumno =  alumnoService.findByCourseAlumno(idCurso); 
    		System.out.println(alumno.size());
    		return new ResponseEntity<List<ResultadoAlumnos>>(alumno, HttpStatus.OK);

    	}
    		
    catch(Exception e) {
    	System.out.println(e);
		return new ResponseEntity<List<ResultadoAlumnos>>(HttpStatus.BAD_REQUEST);
	}

    }	
    	

    
    
    @GetMapping(value = "/getCourses")
    public ResponseEntity<ResultadoCatedratico> getCourses(@RequestParam Integer idCatedratico)  {
    	
    	try
    	{
    		Catedratico catedratico = catedraticoService.findOne(idCatedratico);
    		ResultadoCatedratico result = new ResultadoCatedratico();
    		result.setApellidos(catedratico.getApellidos());
    		result.setCorreo(catedratico.getCorreo());
    		result.setNoCarnet(catedratico.getIdCatedratico());
    		result.setNombres(catedratico.getNombres());
    		
    		List<Map<String, Curso>> listcursos = new ArrayList <Map<String, Curso>>();
    		
    		List<Curso> cursos  = catedraticoService.getCourses(idCatedratico);
    		
    		for (Curso cu : cursos) {
    			
    			Map<String , Curso> mapcurso = new HashMap<String , Curso>();
    			
    			mapcurso.put("curso", cu);
    			listcursos.add(mapcurso);
    			
    			
    		}
    		
 ;
    	
    				List<List<Map<String, Curso>>> jsoncursos = new ArrayList <List<Map<String, Curso>>>();	
    				
    				
    				jsoncursos.add(listcursos);
    		result.setAsignaciones(jsoncursos);  
    		
    		
   
    		return new ResponseEntity<ResultadoCatedratico>(result, HttpStatus.OK); 

    	}
    		
    catch(Exception e) {
    	System.out.println(e); 
		return new ResponseEntity<ResultadoCatedratico>(HttpStatus.BAD_REQUEST);
	}

    }	

    
    
    
    
    
    
    
    
    
    
        @GetMapping(value = "/allcatedraticos")
        public ResponseEntity<List<Catedratico>> getAllCatedraicos()  {
        	
        	try
        	{
        		List<Catedratico> catedraticos =  catedraticoService.findAll();
        	
        		return new ResponseEntity<List<Catedratico>>(catedraticos, HttpStatus.OK);

        	}
        		
        catch(Exception e) {
        	System.out.println(e);
    		return new ResponseEntity<List<Catedratico>>(HttpStatus.BAD_REQUEST);
    	}
    	
    	
    }
    @GetMapping(value ="/getCursosCatedratico/{idCatedratico}")
    public ResponseEntity<List<Curso>> getCursosCatedratico(@PathVariable("idCatedratico") String catedratico)
    {
 
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
    
    
    @PostMapping(value = "/addusercatedratico")
    public String CompradeTicket(@RequestParam String id_catedratico, @RequestParam String correo, @RequestParam String idUsuario) throws IOException  {    
    	String nombre = null;
    	Catedratico catedratico = catedraticoService.findOne(Integer.parseInt(id_catedratico)); 
    	User user = new User();
    
    	if (catedratico != null) {
    		nombre = catedratico.getNombres();
        	catedratico.setCorreo(correo);
        	catedraticoService.save(catedratico);
        	
        	CardexTesoreria tesoreria = new CardexTesoreria();
        	UserPermission userrole = new UserPermission();
        	Rol rol = new Rol();
        	rol.setCodigoRol(2);
        	rol.setDescripcion("ROLE_CATEDRATICO");
        	tesoreria.setDescripcion("Entrada vendida al catedratico: "+catedratico.getNombres()+" "+ catedratico.getApellidos());
        	Date date = Calendar.getInstance().getTime();
        	tesoreria.setFecha(date);
        	tesoreria.setMonto(100.00);
        	tesoreria.setTipo((long)1);
        	tesoreria.setIdUsuario(Integer.parseInt(idUsuario));
        	
        	
         	
        	try
        	{
        		String passw = pass.genrar();
        		System.out.println(passwordEncoder.encode(passw));
        		generador.inicioQr(catedratico.getApellidos(),catedratico.getCorreo(), catedratico.getIdCatedratico().toString());
        		mailer.executeMail(id_catedratico,passw );
        		user.setUsername(catedratico.getIdCatedratico().toString());
        		user.setPassword(passwordEncoder.encode(passw));
        		user.setConfirm(0);
        		userRepo.save(user);
        		tesoreriaService.save(tesoreria);
        		userrole.setRoles(rol);
        		userrole.setUsername(user);
        		userRoleService.save(userrole);
        	}catch(Exception e) {
        		
        		System.out.println("error"+e);
        		 e.printStackTrace(); 
        	} 
        		 
    	}
    	
    	
    	
    	
    	
		return "Realizado"	;
    	
    }

    
    

}
