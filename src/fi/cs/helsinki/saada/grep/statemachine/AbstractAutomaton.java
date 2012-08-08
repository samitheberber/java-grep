package fi.cs.helsinki.saada.grep.statemachine;

public abstract class AbstractAutomaton implements Automaton {

    private StateSet states;
    private Vocabulary vocabulary;
    private Delta delta;
    private State startingState;
    private StateSet acceptingStates;

    public AbstractAutomaton(StateSet states, Vocabulary vocabulary, Delta delta, State start, StateSet acceptingStates) {
        this.states = states;
        this.vocabulary = vocabulary;
        this.delta = delta;
        this.startingState = start;
        this.acceptingStates = acceptingStates;
    }

    public StateSet getStates() {
        return this.states;
    }

    public Vocabulary getVocabulary() {
        return this.vocabulary;
    }

    public Delta getDelta() {
        return this.delta;
    }

    public State getStartingState() {
        return this.startingState;
    }

    public StateSet getAcceptingStates() {
        return this.acceptingStates;
    }

}
