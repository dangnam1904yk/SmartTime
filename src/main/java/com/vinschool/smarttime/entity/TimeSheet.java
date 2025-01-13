package com.vinschool.smarttime.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 50)
    String id;
    Date createDate;
    String thu;
    Date updateDate;
    String createBy;
    String updateBy;
    String nameTimeSheet;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_class_id", referencedColumnName = "id")
    @ToString.Exclude
    @JsonBackReference
    CategoryClass categoryClass;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_period_id", referencedColumnName = "id")
    @ToString.Exclude
    @JsonBackReference
    CategoryPeriod categoryPeriod;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_subject_id", referencedColumnName = "id")
    @ToString.Exclude
    @JsonBackReference
    private CategorySubject categorySubject;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_room_id", referencedColumnName = "id")
    @ToString.Exclude
    @JsonBackReference
    private CategoryRoom categoryRoom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_time_line")
    @ToString.Exclude
    @JsonBackReference
    TimeLine timeLine;

    @OneToMany(mappedBy = "timeSheet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    List<NoteBook> noteBook;

}
