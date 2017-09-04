package com.acme.edu.logger;

import com.acme.edu.logger.messaging.IntMessage;
import com.acme.edu.logger.messaging.LoggerMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.util.function.Consumer;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Java_9 on 04.09.2017.
 */
public class LoggerContextTest {

    private LoggerContext loggerContext;
    @Mock
    private Consumer<LoggerMessage> consumer1;
    @Mock
    private Consumer<LoggerMessage> consumer2;

    @Before
    public void setUp() {
        initMocks(this);
        loggerContext = new LoggerContext(consumer1, consumer2);
    }

    @Test
    public void shouldPassMessagesInOrderFromConstructor() {
        //when
        LoggerMessage message = new IntMessage(123);
        loggerContext.log(message);

        //then
        InOrder inOrder = inOrder(consumer1, consumer2);
        inOrder.verify(consumer1, times(1)).accept(message);
        inOrder.verify(consumer2, times(1)).accept(message);
    }

}