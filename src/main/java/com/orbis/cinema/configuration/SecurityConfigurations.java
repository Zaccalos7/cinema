package com.orbis.cinema.configuration;

import com.orbis.cinema.component.LoggerMessageComponent;
import com.orbis.cinema.exceptions.HandlerExceptionInterface;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Slf4j
@Configuration
public class SecurityConfigurations {

    private final HandlerExceptionInterface handlerExceptionInterface;
    private final LoggerMessageComponent loggerMessageComponent;

    public SecurityConfigurations(HandlerExceptionInterface handlerExceptionInterface, LoggerMessageComponent loggerMessageComponent) {
        this.handlerExceptionInterface = handlerExceptionInterface;
        this.loggerMessageComponent = loggerMessageComponent;
    }

    public String[] retrievesConfigurations(){
        String[] configurations = new String[2];
        configurations[0] = getSignKey();
        return configurations;
    }

    private String getSignKey(){
        String signKey = "";
        Path envPath = Path.of("cinema/.env").toAbsolutePath();

        try(Stream<String> lines = Files.lines(envPath)){
            signKey = lines
                    .findFirst()
                    .map(line-> line.split("=", 2)[1])
                    .orElse(handlerExceptionInterface.runnerFileReadingException("file.is.empty"));

            return  signKey;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            loggerMessageComponent.printMessage("error.during.reading.file");
            handlerExceptionInterface.runnerFileReadingException("error.during.reading.file");
        }
        return signKey;
    }
}
