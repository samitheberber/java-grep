package fi.cs.helsinki.saada.grep.statemachine.state_transition;

import fi.cs.helsinki.saada.grep.statemachine.State;

/**
 *
 * @author stb
 */
public class StateTransitionStructure {

    public StateTransitionStructure() {
    }

    public boolean add(State from, char character, StateTransitionResult to) {
        return true;
    }

    public boolean contains(State from, char character) {
        return true;
    }

}
