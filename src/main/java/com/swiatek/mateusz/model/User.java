package com.swiatek.mateusz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor // wymagane jesli z JSONem dzialamy
@AllArgsConstructor
public class User {
    @Id
    Long id;
    String forename;
    String surname;
    String email;
    // Dodanie elementow z tagow gdzie user jest jako klucz obcy
    @OneToMany(mappedBy = "user")
    private
    Set<Tag> tags = new HashSet<>();
}