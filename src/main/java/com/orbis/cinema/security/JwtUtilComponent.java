package com.orbis.cinema.security;

import com.orbis.cinema.configuration.SecurityConfigurations;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Component
public class JwtUtilComponent {


    private final SecurityConfigurations securityConfigurations;


    private static final long EXPIRATION = 1000L * 60 * 60 * 24 * 7 * 30; // 1 mese

    public JwtUtilComponent(SecurityConfigurations securityConfigurations) {
        this.securityConfigurations = securityConfigurations;
    }

    protected String generateToken(String subject) {

        String token;
        String[] configurations = getValueFromEnv();
        String SECRET_KEY = configurations[0];

        token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return token;
    }

    private String[] getValueFromEnv(){
        String[] configurations =  securityConfigurations.retrievesConfigurations();
        return configurations;
    }
}
