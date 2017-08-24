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
	
	 @GetMapping(value = {"/rubro/{tipo}" })
	 public List<CardexTesoreria> obtenerTodosIngresos(@PathVariable("rubro") Optional<Long> tipo) throws ParseException{
	
		 List<CardexTesoreria> ingresos = null;
		 SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		 
		 if(tipo.isPresent())
		 {
			 ingresos = serviceTesoreria.findByTipo(tipo);
		 }
		 
		 return ingresos;
	 }
	 @GetMapping(value = {"/ingresosFecha/{fechaI}" })
	 public List<CardexTesoreria> obtenerTodosIngresosInicial(@PathVariable("fechaI") String fechaI) throws ParseException{
		 List<CardexTesoreria> ingresos = null;
		 SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		 Long tipo =(long) 1;
		 Date fecha = formatoFecha.parse(fechaI);
		 ingresos = serviceTesoreria.findByTipoAndFechaI(tipo, fecha);
		return ingresos;
	 }
	 
	 @GetMapping(value = {"/ingresosRangoFecha/{fechaI}/{fechaF}" })
	 public List<CardexTesoreria> obtenerTodosIngresosRango(@PathVariable("fechaI") String fechaI, @PathVariable("fechaF") String fechaF ) throws ParseException{
	 		
		 List<CardexTesoreria> ingresos = null;
		 SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		 fechaI = fechaI.concat(" 00:00:00");
		 fechaF = fechaF.concat(" 23:59:59");
		 
		 Long tipo =(long) 1;
		 ingresos = serviceTesoreria.findByTipoAndFechaBetween(tipo, formatoFecha.parse(fechaI), formatoFecha.parse(fechaF));
		return ingresos;
	 }
	 
	 
	 @GetMapping(value = {"/egresosFecha/{fechaI}" })
	 public List<CardexTesoreria> obtenerTodosEgresosInicial(@PathVariable("fechaI") String fechaI) throws ParseException{
		 List<CardexTesoreria> ingresos = null;
		 SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		 Long tipo =(long) 2;
		 Date fecha = formatoFecha.parse(fechaI);
		 ingresos = serviceTesoreria.findByTipoAndFechaI(tipo, fecha);
		return ingresos;
	 }
	 
	 @GetMapping(value = {"/egresosRangoFecha/{fechaI}/{fechaF}" })
	 public List<CardexTesoreria> obtenerTodosEgresosRango(@PathVariable("fechaI") String fechaI, @PathVariable("fechaF") String fechaF ) throws ParseException{
	 		
		 List<CardexTesoreria> ingresos = null;
		 SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		 fechaI = fechaI.concat(" 00:00:00");
		 fechaF = fechaF.concat(" 23:59:59");
		 
		 Long tipo =(long) 2;
		 LOG.info("Fecha final "+formatoFecha.parse(fechaF));
		 ingresos = serviceTesoreria.findByTipoAndFechaBetween(tipo, formatoFecha.parse(fechaI), formatoFecha.parse(fechaF));
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
			 
			 ingresos = serviceTesoreria.findByTipoAndFechaBetweenEst(tipo);
		 
		 }
		 return ingresos;
	 }

}
