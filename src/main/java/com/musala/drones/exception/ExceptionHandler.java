package com.musala.drones.exception;

import com.musala.drones.model.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(org.springframework.web.bind.annotation.ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<MessageResponse> handleInvalidInputException(RuntimeException ex) {

        logger.error("Exception - Invalid Input : ", ex.getMessage());

        System.out.println("exception : "+ ex.getMessage());

        return new ResponseEntity<>(new MessageResponse("Exception : ",ex.getMessage(),java.time.LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }




}
