package com.example.demo.exceptions.handlers;

import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.model.error.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class StudentExceptionHandler {

    /**
     * This is a custom Exception Handler for StudentNotFoundException.
     *
     * @param studentNotFoundException
     * @return ResponseEntity for StudentErrorResponse with status code as 404
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> exceptionHandler(StudentNotFoundException studentNotFoundException) {
        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
        studentErrorResponse.setCode(HttpStatus.NOT_FOUND.value());
        studentErrorResponse.setMessage(exceptionMessage(studentNotFoundException));
        studentErrorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * This is a generic Exception Handler.
     *
     * @param exception
     * @return ResponseEntity for StudentErrorResponse with status code as 400
     */
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<StudentErrorResponse> exceptionHandler(Exception exception) {
        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
        studentErrorResponse.setCode(HttpStatus.BAD_REQUEST.value());
        studentErrorResponse.setMessage(exceptionMessage(exception));
        studentErrorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse, HttpStatus.BAD_REQUEST);
    }


    private String exceptionMessage(Exception exception) {
        return String.format("%s exception occurred during request processing: %s", exception.getClass().getSimpleName(), exception.getMessage());
    }
}
