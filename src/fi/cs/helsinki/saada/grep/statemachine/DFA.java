package fi.cs.helsinki.saada.grep.statemachine;

public class DFA extends AbstractAutomaton {

    public DFA(StateSet states, Vocabulary vocabulary, DFAStateTransition delta, State start, StateSet goals) {
        super(states, vocabulary, delta, start, goals);
    }

}
