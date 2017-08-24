package com.seminarioUMG.seminario.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.model.Qr;
import com.seminarioUMG.seminario.services.QrService;

@RestController
public class QrController {
	private static Logger LOG = LoggerFactory.getLogger(QrController.class);
	private final String CODIGO_ACTUALIZADO = "001";
	private final String CODIGO_EXISTENTE = "000";
	private final String CODIGO_NO_EXISTE = "002";
	private final String CODIGO_NO_INGRESO = "003";
	@Autowired QrService servicio;
	@PutMapping(value = "/updateQr")
	public String updateQr(@RequestBody Qr qr) {
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
						 
						 
						 if(!codigo.isRefaccion()==true)
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

}
