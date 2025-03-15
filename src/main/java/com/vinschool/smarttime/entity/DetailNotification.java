package com.vinschool.smarttime.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class DetailNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 50)
    String id;
    boolean statusRead;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_notification")
    Notification notification;

}
