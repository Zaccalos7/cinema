package com.orbis.cinema.restController;

import com.orbis.cinema.component.LoggerMessageComponent;
import com.orbis.cinema.inputRequest.RegisterRecord;
import com.orbis.cinema.handler.ResponseHandler;
import com.orbis.cinema.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
@Tag(name = "Login", description = "API per login e registrazione")
public class LoginController {

    private final LoginService loginService;
    private final ResponseHandler responseHandler;
    private final LoggerMessageComponent loggerMessageComponent;

    @Operation(summary = "Endpoint per la registrazione, tutti i parametri sono required",
            description = "Restituisce l'esito dell'operazione")
    @PostMapping("register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRecord registerRecord) {
        ResponseEntity<Map<String, String>> mapResponseEntity;
        String message;

        loginService.register(registerRecord);

        mapResponseEntity = responseHandler.buildResponse("success.register.response", HttpStatus.CREATED);
        message = loggerMessageComponent.printMessage("success.register.response");
        log.info("{} {}", message, HttpStatus.CREATED);
        return mapResponseEntity;
    }
}
