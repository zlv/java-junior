package com.acme.edu.logger;

import com.acme.edu.logger.formatters.DefaultLoggerFormatter;
import com.acme.edu.logger.savers.ConsoleLoggerSaver;
import org.junit.Before;

public class FlexibleLoggerTest {
    private FlexibleLogger logger;

    @Before
    public void setUp() throws Exception {
        logger = new FlexibleLogger(new ConsoleLoggerSaver(), new DefaultLoggerFormatter());
    }

   /* @Test
    public void */
}