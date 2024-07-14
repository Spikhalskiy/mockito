/*
 * Copyright (c) 2016 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.util.reflection.generic;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("unused")
public class SimpleTypesTest {
    private Integer integer;
    private Number number;
    private String string;

    @ParameterizedTest
    @CsvSource({
        "integer  ,integer  ,true",
        "integer  ,number   ,true",
        "number   ,integer  ,false",
        "integer  ,string   ,false",
        "string   ,integer  ,false"
    })
    public void testSimpleTypes(String sourceFieldName, String targetFieldName, boolean matches)
            throws NoSuchFieldException {
        Field sourceField = SimpleTypesTest.class.getDeclaredField(sourceFieldName);
        Field targetField = SimpleTypesTest.class.getDeclaredField(targetFieldName);
        GenericTypeMatch sourceMatch = GenericTypeMatch.ofField(sourceField);
        GenericTypeMatch targetMatch = GenericTypeMatch.ofField(targetField);
        assertEquals(matches, targetMatch.matches(sourceMatch));
    }
}
