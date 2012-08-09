package fi.cs.helsinki.saada.grep.statemachine;

import fi.cs.helsinki.saada.grep.statemachine.state_transition.StateTransition;

public interface Automaton {

    public StateSet getStates();
    public State getStartingState();
    public StateSet getAcceptingStates();
    public StateTransition getStateTransition();
    public Vocabulary getVocabulary();

}
