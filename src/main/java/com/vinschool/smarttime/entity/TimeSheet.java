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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

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
    CategoryClass categoryClass;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_period_id", referencedColumnName = "id")
    @ToString.Exclude
    CategoryPeriod categoryPeriod;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_subject_id", referencedColumnName = "id")
    @ToString.Exclude
    private CategorySubject categorySubject;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_room_id", referencedColumnName = "id")
    @ToString.Exclude
    private CategoryRoom categoryRoom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_time_line")
    @ToString.Exclude
    TimeLine timeLine;

    @OneToMany(mappedBy = "timeSheet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<NoteBook> noteBook;

}
