package com.pl.service.impl;

import com.pl.dao.FileDao;
import com.pl.exception.CustomFileException;
import com.pl.api.model.RecordDto;
import com.pl.util.FileExtensionHandler;
import com.pl.util.FileExtensionStrategy;
import com.pl.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    FileDao fileDao;

    @Override
    public void upload(MultipartFile file) {

        try {
            //Get file extension
            String fileExtension = Optional.ofNullable(FilenameUtils.getExtension(file.getOriginalFilename()))
                    .orElseThrow(() -> new CustomFileException("Not found file extension"));

            //Get handler by file extension
            FileExtensionStrategy fileExtensionStrategy = FileExtensionHandler.getStrategyByFileExtension(fileExtension);

            //Parse records from file to RecordDto
            List<RecordDto> convertedRecordDTOS = fileExtensionStrategy.getConvertedRowDTOs(file);

            fileDao.insert(convertedRecordDTOS);

        } catch (CustomFileException e) {

            log.error(e.getErrorMessage(), e);
            throw e;

        } catch (Exception e) {

            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public RecordDto getRecord(Integer id) {

       return fileDao.getById(id);
    }

    @Override
    public void deleteRecord(Integer id) {

        fileDao.delete(id);
    }
}
