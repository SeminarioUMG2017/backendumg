package com.seminarioUMG.seminario.controllers;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.seminarioUMG.seminario.model.Qr;
import com.seminarioUMG.seminario.model.User;
import com.seminarioUMG.seminario.services.UserRepository;

@Controller
public class ConfirmMailController {
	
	@Autowired
	UserRepository userRepo; 
	
    @GetMapping(value = "/confirm/{username}")
    public String confirm(@PathVariable	 String username ,Model model){
    
    	model.addAttribute("logo","http://34.233.183.228:8080/seminario/img/logo.jpg"); 
    	User user = userRepo.findOneByUsername(username);
    	
    	if(user !=null) {
    		model.addAttribute("username",user.getUsername());
    		
    	}else {
    		model.addAttribute("username","nouser");
    	}
    	
    	
		return "confirma";

    	
    }

}
