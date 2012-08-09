package fi.cs.helsinki.saada.grep.statemachine;

public interface StateTransition {

    public boolean add(State state, char character, StateTransitionResult result);
    public boolean contains(State state, char character);

}
