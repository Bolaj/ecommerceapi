package com.timibolaji.ecommerce.api.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private @NotBlank String firstName;
    private @NotBlank String lastName;
    private @NotBlank String email;
    private @NotBlank String password;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
