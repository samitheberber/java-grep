package fi.cs.helsinki.saada.grep.statemachine;

public interface Automaton {

    public StateSet getCurrentStates() throws Exception;
    public State getCurrentState() throws Exception;

}
