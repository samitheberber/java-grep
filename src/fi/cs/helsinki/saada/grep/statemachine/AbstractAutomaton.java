package fi.cs.helsinki.saada.grep.statemachine;

public abstract class AbstractAutomaton implements Automaton {

    private StateSet states;
    private Vocabulary vocabulary;
    private Delta delta;
    private StateSet acceptingStates;
    private StateSet currentStates;

    public AbstractAutomaton(StateSet states, Vocabulary vocabulary, Delta delta, State start, StateSet acceptingStates) {
        this.states = states;
        this.vocabulary = vocabulary;
        this.delta = delta;
        this.currentStates = new StateSet();
        this.currentStates.add(start);
        this.acceptingStates = acceptingStates;
    }

    public StateSet getCurrentStates() throws Exception {
        return this.currentStates;
    }

    public State getCurrentState() throws Exception {
        throw new Exception("use getCurrentStates()");
    }

    protected void setStates(StateSet states) {
        this.states = states;
    }

    protected void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    protected DeltaResult delta(State state, char letter) {
        return this.delta.calculate(state, letter);
    }

    protected StateSet getAcceptingStates() {
        return this.acceptingStates;
    }

    protected void setCurrentStates(StateSet currentStates) {
        this.currentStates = currentStates;
    }

}
