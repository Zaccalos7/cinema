package com.orbis.cinema.service;

import com.orbis.cinema.dto.CredentialDto;
import com.orbis.cinema.inputRequest.RegisterRecord;
import com.orbis.cinema.mapping.CredentialMapper;
import com.orbis.cinema.model.Credential;
import com.orbis.cinema.model.User;
import com.orbis.cinema.repository.CredentialRepository;
import com.orbis.cinema.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final CredentialMapper credentialMapper;
    private final CredentialRepository credentialRepository;

    public LoginService(UserRepository userRepository, CredentialMapper credentialMapper, CredentialRepository credentialRepository) {
        this.userRepository = userRepository;
        this.credentialMapper = credentialMapper;
        this.credentialRepository = credentialRepository;
    }

    public boolean register(RegisterRecord registerRecord){
//        Credential credential = Credential.builder()
//                .email(registerRecord.email())
//                .password(registerRecord.password())
//                .build();

        Credential credential = makeCredential(registerRecord.email(), registerRecord.password());

        User user = User.builder()
                .nickName(registerRecord.nickName())
                .credential(credential)
                .build();

        credentialRepository.save(credential);
        userRepository.save(user);
        return true;
    }

    private Credential makeCredential(String email , String password){
        String passwordEncoded = cryptPassword(password);

        CredentialDto credentialDto = CredentialDto.builder()
                .email(email)
                .password(passwordEncoded)
                .build();

        Credential credential = credentialMapper.toModel(credentialDto);

        credentialRepository.save(credential);
        return credential;
    }

    private void makeUser(){

    }

    private String cryptPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncoded = encoder.encode(password);
        return passwordEncoded;
    }
}
