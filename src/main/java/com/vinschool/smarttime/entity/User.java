package com.vinschool.smarttime.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 50)
    String id;
    String fullName;
    @JsonIgnore
    String password;
    @Column(length = 10)
    String phone;
    @Column(unique = true)
    String email;
    String displayName;
    boolean active;
    String avatar;
    Date createDate;
    Date updateDate;
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @JsonIgnore
    List<Role> role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonManagedReference
    List<TimeSheet> timeSheet;

}
