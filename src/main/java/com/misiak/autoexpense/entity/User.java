package com.misiak.autoexpense.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

    public User(String id, String email, String firstName, String lastName, Timestamp signedAt) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.signedAt = signedAt;
    }

    @Id
    private String id;

    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "signed_at")
    private Timestamp signedAt;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Car> cars;

}
