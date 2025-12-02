package com.orbis.cinema.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pkid;

    @Column(nullable = false)
    private String nickName;

    @OneToOne(cascade = CascadeType.ALL)
    private Credential credential;
}
