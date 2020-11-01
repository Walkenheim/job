package com.pl.api.controller;

import com.pl.api.ApiConst;
import com.pl.api.model.RecordDto;
import com.pl.exception.CustomFileException;
import com.pl.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// todo add exception handler , swagger

@RestController("/api/" + ApiConst.API_VERSION + "/file")
@AllArgsConstructor
public class FileController {

    FileService fileService;

    @PostMapping
    @ExceptionHandler({CustomFileException.class})
    public void upload(@RequestBody MultipartFile file) {

        fileService.upload(file);
    }

    @GetMapping
    public ResponseEntity<RecordDto> getRecord(@RequestParam Integer id) {

        return new ResponseEntity<>(fileService.getRecord(id), HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteRecord(@RequestParam Integer id) {

        fileService.deleteRecord(id);
    }
}
