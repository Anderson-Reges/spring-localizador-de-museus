package com.betrybe.museumfinder.advice;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleControllerException {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleGenericInternalServerErro() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno!");
  }

  @ExceptionHandler(InvalidCoordinateException.class)
  public ResponseEntity<String> handleBadRequest(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coordenada inválida!");
  }

  @ExceptionHandler(MuseumNotFoundException.class)
  public ResponseEntity<String> handleNotFound(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Museu não encontrado!");
  }

}
