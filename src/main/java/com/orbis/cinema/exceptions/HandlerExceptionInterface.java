package com.orbis.cinema.exceptions;

import com.orbis.exception.annotations.ExceptionMaker;
import com.orbis.exception.annotations.ExceptionRunner;

@ExceptionMaker(classesName = {"NotValidException", "FileReadingException"})
public interface HandlerExceptionInterface {

    @ExceptionRunner(exceptionClass = "NotValidException", componentModel = "spring")
    void runnerNotValidException(String message);

    @ExceptionRunner(exceptionClass = "FileReadingException", componentModel = "spring")
    String runnerFileReadingException(String message);
}
