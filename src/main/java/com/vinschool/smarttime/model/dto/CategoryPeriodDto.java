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
public class CategoryPeriodDto {
    String id;
    String codePeriod;
    String namePeriod;
    boolean isAcitve;
    Date createDate;
    Date updateDate;
}
