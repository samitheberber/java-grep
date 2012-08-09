package fi.cs.helsinki.saada.grep.statemachine;

public abstract class AbstractStateTransition implements StateTransition {

    public AbstractStateTransition() {
    }

    @Override
    public boolean add(State state, char character,  StateTransitionResult result) {
        return true;
    }

    @Override
    public boolean contains(State state, char character) {
        return true;
    }

}
