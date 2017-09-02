


package com.seminarioUMG.seminario.methods;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.Qr;
import com.seminarioUMG.seminario.services.AlumnoService;
import com.seminarioUMG.seminario.services.QrService;


@Service
public class Mailer {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	AlumnoService alumnoService;
	@Autowired
	GeneradorQr generador;
	@Autowired
	QrService qrservice;
	@Autowired
	ServletContext servletContext;
	@Autowired
	Environment environment;
	
	public void executeMail(String nocarnet , String pass) throws IOException{
	
	 final Locale locale = null;
	 final Context ctx = new Context(locale.US);
	 final Context ctxno = new Context(locale.US);
	 
	 Alumno alumno = alumnoService.findOne(nocarnet);
	 
	
	 String to = alumno.getCorreo();

    String from = "noreplyumg@gmail.com"; 

    final String username = "noreplyumg@gmail.com";
    final String password = "SeminarioUMG2017";

    String host = "smtp.gmail.com";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.socketFactory.fallback","false");
    props.put("mail.smtp.ssl.enable","true");


    Session session = Session.getInstance(props,
       new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication(username, password);
          }
       });
    

    try {

     	        Message message = new MimeMessage(session);

     	        message.setFrom(new InternetAddress(from));
     	        message.setRecipients(Message.RecipientType.TO,
     	           InternetAddress.parse(to));

     	        message.setSubject("Seminario UMG 2017");

     	        BodyPart messageBodyPart = new MimeBodyPart();
 

     	        Qr	qr = qrservice.findOne(nocarnet);
     	        
     	        String qrpath = qr.getNoCarnet(); 	   
   

     	       	ctxno.setVariable("name", alumno.getNombres() );
     	    	ctxno.setVariable("nocarnet", nocarnet);
     	    	ctxno.setVariable("pass", pass);
     	     	ctxno.setVariable("user", nocarnet);
     	    	
     	    	System.out.println("Real  " +servletContext.getRealPath("/"));
     	    	System.out.println("Context "+servletContext.getContextPath());
     	    	ctxno.setVariable("qr","http://34.233.183.228:8080/seminario/codigosqr/"+nocarnet+".png");
     	    	ctxno.setVariable("logo","http://34.233.183.228:8080/seminario/img/logo.jpg"); 
     	    	
     	    
     	    	final String htmlContentno = this.templateEngine.process("email", ctxno);   	
     	    	
     	    	message.setContent(htmlContentno, "text/html; charset=utf-8");
  
     	        messageBodyPart.setContent(htmlContentno, "text/html"); 
     	        // Create a multipar message
     	        Multipart multipart = new MimeMultipart();
     	        multipart.addBodyPart(messageBodyPart);
     	        messageBodyPart = new MimeBodyPart();

     	        message.setContent(multipart);




 				

     	        Transport.send(message);
     	 
     	     } catch (MessagingException i) {

     	    	 i.printStackTrace();
     	     }

}



	
	
}
