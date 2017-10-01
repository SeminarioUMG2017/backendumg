package com.seminarioUMG.seminario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.model.Qr;
import com.seminarioUMG.seminario.model.User;
import com.seminarioUMG.seminario.resultmodels.Password;
import com.seminarioUMG.seminario.services.QrService;
import com.seminarioUMG.seminario.services.UserRepository;

@RestController
public class GenericController {

	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepo;
	@Autowired
	QrService qrService;  

	
	
	
	
	
	@PostMapping(value = "/changepassword")
    public String ChangePassword(@RequestBody Password password)  {
    	
    String respuesta;
    		try {
    			
    			User user = userRepo.findOneByUsername(password.getUser());
    			
    			if (passwordEncoder.matches(password.getActualpass(), user.getPassword())) {
    				System.out.println(password.getConfirmpass());
    				System.out.println(password.getNewpass());		
    				if(password.getNewpass().equals(password.getConfirmpass()) ) {
    		
    					user.setPassword(passwordEncoder.encode(password.getConfirmpass()));
    					user.setConfirm(1);
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
    
	
	
    @GetMapping(value = "/confirmUser")
    public Integer confirmUser(@RequestParam String username)  {
    	
   
    			User user = userRepo.findOneByUsername(username);
    			
    			
		return user.getConfirm();     
    	
    }
   
    

    @GetMapping(value = "/getqrbyid")
    public String getQrById(@RequestParam String nocarnet){
    	
    	
    	Qr qr = qrService.getOne(nocarnet);
    	if(qr != null) {
    		
    		String url =" http://34.233.183.228:8080/seminario/codigosqr/"+nocarnet+".png"; 
    		return url;
    	}else {
    		return "no se encuentra la imagen, verificar en tesoreria";
    	}

    	
    }
    

	
}
