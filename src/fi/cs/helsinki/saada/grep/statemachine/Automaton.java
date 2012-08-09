package fi.cs.helsinki.saada.grep.statemachine;

public interface Automaton {

    public StateSet getStates();
    public State getStartingState();
    public StateSet getAcceptingStates();
    public StateTransition getStateTransition();
    public Vocabulary getVocabulary();

}
