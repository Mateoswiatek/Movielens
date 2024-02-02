package com.swiatek.mateusz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}