package com.acme.edu.logger.formatters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Java_9 on 29.08.2017.
 */
public class DefaultLoggerFormatterTest {
    private LoggerFormatter loggerFormatter;
    @Before
    public void setUp() throws Exception {
        loggerFormatter = new DefaultLoggerFormatter();
    }

    @Test
    public void shouldContainPrefixAndFormatedTextWhenFormatArray() {
        int[] arr = {3,21,63};
        String result = loggerFormatter.format(arr);

        assertEquals(result, "primitives array: {3, 21, 63}");
    }

    @Test
    public void shouldLogEmptyArrayOfPrimitives() {
        int[] array = new int[0];
        String result = loggerFormatter.format(array);

        assertEquals(result, "primitives array: {}");
    }

    @Test
    public void shouldFormatObjectUsingToString() {
        Object mockedObject = mock(Object.class);
        String toStringValue = "my object string";
        when(mockedObject.toString()).thenReturn(toStringValue);

        String result = loggerFormatter.format(mockedObject);

        assertEquals(result, "reference: " + toStringValue);
    }

    @Test
    public void shouldLogNullObjectWithoutNPE() {
        String result = loggerFormatter.format((Object) null);

        assertEquals(result, "reference: null");
    }

    @Test
    public void shouldLogNullStringWithoutNPE() {
        String result = loggerFormatter.format((String) null);

        assertEquals(result, "string: null");
    }

    @Test
    public void shouldLogNullArrayWithoutNPE() {
        String result = loggerFormatter.format((int[]) null);

        assertEquals(result, "primitives array: null");
    }
}