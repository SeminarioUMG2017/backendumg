package com.seminarioUMG.seminario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.model.CardexTesoreria;
import com.seminarioUMG.seminario.services.TesoreriaService;

@RestController
@RequestMapping(value = "/tesoreria")
public class TesoreriaController {
	
	@Autowired TesoreriaService serviceTesoreria;
	
	 @GetMapping(value = "/ingresos")
	 public List<CardexTesoreria> obtenerTodosIngresos(){
		 List<CardexTesoreria> ingresos = serviceTesoreria.findByTipo(1);
		 return ingresos;
	 }
	 
	 @PostMapping(value = "/addIngreso")
	 public void addIngreso(@RequestBody CardexTesoreria cardex) {
		 serviceTesoreria.save(cardex);
	 }

}
