package com.pl.dao.impl;

import com.pl.api.model.FilterDto;
import com.pl.api.model.PaginationDto;
import com.pl.api.model.RecordDto;
import com.pl.dao.FileDao;
import com.pl.exception.FileException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


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
    public PaginationDto<List<RecordDto>> getRecords(FilterDto filterDto) {

        PaginationDto<List<RecordDto>> paginationDto = new PaginationDto<>();

        if (isNull(filterDto.getDateFrom()) && isNull(filterDto.getDateTo())) {

            return paginationDto
                    .setData(ownDatabase)
                    .setLimit(ownDatabase.size())
                    .setOffSet(0);
        }

        List<RecordDto> filteredRecords = new ArrayList<>();
        for (RecordDto recordDto : ownDatabase) {
            if (recordDto.getUpdatedTimestamp().isAfter(filterDto.getDateFrom()) && recordDto.getUpdatedTimestamp().isBefore(filterDto.getDateTo())) {
                filteredRecords.add(recordDto);
            }
        }

        return paginationDto
                .setData(filteredRecords)
                .setLimit(filteredRecords.size())
                .setOffSet(0); // In real database, I will fill metadata from resultSet by db query


    }
}
