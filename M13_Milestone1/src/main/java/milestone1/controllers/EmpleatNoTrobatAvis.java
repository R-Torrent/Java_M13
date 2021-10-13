package milestone1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmpleatNoTrobatAvis {
	
	@ResponseBody
	@ExceptionHandler(EmpleatNoTrobatException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String EmpleatNoTrobatHandler(EmpleatNoTrobatException ex) {
		return ex.getMessage();
	}
	
}
