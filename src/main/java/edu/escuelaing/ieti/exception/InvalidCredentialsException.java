package edu.escuelaing.ieti.exception;

import javax.ws.rs.InternalServerErrorException;

import org.springframework.http.HttpStatus;

import edu.escuelaing.ieti.dto.ServerErrorResponseDto;
import edu.escuelaing.ieti.enums.ErrorCodeEnum;

public class InvalidCredentialsException extends InternalServerErrorException{
    
    public InvalidCredentialsException() {

        super(new ServerErrorResponseDto("User not found", ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND).getMessage());
    }
    
}
