package com.geekbrains.demoboot.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

}
