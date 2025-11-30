package com.orbis.cinema.service;

import com.orbis.cinema.dto.CredentialDto;
import com.orbis.cinema.dto.UserDto;
import com.orbis.cinema.inputRequest.RegisterRecord;
import com.orbis.cinema.mapping.CredentialMapper;
import com.orbis.cinema.mapping.UserMapper;
import com.orbis.cinema.model.Credential;
import com.orbis.cinema.model.User;
import com.orbis.cinema.repository.CredentialRepository;
import com.orbis.cinema.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final CredentialMapper credentialMapper;
    private final CredentialRepository credentialRepository;
    private final UserMapper userMapper;

    public boolean register(RegisterRecord registerRecord){
        return makeRegister(registerRecord);

    }

    @Transactional
    private boolean makeRegister(RegisterRecord registerRecord){
        Credential credential = makeCredential(registerRecord.email(), registerRecord.password());
        User user = makeUser(registerRecord.nickName(), credential);

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
