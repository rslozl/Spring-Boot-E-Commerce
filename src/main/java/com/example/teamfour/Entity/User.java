package com.example.teamfour.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String parola;
    private String isim;
    private String soyisim;
    private String mail;

    @Transient
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

}
