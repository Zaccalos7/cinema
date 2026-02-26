package com.orbis.cinema.model;

import jakarta.persistence.*;

import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String language;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
