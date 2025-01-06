package com.vinschool.smarttime.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class CategoryClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 50)
    private String id;
    private String className;
    private Date createDate;
    private String createById;
    private boolean isActive;
}
