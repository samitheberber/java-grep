package fi.cs.helsinki.saada.grep.statemachine;

public class DFA extends AbstractAutomaton {

    public DFA(StateSet states, Vocabulary vocabulary, DeltaDFA delta, State start, StateSet goals) {
        super(states, vocabulary, delta, start, goals);
    }

    public boolean run(String characters) throws Exception {
        return this.getAcceptingStates().contains(this.result(characters));
    }

    private State result(String characters) throws Exception {
        State current = this.getCurrentState();
        for(char c : characters.toCharArray()) {
            current = (State) this.delta(current, c);
        }
        return current;
    }

    public State getCurrentState() throws Exception {
        return (State) super.getCurrentStates().toArray()[0];
    }

    public StateSet getCurrentStates() throws Exception {
        throw new Exception("use getCurrentState()");
    }

}
