package com.nihek.notification_microservice.config;

import com.nihek.notification_microservice.proccessors.UserRegisteredProccessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Configuration
public class StreamConfig {

    @Bean
    public Consumer<Flux<String>> userRegisteredBinding(final UserRegisteredProccessor userRegisteredProccessor){
        return (flujo) -> userRegisteredProccessor.proccess(flujo);
    }
}
