package fi.cs.helsinki.saada.grep.statemachine;

public abstract class AbstractAutomaton implements Automaton {

    private StateSet states;
    private Vocabulary vocabulary;
    private StateTransition stateTransition;
    private State startingState;
    private StateSet acceptingStates;

    public AbstractAutomaton(StateSet states, Vocabulary vocabulary, StateTransition delta, State start, StateSet acceptingStates) {
        this.states = states;
        this.vocabulary = vocabulary;
        this.stateTransition = delta;
        this.startingState = start;
        this.acceptingStates = acceptingStates;
    }

    @Override
    public StateSet getStates() {
        return this.states;
    }

    @Override
    public Vocabulary getVocabulary() {
        return this.vocabulary;
    }

    @Override
    public StateTransition getStateTransition() {
        return this.stateTransition;
    }

    @Override
    public State getStartingState() {
        return this.startingState;
    }

    @Override
    public StateSet getAcceptingStates() {
        return this.acceptingStates;
    }

}
