package com.orbis.cinema.exceptions;

import com.orbis.exception.annotations.ExceptionMaker;
import com.orbis.exception.annotations.ExceptionRunner;

@ExceptionMaker(classesName = {"NotValidException"})
public interface HandlerExceptionInterface {

    @ExceptionRunner(exceptionClass = "NotValidException", componentModel = "spring")
    public void runnerNotValidException(String message);
}
