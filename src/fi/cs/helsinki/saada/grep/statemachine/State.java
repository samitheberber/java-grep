package fi.cs.helsinki.saada.grep.statemachine;

import fi.cs.helsinki.saada.grep.statemachine.state_transition.StateTransitionResult;

public class State implements StateTransitionResult {

    private String name;

    public State() {
        this("");
    }

    public State(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
