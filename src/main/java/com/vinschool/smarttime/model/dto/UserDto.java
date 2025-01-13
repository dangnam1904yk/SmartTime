package com.vinschool.smarttime.model.dto;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)

public class UserDto {
    String id;
    String fullName;
    String phone;
    String email;
    String displayName;
    boolean active;
    String avatar;
    Date createDate;
    Date updateDate;
}
