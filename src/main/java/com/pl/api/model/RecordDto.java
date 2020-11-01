package com.pl.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class RecordDto {

    int primaryKey;
    String name;
    String description;
    LocalDate updatedTimestamp;
}
