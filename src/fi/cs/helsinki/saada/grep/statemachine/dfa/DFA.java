package fi.cs.helsinki.saada.grep.statemachine.dfa;

import fi.cs.helsinki.saada.grep.statemachine.AbstractAutomaton;
import fi.cs.helsinki.saada.grep.statemachine.State;
import fi.cs.helsinki.saada.grep.statemachine.StateSet;
import fi.cs.helsinki.saada.grep.statemachine.Vocabulary;

public class DFA extends AbstractAutomaton {

    public DFA(StateSet states, Vocabulary vocabulary, DFAStateTransition delta, State start, StateSet goals) {
        super(states, vocabulary, delta, start, goals);
    }

}
