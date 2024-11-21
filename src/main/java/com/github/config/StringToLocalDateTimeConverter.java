package com.github.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Override
    public LocalDateTime convert(String source) {
        try {
            return LocalDateTime.parse(source, FORMATTER);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de data inválido para LocalDateTime: " + source);
        }
    }
}
