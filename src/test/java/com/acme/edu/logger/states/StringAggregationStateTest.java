package com.acme.edu.logger.states;

import com.acme.edu.logger.messaging.messages.ByteMessage;
import com.acme.edu.logger.messaging.messages.CharMessage;
import com.acme.edu.logger.messaging.messages.IntMessage;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created by Java_9 on 04.09.2017.
 */
public class StringAggregationStateTest {

    public StringAggregationState stringAggregationState;

    @Before
    public void setUp() {
        stringAggregationState = new StringAggregationState("value", 1);
    }

    @Test
    public void shouldSwitchStateOnChar() {
        //when
        State result = stringAggregationState.accept(new CharMessage('a'));

        //then
        assertThat(result, instanceOf(NoAggregationState.class));
    }

    @Test
    public void shouldSwitchSatateOnByte() {
        //when
        State result = stringAggregationState.accept(new ByteMessage(Byte.MAX_VALUE));

        //then
        assertThat(result, instanceOf(ByteAggregationState.class));
    }

    @Test
    public void shouldSwitchSatateOnInt() {
        //when
        State result = stringAggregationState.accept(new IntMessage(42));

        //then
        assertThat(result, instanceOf(IntAggregationState.class));
    }


}