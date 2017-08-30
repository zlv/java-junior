package com.acme.edu.logger;

import com.acme.edu.legacy.SysoutCaptureAndAssertionAbility;
import com.acme.edu.logger.FlexibleLogger;
import com.acme.edu.logger.formatters.LoggerFormatter;
import com.acme.edu.logger.savers.LoggerPrinter;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * Created by Java_9 on 29.08.2017.
 */

//TODO rename to flexible logger test
public class FlexibleLoggerTest implements SysoutCaptureAndAssertionAbility {

    private FlexibleLogger logger;
    @Mock
    private LoggerPrinter mockedPrinter;
    @Mock
    private LoggerFormatter mockedFormatter;


    @Before
    public void setUp() {
        initMocks(this);
        captureSysout();
        resetOut();
        logger = new FlexibleLogger(mockedPrinter, mockedFormatter);
    }

    @Test
    public void shouldPrintBoolean() {
        //given
        when(mockedFormatter.format(false)).thenReturn("my format with false value");

        //when
        logger.log(false);

        //then
        verify(mockedPrinter, times(1)).println("my format with false value");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintIntSum() {
        //given
        when(mockedFormatter.format(15)).thenReturn("my format with 15 value");

        //when
        logger.log(8);
        logger.log(7);
        logger.flush();

        //then
        verify(mockedPrinter, times(1)).println("my format with 15 value");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintByteSum() {
        //given
        when(mockedFormatter.format(30)).thenReturn("my format with 30 value");

        //when
        logger.log((byte)23);
        logger.log((byte)7);
        logger.flush();

        //then
        verify(mockedPrinter, times(1)).println("my format with 30 value");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintByteSumOverflow() {
        //given
        when(mockedFormatter.format(Byte.MAX_VALUE)).thenReturn("my format with max value");
        when(mockedFormatter.format(27)).thenReturn("my format with 27 value");

        //when
        logger.log((byte) (Byte.MAX_VALUE / 2 + 13));
        logger.log((byte) (Byte.MAX_VALUE / 2 + 15));
        logger.flush();

        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("my format with 27 value");
        inOrder.verify(mockedPrinter, times(1)).println("my format with max value");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldPrintIntSumOverflow() {
        //given
        when(mockedFormatter.format(Integer.MIN_VALUE)).thenReturn("my format with min value");
        when(mockedFormatter.format(-32)).thenReturn("my format with -32 value");

        //when
        logger.log(Integer.MIN_VALUE / 2 - 17);
        logger.log(Integer.MIN_VALUE / 2 - 15);
        logger.flush();

        //then
        InOrder inOrder = inOrder(mockedPrinter);
        inOrder.verify(mockedPrinter, times(1)).println("my format with -32 value");
        inOrder.verify(mockedPrinter, times(1)).println("my format with min value");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldAggregateStringValues() {
        //given
        String myString = "myString";
        when(mockedFormatter.format(myString + " (x3)")).thenReturn("string: " + myString + " (x3)");

        //when
        logger.log(myString);
        logger.log(myString);
        logger.log(myString);
        logger.flush();

        //then
        verify(mockedPrinter, times(1)).println("string: " + myString + " (x3)");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldLogArray() {
        //given
        when(mockedFormatter.format(new int[] {5, 3, -1})).thenReturn("test arr with values: 5, 3, -1");

        //when
        logger.log(new int[] {5, 3, -1});

        //then
        verify(mockedPrinter, times(1)).println("test arr with values: 5, 3, -1");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldLogObjectsUsingToString() {
        //given
        Object mockedObject = new Object();
        when(mockedFormatter.format(mockedObject)).thenReturn("test new test object");

        //when
        logger.log(mockedObject);

        //then
        verify(mockedPrinter, times(1)).println("test new test object");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @Test
    public void shouldPrintSingleStringWithoutCount() {
        //given
        String inputString = "test string";
        when(mockedFormatter.format(inputString)).thenReturn("formatted test string");

        //when
        logger.log(inputString);
        logger.flush();

        //then
        verify(mockedPrinter, times(1)).println("formatted test string");
        verifyNoMoreInteractions(mockedPrinter);
    }

    @After
    public void tearDown() {
        resetOut();
    }
}