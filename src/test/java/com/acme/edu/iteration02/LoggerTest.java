package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static java.lang.System.lineSeparator;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion


  // State
    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        Logger.log("str 1");
        Logger.log(1);
        Logger.log(2);
        Logger.log("str 2");
        Logger.log(0);
        Logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1\n" +
            "primitive: 3\n" +
            "string: str 2\n" +
            "primitive: 0\n"
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        Logger.log("str 1");
        Logger.log(10);
        Logger.log(Integer.MAX_VALUE);
        Logger.log("str 2");
        Logger.log(0);
        Logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1\n" +
            "primitive: 10\n" +
            "primitive: " + Integer.MAX_VALUE + "\n" +
            "string: str 2\n" +
            "primitive: 0\n"
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        Logger.log("str 1");
        Logger.log((byte)10);
        Logger.log((byte)Byte.MAX_VALUE);
        Logger.log("str 2");
        Logger.log(0);
        Logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1\n" +
            "primitive: 10\n" +
            "primitive: " + Byte.MAX_VALUE + "\n" +
            "string: str 2\n" +
            "primitive: 0\n"
        );
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        Logger.log("str 1");
        Logger.log("str 2");
        Logger.log("str 2");
        Logger.log(0);
        Logger.log("str 2");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1" + lineSeparator() +
            "string: str 2 (x2)" + lineSeparator() +
            "primitive: 0" + lineSeparator() +
            "string: str 2" + lineSeparator() +
            "string: str 3 (x3)" + lineSeparator()
        );
        //endregion
    }

}