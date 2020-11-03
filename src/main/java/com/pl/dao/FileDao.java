package com.pl.dao;

import com.pl.api.model.FilterDto;
import com.pl.api.model.PaginationDto;
import com.pl.api.model.RecordDto;

import java.util.List;

public interface FileDao {

    void insert(List<RecordDto> recordDTOs);

    RecordDto getById(int id);

    void delete(Integer id);

    PaginationDto<List<RecordDto>> getRecords(FilterDto filterDto);
}
