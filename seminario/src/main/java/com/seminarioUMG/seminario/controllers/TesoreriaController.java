package com.seminarioUMG.seminario.controllers;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.model.CardexTesoreria;
import com.seminarioUMG.seminario.services.TesoreriaService;


@RestController
@RequestMapping(value = "/tesoreria")
public class TesoreriaController {
	private static Logger LOG = LoggerFactory.getLogger(TesoreriaController.class);
	@Autowired TesoreriaService serviceTesoreria;
	
	 @GetMapping(value = {"/ingresos/","/ingresos/{tipo}", "/ingresos/{clase}/{dateI}",
			 "/ingresos/{clase}/{dateI}/{dateF}" })
	 public List<CardexTesoreria> obtenerTodosIngresos(@PathVariable("tipo") Optional<Long> tipo, 
			 @PathVariable("dateI") Optional<Date> dateI, @PathVariable("dateF") Optional<Date> dateF, 
			 @PathVariable("hoy") Optional<Integer> hoy){
	
		 List<CardexTesoreria> ingresos = null;
		
		 if(!tipo.isPresent() && !dateI.isPresent() && !dateF.isPresent() && !hoy.isPresent())
		 {
			 ingresos = serviceTesoreria.findByTipo(tipo);
		 }
		 else if (hoy.isPresent()){
			 
//			 ingresos = serviceTesoreria.findByTipoAndFecha(tipo,new Date());
		 }
		
		 return ingresos;
	 }
	 
	 @PostMapping(value = "/addIngreso")
	 public void addIngreso(@RequestBody CardexTesoreria cardex) {
		 serviceTesoreria.save(cardex);
	 }
	 @GetMapping(value = "/ingresoshoy/{tipo}")
	 public List<CardexTesoreria> obteneringresosGastosHoy(@PathVariable("tipo") Optional<Long> tipo) throws ParseException
	 {
		 List<CardexTesoreria> ingresos = null;
		 if(tipo.isPresent())
		 {
			 SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat formatoHora = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			 Date fecha = new Date();
			 String fechaConvertidaI = null;
			 String fechaConvertidaF = null;
			 fechaConvertidaI = formatoFecha.format(fecha);
			 LOG.info("Fecha "+fechaConvertidaI.toString());
//			 fechaConvertidaF =fechaConvertidaI.concat(" 23:59:59");
			 fechaConvertidaI=fechaConvertidaI.concat(" 00:00:00");
			 
			 Date fechaI= formatoHora.parse(fechaConvertidaI);
			 LOG.info("fecha Inicial" +fechaConvertidaI);
//			 Date fechaF = formatoHora.parse(fechaConvertidaF);
//			 LOG.info("fecha Final" +fechaF.toString());
//			 
			 ingresos = serviceTesoreria.findByTipoAndFechaBetweenEst(tipo);
		 
		 }
		 return ingresos;
	 }

}
