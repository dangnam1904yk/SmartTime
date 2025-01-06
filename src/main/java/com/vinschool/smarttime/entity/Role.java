package com.vinschool.smarttime.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 50)
    String id;
    String nameRole;
    @ManyToMany()
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> users;
}
