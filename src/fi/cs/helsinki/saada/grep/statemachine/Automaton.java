package fi.cs.helsinki.saada.grep.statemachine;

public interface Automaton {

    public StateSet currentStates() throws Exception;
    public State currentState() throws Exception;

}
