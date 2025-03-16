package com.vinschool.smarttime.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Role> role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    List<TimeSheet> timeSheet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    List<DetailNotification> detailNotifications;

    @OneToOne
    AccountSchedule accountSchedules;

}
