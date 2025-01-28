package com.nihek.notification_microservice.proccessors;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import com.nihek.notification_microservice.service.MailgunService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class UserRegisteredProccessor {

    private final MailgunService mailgunService;

    public UserRegisteredProccessor(MailgunService mailgunService) {
        this.mailgunService = mailgunService;
    }


    public void proccess(Flux<String> email){
        mailgunService.sendEmail(email.subscribe().toString());
    }
}
