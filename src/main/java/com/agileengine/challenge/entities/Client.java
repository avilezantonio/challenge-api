package com.agileengine.challenge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client extends BaseEntity {
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String email;
}
