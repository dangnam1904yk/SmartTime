package com.vinschool.smarttime.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 50)
    String id;
    String className;
    Date createDate;
    String createById;
    boolean isActive;

    @OneToMany(mappedBy = "categoryClass", cascade = CascadeType.ALL)
    List<TimeSheet> timeSheet;
}
