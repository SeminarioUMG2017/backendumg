package com.seminarioUMG.seminario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.model.Qr;
import com.seminarioUMG.seminario.services.UserRoleService;

@RestController
public class AdminController {

	@Autowired
	UserRoleService userrole;
	
	
	
    @GetMapping(value = "/prueba")
    public String getQ(@RequestParam String username){
    	
    	System.out.println(userrole.findRoles(username).get(0).getRoles().getDescripcion());
    	
		return null;
    	
    	
    	

    	
    }
	
	
}
