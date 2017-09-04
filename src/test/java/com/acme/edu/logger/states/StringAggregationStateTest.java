package com.acme.edu.logger.states;

import com.acme.edu.logger.messaging.ByteMessage;
import com.acme.edu.logger.messaging.CharMessage;
import com.acme.edu.logger.messaging.IntMessage;
import com.acme.edu.logger.messaging.StringMessage;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

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
    public void shouldSwitchStateOnPrimitives() {
        //when
        State result = stringAggregationState.visit(new CharMessage('a'));

        //then
        assertThat(result, instanceOf(NoAggregationState.class));
    }

    @Test
    public void shouldSwitchStateOnByte() {
        //when
        State result = stringAggregationState.visit(new ByteMessage(Byte.MAX_VALUE));

        //then
        assertThat(result, instanceOf(ByteAggregationState.class));
    }

    @Test
    public void shouldSwitchSatateOnInt() {
        //when
        State result = stringAggregationState.visit(new IntMessage(42));

        //then
        assertThat(result, instanceOf(IntAggregationState.class));
    }

    @Test
    public void shouldSwitchStateOnDifferentString() {
        //when
        State result = stringAggregationState.visit(new StringMessage("another string", 1));

        //then
        assertThat(result, instanceOf(StringAggregationState.class));
        assertTrue(stringAggregationState.isDifferentState(result));
    }

    @Test
    public void shouldNotSwitchStateOnSameString() {
        //when
        State result = stringAggregationState.visit(new StringMessage("value", 1));

        //then
        assertThat(result, instanceOf(StringAggregationState.class));
        assertFalse(stringAggregationState.isDifferentState(result));
    }

}