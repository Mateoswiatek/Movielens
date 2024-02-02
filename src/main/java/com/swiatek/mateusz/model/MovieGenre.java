package com.swiatek.mateusz.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="movie_genres")
public class MovieGenre {
    @Embeddable
    @Data // aby uproscic
    public static class MovieGenreId implements Serializable {
        @ManyToOne // wiele moze wskazywac na jeden film
        private Movie movie;
        private String genre;

        public MovieGenreId(Movie movie, String genre){
            this.setMovie(movie);
            this.setGenre(genre);
        }

        public MovieGenreId() {
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MovieGenreId other)) return false;
            // tutaj dzieki instanceof mamy odrazu ohter, i nie trzeba rzutowac
            return Objects.equals(other.getGenre(), this.getGenre()) &&
                    Objects.equals(this.getMovie(), other.getMovie());
        }
        @Override
        public int hashCode() {
            return Objects.hash(getGenre(), getMovie().getId());
        }
    }

    @EmbeddedId // tutaj jest nasz klucz
    MovieGenre.MovieGenreId movieGenreId = new MovieGenreId();

    public MovieGenre(Movie movie, String genre) {
        this.setMovie(movie);
        this.setGenre(genre);
    }

    public MovieGenre() {

    }

    public Movie getMovie() {
        return movieGenreId.movie;
    }

    public void setMovie(Movie movie) {
        movieGenreId.movie = movie;
    }

    public String getGenre() {
        return movieGenreId.genre;
    }

    public void setGenre(String genre) {
        movieGenreId.genre = genre;
    }

    public String toString(){
        return movieGenreId.getGenre();
    }


}
