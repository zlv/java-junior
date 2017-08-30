package com.acme.edu.logger.savers;

import com.acme.edu.legacy.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Java_9 on 29.08.2017.
 */
public class ConsoleLoggerSaverTest implements SysoutCaptureAndAssertionAbility {
    private ConsoleLoggerSaver loggerSaver;

    @Before
    public void setUp() throws Exception {
        loggerSaver = new ConsoleLoggerSaver();
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }

    @Test
    public void shouldAddNewLineWhenCallPrintln() {
        loggerSaver.println("Some String");

        assertSysoutEquals("Some String" + System.lineSeparator());
    }

    @Test
    public void shouldContatinSameStringAsInSysoutWhenCallPrint() {
        loggerSaver.print("Some String");

        assertSysoutEquals("Some String");
    }

}