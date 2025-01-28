package com.nihek.auth_service.controller.RestException;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;
}
