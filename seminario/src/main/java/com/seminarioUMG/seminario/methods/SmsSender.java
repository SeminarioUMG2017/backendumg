package com.seminarioUMG.seminario.methods;


import java.net.URISyntaxException;

import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.twilio.sdk.Twilio;
import com.twilio.sdk.creator.api.v2010.account.MessageCreator;
import com.twilio.sdk.exception.ApiException;
import com.twilio.sdk.resource.api.v2010.account.Message;
import com.twilio.sdk.type.PhoneNumber;


@Service
public class SmsSender {
	private static final Log logger = LogFactory.getLog(SmsSender.class);
	
	private static final String ACCOUNT_SID = "AC6ab9fce84a88f8593ce03fbb0eec75de";
	private static final String AUTH_TOKEN = "4690548318023a1ce44047067c255450";
	private static final String Twilio_From ="17722911608";
	

    public void sender(String to , String smsMessage) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        MessageCreator creator = Message.create(ACCOUNT_SID, new PhoneNumber(to), new PhoneNumber(Twilio_From), smsMessage); 

        try {
        	
        	 Message message = creator.execute();
;
		} catch (ApiException e) {
			
			
			
			switch (e.getMessage()) {
			case "Authenticate":
				logger.error("Error en la autenticacion, Verificar SID y Token");
				break;

			default:
				logger.error("Error en el envio del sms" + e.getMessage()); 
				break;
			}
			
			
		}
       
    }
}