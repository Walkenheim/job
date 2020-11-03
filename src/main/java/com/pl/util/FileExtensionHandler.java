package com.pl.util;

import com.pl.exception.FileException;
import com.pl.util.handler.CsvFileHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public final class FileExtensionHandler {

    static Map<String, FileExtensionStrategy> strategies = new HashMap<>() {{
        put("csv", new CsvFileHandler());
    }};

    public static FileExtensionStrategy getStrategyByFileExtension(String fileExtension) {

        return Optional.ofNullable(strategies.get(fileExtension))
                .orElseThrow(() -> new FileException("Unsupported file extension"));
    }
}
