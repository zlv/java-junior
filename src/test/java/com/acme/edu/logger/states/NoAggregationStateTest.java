package com.acme.edu.logger.states;

import com.acme.edu.logger.messaging.messages.CharMessage;
import com.acme.edu.logger.messaging.messages.ObjectMessage;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

/**
 * Created by Java_9 on 04.09.2017.
 */
public class NoAggregationStateTest {

    private NoAggregationState noAggregationState;

    @Before
    public void setUp() {
        noAggregationState = new NoAggregationState(new ObjectMessage(42));
    }

    @Test
    public void shouldSwitchStateOnChar() {
        //when
        State result = noAggregationState.accept(new CharMessage('a'));

        //then
        assertThat(result, instanceOf(NoAggregationState.class));
    }
}