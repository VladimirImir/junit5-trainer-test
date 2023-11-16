package com.dev.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocalDateFormatterTest {

    @Test
    void format() {
        String date = "2020-11-24";

        var actualResult = LocalDateFormatter.format(date);

        assertThat(actualResult).isEqualTo(LocalDate.of(2020, 11, 24));
    }

    @Test
    void shouldThrowExceptionIfDataInvalid() {
        String date = "2020-11-24 12:25";

        assertThrows(DateTimeParseException.class, () -> LocalDateFormatter.format(date));
    }

    @ParameterizedTest
    @MethodSource("getValidatorArguments")
    void isValid(String date, boolean expectedResult) {
        var actualResult = LocalDateFormatter.isValid(date);

        assertEquals(expectedResult, actualResult);
    }

    static Stream<Arguments> getValidatorArguments() {
        return Stream.of(
                Arguments.of("2020-11-24", true),
                Arguments.of("01-01-2001", false),
                Arguments.of("2020-11-24 12:25", false),
                Arguments.of(null, false)
        );
    }
}