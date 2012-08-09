package fi.cs.helsinki.saada.grep.statemachine.state_transition;

import fi.cs.helsinki.saada.grep.statemachine.State;

public interface StateTransition {

    public boolean add(State state, char character, StateTransitionResult result);
    public boolean contains(State state, char character);

}
