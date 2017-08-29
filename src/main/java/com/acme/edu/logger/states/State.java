package com.acme.edu.logger.states;

import com.acme.edu.logger.messaging.MessageVisitor;
import com.acme.edu.logger.messaging.messages.*;


/**
 * Created by Java_9 on 28.08.2017.
 */
public abstract class State implements MessageVisitor {

    protected String[] builtOutput;

    public State(String[] builtOutput) {
        this.builtOutput = builtOutput;
    }

    public String[] getBuildOutput() {
        return builtOutput;
    }
    public abstract String[] getCurrentOutput();
}
