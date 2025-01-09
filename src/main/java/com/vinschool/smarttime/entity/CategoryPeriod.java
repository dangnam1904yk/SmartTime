package com.vinschool.smarttime.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class CategoryPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 50)
    String id;
    String codePeriod;
    String namePeriod;
    boolean isAcitve;
    Date createDate;
    Date updateDate;
    @OneToMany(mappedBy = "categoryPeriod", cascade = CascadeType.ALL)
    List<TimeSheet> timeSheet;
}
