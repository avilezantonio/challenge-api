package com.agileengine.challenge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.ORDINAL)
    private Role role;
}
