package com.seminarioUMG.seminario.controllers;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.CardexTesoreria;
import com.seminarioUMG.seminario.model.Qr;
import com.seminarioUMG.seminario.services.AlumnoService;
import com.seminarioUMG.seminario.services.QrService;

@RestController
public class QrController {
	private static Logger LOG = LoggerFactory.getLogger(QrController.class);
	 public static final String IMAGE = "src/main/resources/static/diplomavertical.jpg";
	private final String CODIGO_ACTUALIZADO = "Codigo Actualizado";
	private final String CODIGO_EXISTENTE = "Estado ya ingresado";
	private final String CODIGO_NO_EXISTE = "Codigo no registrado en base de datos";
	private final String CODIGO_NO_INGRESO = "Codigo no registro ingreso a evento";
	@Autowired QrService servicio;
	@Autowired
	AlumnoService alumnoService; 
	@Value("${ruta.diplomas}") String rutaDiploma;
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
//								 codigo.setDiploma(true);
								 List<Alumno> alumno =alumnoService.findBylikeCarnet(codigo.getNoCarnet());
								 crearDiploma(alumno.get(0));
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
			LOG.error("error qr ",e);
			 codigoValidacion = "Error";
			 
		 }
		
		 return codigoValidacion;
	 }
	@GetMapping("/getInfoQR/{carnet}")
	 public Qr obtenerInfoQR(@PathVariable("carnet") String carnet) throws ParseException{
 		
		 Qr codigoQr = servicio.findOne(carnet);
		return codigoQr;
	 }
	
	
	public void crearDiploma(Alumno alumno) throws DocumentException, MalformedURLException, IOException {
		
		
			LOG.info("ruta "+rutaDiploma);
			File archivo = new File(rutaDiploma+"Diploma"+alumno.getNoCarnet()+".pdf");
			BufferedWriter bw;
			if(archivo.exists()) {
			      bw = new BufferedWriter(new FileWriter(archivo));
			} else {
			      bw = new BufferedWriter(new FileWriter(archivo));
			     
			}
		  	Document document = new Document(PageSize.A4);
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(rutaDiploma+"Diploma"+alumno.getNoCarnet()+".pdf"));
	        document.open();
	        float width = document.getPageSize().getWidth();
	        float height = document.getPageSize().getHeight();
	        Paragraph p= new Paragraph(alumno.getNombres()+" "+alumno.getApellidos(), new Font(FontFamily.HELVETICA, 22));
	        Paragraph pEspacio= new Paragraph(" ", new Font(FontFamily.HELVETICA, 22));
	        p.setAlignment(Element.ALIGN_CENTER);
	        document.add(pEspacio);
	        document.add(pEspacio);
	        document.add(pEspacio);
	        document.add(pEspacio);
	        document.add(pEspacio);
	        document.add(pEspacio);
	        document.add(pEspacio);
	        document.add(pEspacio);
	        document.add(p);
	        
	      
	        PdfContentByte canvas = writer.getDirectContentUnder();
	        Image image = Image.getInstance(IMAGE);
	        image.scaleAbsoluteHeight(PageSize.A4.getHeight());
	        image.scaleAbsoluteWidth(PageSize.A4.getWidth());
	        image.setAbsolutePosition(0, 0);
	        canvas.addImage(image);
	        document.close();
		
	}
	

}
