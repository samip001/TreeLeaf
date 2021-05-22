package com.treeleaf.treeleafvehicletracking.error;

import com.treeleaf.treeleafvehicletracking.error.exception.CameraIDNotFoundException;
import com.treeleaf.treeleafvehicletracking.error.exception.LocationIDNotFoundException;
import com.treeleaf.treeleafvehicletracking.error.exception.VehicleIDNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    @ExceptionHandler(LocationIDNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> LocationIDNotFoundException(LocationIDNotFoundException ex,
                                                                                     WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(dateFormat.format(new Date()), ex.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CameraIDNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> CameraIDNotFoundException(CameraIDNotFoundException ex,
                                                                                     WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(dateFormat.format(new Date()), ex.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VehicleIDNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> vehicleIDNotFoundException(VehicleIDNotFoundException ex,
                                                                             WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(dateFormat.format(new Date()), ex.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // global exception
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(dateFormat.format(new Date()), ex.getMessage(),
                request.getDescription(false),HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
