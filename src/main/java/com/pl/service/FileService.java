package com.pl.service;

import com.pl.api.model.PaginationDto;
import com.pl.api.model.RecordDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface FileService {

    /**
     *  Method retrieve file from client-side, parse data from file by extension of file and save data into the storage
     *
     * @param file from request with any format
     */
    void upload(MultipartFile file);

    /**
     *  Retrieve RecordDto by id from client
     *
     * @param id a Primary key of record
     * @return RecordDto from uploaded file
     */
    RecordDto getRecord(Integer id);

    /**
     *  Remove record from storage by Id
     *
     * @param id a Primary key of record
     */
    void deleteRecord(Integer id);

    /**
     * Get paginated list of records by specific date
     * @param startDate date from param
     * @param endDate date to param
     * @return list of records by specific date with paging meta info
     */
    List<PaginationDto<RecordDto>> getRecords(LocalDate startDate, LocalDate endDate);
}
