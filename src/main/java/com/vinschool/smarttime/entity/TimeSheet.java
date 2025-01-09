package com.vinschool.smarttime.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "category_class_id", referencedColumnName = "id")
    CategoryClass categoryClass;

    @ManyToOne
    @JoinColumn(name = "category_period_id", referencedColumnName = "id")
    private CategoryPeriod categoryPeriod;

    @ManyToOne
    @JoinColumn(name = "category_subject_id", referencedColumnName = "id")
    private CategorySubject categorySubject;

    @ManyToOne
    @JoinColumn(name = "category_room_id", referencedColumnName = "id")
    private CategoryRoom categoryRoom;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_time_line")
    TimeLine timeLine;

    @OneToMany(mappedBy = "timeSheet", cascade = CascadeType.ALL)
    List<NoteBook> noteBook;

}
