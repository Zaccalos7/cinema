package com.orbis.cinema.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pkid;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToOne(mappedBy = "credential")
    private User user;
}
