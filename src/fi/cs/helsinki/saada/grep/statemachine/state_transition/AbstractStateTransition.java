package fi.cs.helsinki.saada.grep.statemachine.state_transition;

import fi.cs.helsinki.saada.grep.statemachine.State;

public abstract class AbstractStateTransition implements StateTransition {

    private StateTransitionStructure structure;

    public AbstractStateTransition(StateTransitionStructure structure) {
        this.structure = structure;
    }

    @Override
    public boolean add(State state, char character, StateTransitionResult result) {
        return structure.add(state, character, result);
    }

    @Override
    public boolean contains(State state, char character) {
        return structure.contains(state, character);
    }

}
