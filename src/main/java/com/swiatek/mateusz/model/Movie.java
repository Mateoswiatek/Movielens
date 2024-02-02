package com.swiatek.mateusz.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@ToString
@Entity
@Table(name="movies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    private Long id;
    private String title;

    // bo w Javie trzeba dawac w druga strone, bo inaczej nie bedzie dzialac
    // jeden film moze miec wiele gatunkow cascade - co ma sie dziac przy usunieciu, inicjalizacja, Lazy - nie mozna dociagac gdy tranzakcja sie skonczy
    @OneToMany(mappedBy = "movieGenreId.movie",cascade= CascadeType.ALL, fetch=FetchType.LAZY)
    private
    Set<MovieGenre> genreList = new HashSet<>();

    // Tutaj tagi do tego filmu, tez moze byc wiecej niz jeden
    @OneToMany(mappedBy = "movie")
    private
    Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private
    Set<Tag> ratings = new HashSet<>();

}