package com.pl.dao.impl;

import com.pl.api.model.RecordDto;
import com.pl.dao.FileDao;
import com.pl.exception.CustomFileException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// todo for real database , I would use entity instead of dto

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CsvFileDaoImpl implements FileDao {

    static final String KEY_NAME = "csvRecords";

    //This map is representation of some DataBase
    Map<String, List<RecordDto>> ownDatabase = new HashMap<>();

    @Override
    public void insert(List<RecordDto> recordDTOs) {

        ownDatabase.put(KEY_NAME, recordDTOs);
    }

    @Override
    public RecordDto getById(int id) {

      List<RecordDto> recordDTOs = ownDatabase.get(KEY_NAME);

      return recordDTOs
              .stream()
              .filter(dto -> dto.getPrimaryKey() == id)
              .findFirst().orElseThrow(() -> new CustomFileException("Not found record in storage by Id"));
    }

    @Override
    public void delete(Integer id) {

        List<RecordDto> recordDtos = ownDatabase.get(KEY_NAME);

        RecordDto recordDto = getById(id);

        recordDtos.remove(recordDto);

        ownDatabase.put(KEY_NAME, recordDtos);
    }
}
