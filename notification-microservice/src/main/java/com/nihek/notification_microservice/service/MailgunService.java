package com.nihek.notification_microservice.service;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailgunService {

//    @Value("${mailgun.api}")
//    String apiKey ;
//    @Value("${mailgun.domain}")
//    String domain;
    public void sendEmail(String email){

        String apikey = "cf866f1edcaf9db4be340f89e015c69d-d8df908e-4e992d49";
        String domain  = "sandboxe173ccbda8fa4a75aeba1696e2c3a053.mailgun.org";

        // Crea un cliente de Mailgun
        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(apikey)
                .createApi(MailgunMessagesApi.class);
        // Construye el mensaje
        Message message = Message.builder()
                .from("Excited User <USER@sandboxe173ccbda8fa4a75aeba1696e2c3a053.mailgun.org>") // Remitente
                .to(email) // Destinatario
                .subject("Creación cuenta en nihek") // Asunto
                .text("Este es el contenido del correo en texto plano.") // Cuerpo del mensaje (texto plano)
                .html("<h1>GRACIAS POR CONFIAR EN NIHEK :)</h1>") // Cuerpo del mensaje (HTML)
                .build();
        try {
            MessageResponse response = mailgunMessagesApi.sendMessage(domain,message);
        }catch (RuntimeException e){
            e.getMessage();
        }
    }
}
