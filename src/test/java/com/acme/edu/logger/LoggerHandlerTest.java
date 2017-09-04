package com.acme.edu.logger;

import com.acme.edu.legacy.SysoutCaptureAndAssertionAbility;
import com.acme.edu.logger.formatters.LoggerFormatter;
import com.acme.edu.logger.messaging.*;
import com.acme.edu.logger.savers.LoggerPrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * Created by Java_9 on 29.08.2017.
 */

public class LoggerHandlerTest implements SysoutCaptureAndAssertionAbility {

    private LoggerHandler logger;
    @Mock
    private LoggerPrinter mockedPrinter;
    private LoggerFormatter formatter = new MockedFormatterForTests();


    @Before
    public void setUp() {
        initMocks(this);
        captureSysout();
        resetOut();
        logger = new LoggerHandler(message -> {
            try {
                mockedPrinter.println(message.accept(formatter));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void shouldPrintBoolean() throws IOException {

        //when
        logger.log(new BooleanMessage(false));

        //then
        verify(mockedPrinter, times(1)).println("my boolean: false");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintIntSum() throws IOException {
        //when
        logger.log(new IntMessage(8));
        logger.log(new IntMessage(7));
        logger.log(new FlushMessage());

        //then
        verify(mockedPrinter, times(1)).println("my int value: 15");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintByteSum() throws IOException {
        //when
        logger.log(new ByteMessage((byte) 23));
        logger.log(new ByteMessage((byte) 7));
        logger.log(new FlushMessage());

        //then
        verify(mockedPrinter, times(1)).println("my byte value: 30");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintByteSumOverflow() throws IOException {
        //when
        logger.log(new ByteMessage(Byte.MAX_VALUE));
        logger.log(new ByteMessage((byte) 27));
        logger.log(new FlushMessage());

        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("my byte value: 27");
        inOrder.verify(mockedPrinter, times(1)).println("my byte value: " + Byte.MAX_VALUE);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldPrintIntSumOverflow() throws IOException {

        //when
        logger.log(new IntMessage(Integer.MIN_VALUE / 2 - 17));
        logger.log(new IntMessage(Integer.MIN_VALUE / 2 - 15));
        logger.log(new FlushMessage());

        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("my int value: -32");
        inOrder.verify(mockedPrinter, times(1)).println("my int value: " + Integer.MIN_VALUE);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldAggregateStringValues() throws IOException {
        //when
        String myString = "myString";
        logger.log(new StringMessage(myString, 1));
        logger.log(new StringMessage(myString, 1));
        logger.log(new StringMessage(myString, 1));
        logger.log(new FlushMessage());

        //then
        verify(mockedPrinter, times(1)).println("my string " + myString + " occurred 3 times");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldLogArray() throws IOException {
        //when
        logger.log(new IntArrayMessage(new int[] {5, 3, -1}));

        //then
        verify(mockedPrinter, times(1)).println("my array: [5, 3, -1]");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldLogObjectsUsingToString() throws IOException {
        //given
        Object mockedObject = mock(Object.class);
        when(mockedObject.toString()).thenReturn("toString value");

        //when
        logger.log(new ObjectMessage(mockedObject));

        //then
        verify(mockedPrinter, times(1)).println("my object: toString value");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintSingleStringWithoutCount() throws IOException {
        //when
        String inputString = "test string";
        logger.log(new StringMessage(inputString, 1));
        logger.log(new FlushMessage());

        //then
        verify(mockedPrinter, times(1)).println("my string " + inputString + " occurred 1 times");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintDifferentNonAggregatedTypes() throws IOException {
        //when
        logger.log(new ObjectMessage(42));
        logger.log(new BooleanMessage(true));
        logger.log(new FlushMessage());

        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("my object: 42");
        inOrder.verify(mockedPrinter, times(1)).println("my boolean: true");
        inOrder.verifyNoMoreInteractions();
    }

    @Test(expected = RuntimeException.class)
    public void shouldHandleExceptionFromPrinter() throws IOException {
        //given
        doThrow(new IOException()).when(mockedPrinter).println(anyString());

        //when
        logger.log(new BooleanMessage(false));

        //then
        fail();
    }

    @Test
    public void shouldLogStringsWhenPassDifferentStrings() throws IOException {
        //given
        String myString = "myString";
        String myString2 = "myString2";

        //when
        logger.log(new StringMessage(myString, 1));
        logger.log(new StringMessage(myString, 1));
        logger.log(new StringMessage(myString2, 1));
        logger.log(new FlushMessage());


        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("my string " + myString + " occurred 2 times");
        inOrder.verify(mockedPrinter, times(1)).println("my string " + myString2 + " occurred 1 times");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldPrintCurrentStringStreakWhenBooleanMessageSent() throws IOException {
        //given
        String testString = "testString";

        //when
        logger.log(new StringMessage(testString, 1));
        logger.log(new StringMessage(testString, 1));
        logger.log(new BooleanMessage(true));

        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("my string " + testString + " occurred 2 times");
        inOrder.verify(mockedPrinter, times(1)).println("my boolean: true");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldPrintDifferentMessagesCorrectlyWhenPassBooleanAfterString() throws IOException {
        //given
        String testString = "testString";

        //when
        logger.log(new StringMessage(testString, 1));
        logger.log(new StringMessage(testString, 1));
        logger.log(new BooleanMessage(true));

        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("my string " + testString + " occurred 2 times");
        inOrder.verify(mockedPrinter, times(1)).println("my boolean: true");
        inOrder.verifyNoMoreInteractions();
    }


    @After
    public void tearDown() {
        resetOut();
    }
}