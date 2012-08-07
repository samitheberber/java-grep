package fi.cs.helsinki.saada.grep.statemachine;

public abstract class AbstractAutomaton implements Automaton {

    private StateSet states;
    private Vocabulary vocabulary;
    private Delta delta;
    private StateSet acceptingStates;
    private StateSet currentStates;

    public StateSet currentStates() {
        return this.currentStates;
    }

    public State currentState() throws Exception {
        throw new Exception("use currentStates()");
    }

    protected void setStates(StateSet states) {
        this.states = states;
    }

    protected void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    protected void setDelta(Delta delta) {
        this.delta = delta;
    }

    protected void setAcceptingStates(StateSet acceptingStates) {
        this.acceptingStates = acceptingStates;
    }

    protected void setCurrentStates(StateSet currentStates) {
        this.currentStates = currentStates;
    }

}
