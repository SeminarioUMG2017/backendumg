


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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class Mailer {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public void executeMail(String correo) throws IOException{
	
	 final Locale locale = null;
	 final Context ctx = new Context(locale.US);
	 final Context ctxno = new Context(locale.US);
	 
	 String to = correo;

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

     	        message.setSubject("Facebook Ads");

     	        BodyPart messageBodyPart = new MimeBodyPart();


     	        String logoTPP = "http://drive.google.com/uc?export=view&id="
     	        		+ "0B1BhGir5QE7db3k5Z0RqM2FBeEE";

     	   
   



//     	    	ctxno.setVariable("ultimoenvio", 1);
//     	    	ctxno.setVariable("User", iUser.getDetailbyIdUser(idUser).getFirstName());
//     	    	ctxno.setVariable("count", count);
//     	    	ctxno.setVariable("si",2);
//     	    	ctxno.setVariable("logoTPP", logoTPP);
//     	     	ctxno.setVariable("camp", campaña.getCampname());	
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
