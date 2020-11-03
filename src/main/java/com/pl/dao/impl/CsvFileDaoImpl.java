package com.pl.dao.impl;

import com.pl.api.model.RecordDto;
import com.pl.dao.FileDao;
import com.pl.exception.FileException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.*;


// Representation of some database

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CsvFileDaoImpl implements FileDao {

    List<RecordDto> ownDatabase = new ArrayList<>();

    @Override
    public void insert(List<RecordDto> recordDTOs) {

        ownDatabase.stream()
                .filter(rec -> recordDTOs
                        .stream()
                        .anyMatch(recordDto -> recordDto.getPrimaryKey() == rec.getPrimaryKey())).
                forEach(rec -> {
            throw new FileException("PRIMARY_KEY must be unique");
        });

        ownDatabase.addAll(recordDTOs);
    }

    @Override
    public RecordDto getById(int id) {

        return ownDatabase
                .stream()
                .filter(dto -> dto.getPrimaryKey() == id)
                .findFirst()
                .orElseThrow(() -> new FileException("Not found record in storage by Id"));
    }

    @Override
    public void delete(Integer id) {

        RecordDto recordDto = getById(id);

        if (!ownDatabase.remove(recordDto)) throw new FileException("Cannot remove record from storage by id");
    }

    @Override
    public List<RecordDto> getRecords() {

        return null;
    }
}
