package com.orbis.cinema.security;

import com.orbis.cinema.inputRequest.LoginRecord;
import com.orbis.cinema.model.Credential;
import com.orbis.cinema.repository.CredentialRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticatorService {
    private final CredentialRepository credentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilComponent jwtUtilComponent;

    public String login(@Valid LoginRecord loginRecord) {
        String email = loginRecord.email();
        String password = loginRecord.password();

        Credential credential = credentialRepository.findByEmail(email);

        if (credential == null) {
            throw new RuntimeException("Credenziali non valide");
        }

        boolean match = passwordEncoder.matches(
                password,
                credential.getPassword()
        );

        if (!match) {
            throw new RuntimeException("Credenziali non valide");
        }

        String bear= null;
        bear = jwtUtilComponent.generateToken(email);
        return bear;
    }


    private Credential retrivesUserCredentials(String email, String password){
        Credential credential = credentialRepository.findByEmailAndPassword(email, password);

        if(credential == null){

        }
        return null;
    }
}
