package com.swiatek.mateusz.model;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;


@Entity
@Table(name="tags")
public class Tag {
    @Id
    //TODO sprawdzic czy to dziala? w poprzednim roku nie dizalalo, jesli nie, to zakomendowac i tyle
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ToString.Exclude
    @ManyToOne(optional = false) // not null
    User user;
    @ToString.Exclude
    @ManyToOne(optional = false)
    Movie movie;


    LocalDate date;
    String tag;


}
