package com.orbis.cinema.handler;


import com.orbis.cinema.exceptions.FileReadingException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionsHandler {

    private final MessageSource messageSource;

    public ExceptionsHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException ex){
        Map<String, String> errorResponse = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                        {
                            String code = fieldError.getDefaultMessage();
                            String message = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
                            errorResponse.put(fieldError.getField(), message);
                        }

                );

        return  ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Map<String, String>> handleException(SQLException ex){
        Map<String, String> errorResponse = new HashMap<>();

        String code = "sql error";
        String errorMessage = ex.getLocalizedMessage();
        errorResponse.put(code, errorMessage);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(FileReadingException.class)
    public ResponseEntity<Map<String, String>> handleException(FileReadingException ex){
        Map<String, String> errorResponse = new HashMap<>();

        String code = "KO";
        String errorCodeMessage = ex.getLocalizedMessage();
        String errorMessage = messageSource.getMessage(errorCodeMessage, null, LocaleContextHolder.getLocale());
        errorResponse.put(code, errorMessage);
        return ResponseEntity.internalServerError().body(errorResponse);
    }


}
