package com.pl.util;

import com.pl.api.model.RecordDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileExtensionStrategy {

    List<RecordDto> getConvertedRowDTOs(MultipartFile textFile) throws IOException;
}
