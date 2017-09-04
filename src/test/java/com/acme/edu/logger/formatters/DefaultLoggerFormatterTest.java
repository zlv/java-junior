package com.acme.edu.logger.formatters;

import com.acme.edu.logger.messaging.messages.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
        IntArrayMessage intArrMessage = new IntArrayMessage(arr);
        String result = loggerFormatter.accept(intArrMessage);

        assertEquals(result, "primitives array: {3, 21, 63}");
    }

    @Test
    public void shouldLogEmptyArrayOfPrimitives() {
        int[] array = new int[0];
        IntArrayMessage intArrMessage = new IntArrayMessage(array);
        String result = loggerFormatter.accept(intArrMessage);

        assertEquals(result, "primitives array: {}");
    }

    @Test
    public void shouldFormatObjectUsingToString() {
        Object mockedObject = mock(Object.class);
        String toStringValue = "my object string";
        when(mockedObject.toString()).thenReturn(toStringValue);

        String result = loggerFormatter.accept(new ObjectMessage(mockedObject));

        assertEquals(result, "reference: " + toStringValue);
    }

    @Test
    public void shouldLogNullObjectWithoutNPE() {
        String result = loggerFormatter.accept(new ObjectMessage(null));

        assertEquals(result, "reference: null");
    }

    @Test
    public void shouldLogNullStringWithoutNPE() {
        String result = loggerFormatter.accept(new StringMessage(null, 1));

        assertEquals(result, "string: null");
    }

    @Test
    public void shouldLogNullArrayWithoutNPE() {
        String result = loggerFormatter.accept(new IntArrayMessage(null));

        assertEquals(result, "primitives array: null");
    }

    @Test
    public void shouldPrintStringWithoutSuffixWhenPassSingleString() {
        String result = loggerFormatter.accept(new StringMessage("test", 1));

        assertEquals(result, "string: test");
    }

    @Test
    public void shouldPrintStringWithSuffixWhenPassMultipleStrings() {
        String result = loggerFormatter.accept(new StringMessage("test", 2));

        assertEquals(result, "string: test (x2)");
    }

    @Test
    public void shouldPrintFormatedTextWhenPassByte() {
        byte value = 42;
        String result = loggerFormatter.accept(new ByteMessage(value));

        assertEquals(result, "primitive: 42");
    }

    @Test
    public void shouldPrintFormatedTextWhenPassInt() {
        int value = 42;
        String result = loggerFormatter.accept(new IntMessage((value)));

        assertEquals(result, "primitive: 42");
    }

}