package milestone1.controllers;

import java.util.NoSuchElementException;

public class EmpleatNoTrobatException extends NoSuchElementException {
	
	private static final long serialVersionUID = 1L;
	
	EmpleatNoTrobatException(Long id) {
		super("Empleat " + id + " desconegut/da");
	}
	
}
