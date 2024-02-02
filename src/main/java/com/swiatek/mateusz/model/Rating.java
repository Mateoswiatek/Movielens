package com.swiatek.mateusz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="ratings")
public class Rating  {

    @Data
    @Embeddable
    public static class RatingId implements Serializable {

        @ManyToOne
        @JoinColumn(name = "movie_id", insertable = false, updatable = false)
        private Movie movie;

        @ManyToOne
        @JoinColumn(name = "user_id", insertable = false, updatable = false)
        private User user;

        public RatingId(Movie movie, User user){
            this.setMovie(movie);
            this.setUser(user);
        }

        public RatingId() {
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RatingId other)) return false;
            // tutaj dzieki instanceof mamy odrazu ohter, i nie trzeba rzutowac
            return Objects.equals(other.getMovie(), this.getMovie())
                    && Objects.equals(other.getUser(), this.getUser());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getUser().getId(), getMovie().getId());
        }
    }
    @EmbeddedId
    RatingId ratingId = new RatingId();

    void setUser(User user){
        ratingId.setUser(user);
    }
    void setMovie(Movie movie){
        ratingId.setMovie(movie);
    }
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    User user;
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    Movie movie;

    double rating;
    Date date;
}