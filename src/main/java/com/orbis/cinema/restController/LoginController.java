package com.orbis.cinema.restController;

import com.orbis.cinema.inputRequest.RegisterRecord;
import com.orbis.cinema.responseHandler.ResponseHandler;
import com.orbis.cinema.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/")
public class LoginController {

    private final LoginService loginService;
    private final ResponseHandler responseHandler;

    public LoginController(LoginService loginService, ResponseHandler responseHandler) {
        this.loginService = loginService;
        this.responseHandler = responseHandler;
    }

    @PostMapping("register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRecord registerRecord){
        boolean response = loginService.register(registerRecord);
        ResponseEntity<Map<String, String>> mapResponseEntity = response
                ? responseHandler.buildResponse("success.register.response", HttpStatus.CREATED)
                : responseHandler.buildResponse("error.register.response", HttpStatus.NOT_ACCEPTABLE);

        return mapResponseEntity;
    }
}
