package com.acme.edu.logger.states;

import com.acme.edu.logger.message.ByteMessage;
import com.acme.edu.logger.message.IntMessage;
import com.acme.edu.logger.message.SimpleMessage;
import com.acme.edu.logger.message.StringMessage;

/**
 * Created by Java_9 on 28.08.2017.
 */
public abstract class State {

    protected String[] builtOutput;

    public State(String[] builtOutput) {
        this.builtOutput = builtOutput;
    }

    public String[] getBuildOutput() {
        return builtOutput;
    }
    public abstract String[] getCurrentOutput();
    public abstract State acceptMessage(IntMessage message);
    public abstract State acceptMessage(ByteMessage message);
    public abstract State acceptMessage(StringMessage message);
    public abstract State acceptMessage(SimpleMessage message);
}
