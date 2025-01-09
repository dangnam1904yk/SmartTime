package com.vinschool.smarttime.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class NoteBook {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 50)
    String id;
    Date createDate;
    Date updateDate;
    String createBy;
    String updateBy;
    @Column(length = 2000)
    String lessonTopic;
    Integer studentErrorCount = 0;
    @Column(length = 2000)
    String detailErrorStudent;
    @Column(length = 2000)
    String generalComment;
    Double lessonEvaluation = 10.0;

    @ManyToOne
    @JoinColumn(name = "id_time_sheet")
    TimeSheet timeSheet;
}
