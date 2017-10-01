package  com.seminarioUMG.seminario.controllers;




import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.seminarioUMG.seminario.methods.SmsSender;
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
import com.seminarioUMG.seminario.services.UserRoleService;

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
	@Autowired
	UserRoleService userRoleService;
	@Autowired
	SmsSender smsSender;
	
	


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
     
    @GetMapping(value = "/getqr")
    public List<Map<String,String>> getQrById2(@RequestParam String nocarnet){
    	Map<String,String> mapa = new HashMap<String, String>();
		List<Map<String,String>> lmap = new ArrayList<Map<String,String>>();
    	
    	Qr qr = qrService.getOne(nocarnet);

    		mapa.put("url", " http://34.233.183.228:8080/seminario/codigosqr/"+nocarnet+".png");
    		lmap.add(mapa);

		return lmap;

    	
    }
    

    @PostMapping(value = "/asignarcurso")
    public String AsignarCurso(@RequestParam String nocarnet, @RequestParam String idCurso){

    	AsignacionCursos asignacion = new AsignacionCursos();
    	
    	Curso curso = cursoService.findOne(idCurso);
    	Alumno alumno = alumnoService.findOne(nocarnet);
    	asignacion.setCurso(curso);
    	asignacion.setAlumno(alumno);    	
    	asignacionService.save(asignacion);

    	return "Curso asignado";
    }
    
    
    @PostMapping(value = "/confirmarCorreo")
    public String ConfirmarCorreo(@RequestParam String nocarnet, @RequestParam String noTelefono){
 
    	 
    	String respuesta = null;
    	Alumno alumno = alumnoService.findOne(nocarnet);
    	if(alumno != null || alumno.getPagado()== 0) {   
    		
    	   	if(alumno.getTelefono() == null	) {
        	alumno.setTelefono(noTelefono);
        	alumnoService.save(alumno);
        	respuesta = "Correo confirmado para usuario  "+ nocarnet;
        	String nombre = "Hola "+alumno.getNombres()+" tu correo esta confirmado, por favor no borres este mensaje.";
        	smsSender.sender(numberVerificator(noTelefono),limpiarAcentos(nombre));
    	   	}else {
    	   		respuesta = "Ya esta verificado"+ nocarnet;	
    	   	}
    	}else {
    		respuesta = "Verificar"+ nocarnet;	
    	}
    	return respuesta ;
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
    
    
    
    @GetMapping(value = "/infoalumno")
    public ResponseEntity<List<Alumno>> GetAlumnoByCarnet2(@RequestParam String nocarnet)  {
    	
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
    public String CompradeTicket(@RequestParam String nocarnet, @RequestParam String correo, @RequestParam String idUsuario) throws IOException  {    
    	String apellido = null;
    	Alumno alumno = alumnoService.findOne(nocarnet); 
    	User user = new User();
    	Integer idUs = Integer.parseInt(idUsuario);
    	if (alumno != null) {
    		apellido = 	alumno.getApellidos();
        	alumno.setCorreo(correo);
        	alumno.setPagado(1);
        	alumnoService.save(alumno);
        	
        	CardexTesoreria tesoreria = new CardexTesoreria();
        	UserPermission userrole = new UserPermission();
        	Rol rol = new Rol();
        	rol.setCodigoRol(1);
        	rol.setDescripcion("ROLE_ALUMNO");
        	tesoreria.setDescripcion("Entrada vendida a: "+alumno.getNombres()+" "+ alumno.getApellidos());
        	Date date = Calendar.getInstance().getTime();
        	tesoreria.setFecha(date);
        	tesoreria.setMonto(85.00);
        	tesoreria.setTipo((long)1);
        	tesoreria.setIdUsuario(idUs);
        	
         	
        	try
        	{
        		String passw = pass.genrar();
        		generador.inicioQr(alumno.getApellidos(),alumno.getCorreo(), alumno.getNoCarnet());
        		mailer.executeMail(nocarnet,passw );
        		user.setUsername(alumno.getNoCarnet());
        		user.setConfirm(0);
        		user.setPassword(passwordEncoder.encode(passw));
        		userRepo.save(user);
        		tesoreriaService.save(tesoreria);
        		userrole.setRoles(rol);
        		userrole.setUsername(user);
        		userRoleService.save(userrole);
        	}catch(Exception e) {
        		 e.printStackTrace(); 
        	} 
        		 
    	}
    	
    	
    	
    	
    	
		return "Realizado"	;
    	
    }

     
    
    @SuppressWarnings("unused")
	@PostMapping(value = "/reloadmail")
    public String ReenvioPassword(@RequestParam String nocarnet) throws IOException  {    
    
String respuesta = null;
    	if (qrService.findOne(nocarnet) != null) {

    		Alumno alumno = alumnoService.findOne(nocarnet);
    		
    		System.out.println(alumno.getCorreo());
    		User user = userRepo.findOneByUsername(alumno.getNoCarnet());
        	try
        	{ 
        		if(user != null) {
        			
        	
        		
        		String passw = pass.genrar();
        		
        		mailer.executeMail(nocarnet,passw );
        		user.setPassword(passwordEncoder.encode(passw));
        		userRepo.save(user);
        		respuesta = "Realizado";
        		
        		}else { 
        			respuesta = "Usuario no encontrado";
        			
        		}
        	
        	}catch(Exception e) {
        		 e.printStackTrace(); 
        	}
        		 
    	}else {
    		respuesta = "Usuario no encontrado";
    	}


		return respuesta	;
    	
    }
    
    
	
	
	private String numberVerificator(String numero ) {
		


		numero = numero.replaceAll("\\s","");
		numero = numero.replaceAll("[a-z]","");
		numero = numero.replaceAll("[A-Z]","");
		numero = numero.replaceAll("[\\@!\\$\\&\\/\\(\\)\\=\\?\\¡\\[\\]\\.\\:\\;\\}\\{]","");
		numero = numero.replaceAll("\\-","");
		numero = numero.replaceAll("\\_","");
		numero = numero.replaceAll("\\.","");
		numero = numero.replaceAll("\\*","");

		int length = numero.length();
		switch (length) {
		case 12 :
			Pattern pattern = Pattern.compile("[+]{1}+[502]{3}+[0-9]{8}");
			Matcher matcher = pattern.matcher(numero);
			boolean output = matcher.find();
			if(output == true ){
				numero = numero;
			}else{
				numero = "numero invalido";
			}

			
			break;
		case 11 :
			Pattern pattern11 = Pattern.compile("[502]{3}+[0-9]{8}");
			Matcher matcher11 = pattern11.matcher(numero);
			boolean output11 = matcher11.find();
			if(output11 == true ){
				
				numero = "+"+numero;
			}else{
				numero = "numero invalido";
			}
			
			
			
			break;
			
		case 8 :
			Pattern pattern8 = Pattern.compile("[0-9]{8}");
			Matcher matcher8 = pattern8.matcher(numero);
			boolean output8 = matcher8.find();
			if(output8 == true ){
				
				numero = "+502"+numero;
			}else{
				numero = "numero invalido";
			}
			
			
			
			break;

		default:
			
			numero = "numero invalido";
			
			break;
		}
		return numero;
	}
	
	public static String limpiarAcentos(String cadena) {
	    String limpio =null;
	    if (cadena !=null) {
	        String valor = cadena;
	        valor = valor.toUpperCase();
	        limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
	        limpio = limpio.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
	        limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
	    }
	    return limpio;
	}
	
	
 

    
}
