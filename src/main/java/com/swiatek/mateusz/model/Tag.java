package com.swiatek.mateusz.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="tags")
public class Tag {
//    @Id
    //TODO sprawdzic czy to dziala? w poprzednim roku nie dizalalo, jesli nie, to zakomendowac i tyle
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    Long id;
    @Embeddable
    @Data // aby uproscic
    public static class TagId implements Serializable {
        @ManyToOne // wiele moze wskazywac na jeden film
        @JoinColumn(name = "movie_id", insertable = false, updatable = false)
        private Movie movie;
        @ManyToOne
        @JoinColumn(name = "user_id", insertable = false, updatable = false)
        private User user;

        public TagId(Movie movie, User user){
            this.setMovie(movie);
            this.setUser(user);
        }

        public TagId() {
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Tag.TagId other)) return false;
            // tutaj dzieki instanceof mamy odrazu ohter, i nie trzeba rzutowac
            return Objects.equals(other.getMovie(), this.getMovie())
                    && Objects.equals(other.getUser(), this.getUser());
        }

        @Override
        public int hashCode() {
        return Objects.hash(getUser().getId(), getMovie().getId());
    }

        public void setUser(User user) { this.user = user; }
        public void setMovie(Movie movie) { this.movie = movie; }
    }
    @EmbeddedId // tutaj jest nasz klucz
    public TagId tagId = new TagId();

    @ToString.Exclude
    @ManyToOne(optional = false) // not null
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    User user;
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    Movie movie;

//    LocalDate date;
    Date date;
    String tag;
}
