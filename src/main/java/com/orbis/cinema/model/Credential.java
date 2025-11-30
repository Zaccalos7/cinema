package com.orbis.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credentials")
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
