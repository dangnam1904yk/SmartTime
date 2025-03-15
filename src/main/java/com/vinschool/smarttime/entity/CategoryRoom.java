package com.vinschool.smarttime.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class CategoryRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 50)
    String id;
    String codeRoom;
    String nameRoom;
    Date createDate;
    Date updateDate;

    @OneToMany(mappedBy = "categoryRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<TimeSheet> timeSheet;
}
