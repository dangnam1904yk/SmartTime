package com.vinschool.smarttime.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
     @Column(length = 50)
    String id  ;
    String fullName;
    String password;
    @Column(length = 10)
    String phone;
    @Column(unique = true)
    String email;
    String displayName;
    boolean active ;
    String avatar;
    Date createDate;
    Date updateDate;
    @ManyToMany(mappedBy = "users")
    List<Role> role;

}
