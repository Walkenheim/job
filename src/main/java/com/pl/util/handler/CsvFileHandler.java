package com.pl.util.handler;

import com.pl.api.model.RecordDto;
import com.pl.util.FileExtensionStrategy;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
@Slf4j
public class CsvFileHandler implements FileExtensionStrategy {

    @Override
    public List<RecordDto> getConvertedRowDTOs(MultipartFile textFile) throws IOException {

        CSVParser csvParser = CSVFormat.newFormat(';').withFirstRecordAsHeader().parse(new InputStreamReader(textFile.getInputStream()));

        return csvParser.getRecords()
                .stream()
                .map(this::convertCsvRecordToRecordDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private RecordDto convertCsvRecordToRecordDto(CSVRecord csvRecord) {

        String primaryKey = null;
        String name = null;
        String description = null;
        String updatedTimestamp = null;

        RecordDto recordDto = new RecordDto();

        try {

            primaryKey = csvRecord.get("PRIMARY_KEY");
            name = csvRecord.get("NAME");
            description = csvRecord.get("DESCRIPTION");
            updatedTimestamp = csvRecord.get("UPDATED_TIMESTAMP");

            recordDto.setPrimaryKey(Integer.parseInt(primaryKey))
                    .setName(name)
                    .setDescription(description)
                    .setUpdatedTimestamp(LocalDate.parse(updatedTimestamp, DateTimeFormatter.ofPattern("dd.MM.yyyy")));

            return recordDto;

        } catch (Throwable e) {

            log.error("Cannot parse row with data PRIMARY_KEY {}, NAME {}, DESCRIPTION {} , UPDATED_TIMESTAMP {}",
                    primaryKey, name, description, updatedTimestamp);
        }

        return null;
    }
}
