package com.acme.edu.logger;

import com.acme.edu.legacy.SysoutCaptureAndAssertionAbility;
import com.acme.edu.logger.formatters.LoggerFormatter;
import com.acme.edu.logger.messaging.messages.*;
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
    @Mock
    private LoggerFormatter mockedFormatter;


    @Before
    public void setUp() {
        initMocks(this);
        captureSysout();
        resetOut();
        logger = new LoggerHandler(message -> {
            try {
                mockedPrinter.println(message.visit(mockedFormatter));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void shouldPrintBoolean() throws IOException {
        //given
        when(mockedFormatter.accept(new BooleanMessage(false))).thenReturn("my format with false value");

        //when
        logger.log(new BooleanMessage(false));

        //then
        verify(mockedPrinter, times(1)).println("my format with false value");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintIntSum() throws IOException {
        //given
        when(mockedFormatter.accept(new IntMessage(15))).thenReturn("my format with 15 value");

        //when
        logger.log(new IntMessage(8));
        logger.log(new IntMessage(7));
        logger.log(new FlushMessage());

        //then
        verify(mockedPrinter, times(1)).println("my format with 15 value");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintByteSum() throws IOException {
        //given
        when(mockedFormatter.accept(new ByteMessage((byte) 30))).thenReturn("my format with 30 value");

        //when
        logger.log(new ByteMessage((byte) 23));
        logger.log(new ByteMessage((byte) 7));
        logger.log(new FlushMessage());

        //then
        verify(mockedFormatter, times(1)).accept(new ByteMessage((byte) 30));
        verify(mockedPrinter, times(1)).println("my format with 30 value");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintByteSumOverflow() throws IOException {
        //given
        when(mockedFormatter.accept(new ByteMessage(Byte.MAX_VALUE))).thenReturn("my format with max value");
        when(mockedFormatter.accept(new ByteMessage((byte) 27))).thenReturn("my format with 27 value");

        //when
        logger.log(new ByteMessage(Byte.MAX_VALUE));
        logger.log(new ByteMessage((byte) 27));
        logger.log(new FlushMessage());

        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("my format with 27 value");
        inOrder.verify(mockedPrinter, times(1)).println("my format with max value");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldPrintIntSumOverflow() throws IOException {
        //given
        when(mockedFormatter.accept(new IntMessage(Integer.MIN_VALUE))).thenReturn("my format with min value");
        when(mockedFormatter.accept(new IntMessage(-32))).thenReturn("my format with -32 value");

        //when
        logger.log(new IntMessage(Integer.MIN_VALUE / 2 - 17));
        logger.log(new IntMessage(Integer.MIN_VALUE / 2 - 15));
        logger.log(new FlushMessage());

        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("my format with -32 value");
        inOrder.verify(mockedPrinter, times(1)).println("my format with min value");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldAggregateStringValues() throws IOException {
        //given
        String myString = "myString";
        when(mockedFormatter.accept(new StringMessage(myString, 3))).thenReturn("string: " + myString + " (x3)");

        //when
        logger.log(new StringMessage(myString, 1));
        logger.log(new StringMessage(myString, 1));
        logger.log(new StringMessage(myString, 1));
        logger.log(new FlushMessage());

        //then
        verify(mockedPrinter, times(1)).println("string: " + myString + " (x3)");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldLogArray() throws IOException {
        //given
        when(mockedFormatter.accept(new IntArrayMessage(new int[] {5, 3, -1}))).thenReturn("test arr with values: 5, 3, -1");

        //when
        logger.log(new IntArrayMessage(new int[] {5, 3, -1}));

        //then
        verify(mockedPrinter, times(1)).println("test arr with values: 5, 3, -1");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldLogObjectsUsingToString() throws IOException {
        //given
        Object mockedObject = new Object();
        when(mockedFormatter.accept(new ObjectMessage(mockedObject))).thenReturn("test new test object");

        //when
        logger.log(new ObjectMessage(mockedObject));

        //then
        verify(mockedPrinter, times(1)).println("test new test object");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintSingleStringWithoutCount() throws IOException {
        //given
        String inputString = "test string";
        when(mockedFormatter.accept(new StringMessage(inputString, 1))).thenReturn("formatted test string");

        //when
        logger.log(new StringMessage(inputString, 1));
        logger.log(new FlushMessage());

        //then
        verify(mockedPrinter, times(1)).println("formatted test string");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintDifferentNonAggregatedTypes() throws IOException {
        //given
        when(mockedFormatter.accept(new ObjectMessage(42))).thenReturn("formatted object message");
        when(mockedFormatter.accept(new BooleanMessage(true))).thenReturn("formatted boolean message");

        //when
        logger.log(new ObjectMessage(42));
        logger.log(new BooleanMessage(true));
        logger.log(new FlushMessage());

        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("formatted object message");
        inOrder.verify(mockedPrinter, times(1)).println("formatted boolean message");
        inOrder.verifyNoMoreInteractions();
    }

    @Test(expected = RuntimeException.class)
    public void shouldHandleExceptionFromPrinter() throws IOException {
        //given
        doThrow(new IOException()).when(mockedPrinter).println(anyString());
        when(mockedFormatter.accept(new BooleanMessage(false))).thenReturn("formatted boolean message");

        //when
        logger.log(new BooleanMessage(false));

        //then
        fail();
    }

    @Test
    public void shouldLogStringsWhenPassDifferentStrings() throws IOException {
        //given
        String myString = "myString";
        when(mockedFormatter.accept(new StringMessage(myString, 2))).thenReturn("string: " + myString + " (x2)");
        String myString2 = "myString2";
        when(mockedFormatter.accept(new StringMessage(myString2, 1))).thenReturn("string: " + myString2);

        //when
        logger.log(new StringMessage(myString, 1));
        logger.log(new StringMessage(myString, 1));
        logger.log(new StringMessage(myString2, 1));
        logger.log(new FlushMessage());


        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("string: " + myString + " (x2)");
        inOrder.verify(mockedPrinter, times(1)).println("string: " + myString2);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldPrintCurrentStringStreakWhenBooleanMessageSent() throws IOException {
        //given
        String testString = "testString";
        when(mockedFormatter.accept(new StringMessage(testString, 2))).thenReturn("string: " + testString + " (x2)");
        when(mockedFormatter.accept(new BooleanMessage(true))).thenReturn("true boolean message");

        //when
        logger.log(new StringMessage(testString, 1));
        logger.log(new StringMessage(testString, 1));
        logger.log(new BooleanMessage(true));

        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("string: " + testString + " (x2)");
        inOrder.verify(mockedPrinter, times(1)).println("true boolean message");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldPrintDifferentMessagesCorrectlyWhenPassBooleanAfterString() throws IOException {
        //given
        String testString = "testString";
        when(mockedFormatter.accept(new StringMessage(testString, 2))).thenReturn("string: " + testString + " (x2)");
        when(mockedFormatter.accept(new BooleanMessage(true))).thenReturn("true boolean message");

        //when
        logger.log(new StringMessage(testString, 1));
        logger.log(new StringMessage(testString, 1));
        logger.log(new BooleanMessage(true));

        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("string: " + testString + " (x2)");
        inOrder.verify(mockedPrinter, times(1)).println("true boolean message");
        inOrder.verifyNoMoreInteractions();
    }


    @After
    public void tearDown() {
        resetOut();
    }
}