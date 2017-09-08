package com.seminarioUMG.seminario.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.model.Qr;
import com.seminarioUMG.seminario.model.Rol;
import com.seminarioUMG.seminario.model.User;
import com.seminarioUMG.seminario.model.UserPermission;
import com.seminarioUMG.seminario.services.RoleService;
import com.seminarioUMG.seminario.services.UserRepository;
import com.seminarioUMG.seminario.services.UserRoleService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	UserRoleService userRole;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleService rolService;
	
	
    @GetMapping(value = "/createuser")
    public String createUser(@RequestParam String username ,@RequestParam String pass, @RequestParam String rol){
    	User user = new User();
    	UserPermission userrole = new UserPermission();
    String respuesta;
    
    Rol role = rolService.findrolByDesc(rol);
    	
    	try {
    		
    		
    		userrole.setRoles(role);
    		userrole.setUsername(user);
    		
    		
    		user.setPassword(passwordEncoder.encode(pass));
        	user.setUsername(username);
        	
        
        	
        	userRepo.save(user);
        	userRole.save(userrole);
			respuesta = "Usuario Creado Correctaente";
		} catch (Exception e) {
			System.out.println(e);
			respuesta = "Usuario no Creado";
		}

		return respuesta;

    	
    }
	

    @GetMapping(value = "/roles")
    public List<String> roles(){
    	
    	List<String> roles = rolService.roles();
    	
    	
		return roles;

    	
    }

    
    
}
