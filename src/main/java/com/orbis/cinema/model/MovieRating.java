package com.orbis.cinema.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String site;

    private String reviewerName;

    private Float vote;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

}
