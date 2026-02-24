package com.orbis.cinema.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialDto {
    private String email;
    private String password;
    private Boolean hasVerifiedEmail;
}
