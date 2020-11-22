package com.task.floorplan.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException
            (RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateErrorMessage(exception));
    }


    @ExceptionHandler(NoSuchRoomException.class)
    public ResponseEntity<Object> handleNoSuchRoomException
            (NoSuchRoomException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateErrorMessage(exception));
    }


    @ExceptionHandler(InfiniteAreaException.class)
    public ResponseEntity<Object> handleInfiniteAreaException
            (InfiniteAreaException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateErrorMessage(exception));
    }


    @ExceptionHandler(WallsIntersectException.class)
    public ResponseEntity<Object> handleWallsIntersectException
            (WallsIntersectException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateErrorMessage(exception));
    }


    @ExceptionHandler(WallsDiagonalException.class)
    public ResponseEntity<Object> handleWallsDiagonalException
            (WallsDiagonalException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateErrorMessage(exception));
    }

    @ExceptionHandler(NoEnoughCornersException.class)
    public ResponseEntity<Object> handleNoEnoughCornersException
            (NoEnoughCornersException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateErrorMessage(exception));
    }


    private Map<String, String> generateErrorMessage(Exception exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", exception.getMessage());
        return errorResponse;
    }
}
