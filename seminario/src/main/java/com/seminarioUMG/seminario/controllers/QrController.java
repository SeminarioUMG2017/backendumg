package com.seminarioUMG.seminario.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.model.CardexTesoreria;
import com.seminarioUMG.seminario.model.Qr;
import com.seminarioUMG.seminario.services.QrService;

@RestController
public class QrController {
	private static Logger LOG = LoggerFactory.getLogger(QrController.class);
	private final String CODIGO_ACTUALIZADO = "Codigo Actualizado";
	private final String CODIGO_EXISTENTE = "Estado ya ingresado";
	private final String CODIGO_NO_EXISTE = "Codigo no registrado en base de datos";
	private final String CODIGO_NO_INGRESO = "Codigo no registro ingreso a evento";
	@Autowired QrService servicio;
	@PostMapping(value = "/updateQr")
	public @ResponseBody String updateQr(@RequestBody Qr qr) {
		 String codigoValidacion = "";
		 try {
			
			 if(qr.getCadena()!= null)
			 {
				 Qr codigo = servicio.findByCadena(qr.getCadena());
				 
				 if(codigo != null)
				 {
					 
					 if(qr.isIngreso() != false) {
						
						 if(codigo.isIngreso() != true)
						 {
							 codigo.setIngreso(true);
							 codigoValidacion = CODIGO_ACTUALIZADO;
						 }
						 else
						 {
							 codigoValidacion = CODIGO_EXISTENTE;
						 }
						
						
					 }
					 if (qr.isRefaccion() != false)
					 {
						 
						 
						 if(codigo.isRefaccion()==true)
						 {
							
							 codigoValidacion = CODIGO_EXISTENTE;
						 }
						 else
						 {
							 codigo.setRefaccion(true);
							 codigoValidacion = CODIGO_ACTUALIZADO; 
							 
						 }
						 
					 }
					 if (qr.isDiploma()!= false)
					 {
						
							
							 if(codigo.isDiploma()!= true && codigo.isIngreso()==true)
							 {
								 codigo.setDiploma(true);
								 codigoValidacion = CODIGO_ACTUALIZADO;
							 }
							 else {
								 codigoValidacion =CODIGO_NO_INGRESO;
							 }
					 }
					 
					 servicio.save(codigo);
				 }
				 else {
					 codigoValidacion = CODIGO_NO_EXISTE;
				 }
				 
			 }
		 }catch(Exception e) {
			 codigoValidacion = "Error";
			 
		 }
		
		 return codigoValidacion;
	 }
	@GetMapping("/getInfoQR/{carnet}")
	 public Qr obtenerInfoQR(@PathVariable("carnet") String carnet) throws ParseException{
 		
		 Qr codigoQr = servicio.findOne(carnet);
		return codigoQr;
	 }
	

}
