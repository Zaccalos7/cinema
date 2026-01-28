package com.orbis.cinema.service;

import com.orbis.cinema.dto.CredentialDto;
import com.orbis.cinema.dto.UserDto;
import com.orbis.cinema.inputRequest.LoginRecord;
import com.orbis.cinema.inputRequest.RegisterRecord;
import com.orbis.cinema.mapping.CredentialMapper;
import com.orbis.cinema.mapping.UserMapper;
import com.orbis.cinema.model.Credential;
import com.orbis.cinema.model.User;
import com.orbis.cinema.repository.CredentialRepository;
import com.orbis.cinema.repository.UserRepository;
import com.orbis.cinema.security.JwtUtilComponent;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final CredentialMapper credentialMapper;
    private final CredentialRepository credentialRepository;
    private final UserMapper userMapper;
    private final JwtUtilComponent jwtUtilComponent;

    public void register(RegisterRecord registerRecord){
        makeRegister(registerRecord);
    }

    @Transactional
    private void makeRegister(RegisterRecord registerRecord){
        Credential credential = makeCredential(registerRecord.email(), registerRecord.password());
        User user = makeUser(registerRecord.nickName(), credential);

        credentialRepository.save(credential);
        userRepository.save(user);
    }

    private Credential makeCredential(String email , String password){
        String passwordEncoded = cryptPassword(password);

        CredentialDto credentialDto = CredentialDto.builder()
                .email(email)
                .password(passwordEncoded)
                .build();

        Credential credential = credentialMapper.toModel(credentialDto);
        return credential;
    }

    private String cryptPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncoded = encoder.encode(password);
        return passwordEncoded;
    }

    private User makeUser(String nickName, Credential credential){
        UserDto userDto = UserDto.builder()
                .nickName(nickName)
                .build();
        User user = userMapper.toModel(userDto);
        user.setCredential(credential);
        return user;
    }

}
