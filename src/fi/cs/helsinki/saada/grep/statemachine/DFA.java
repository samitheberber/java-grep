package fi.cs.helsinki.saada.grep.statemachine;

public class DFA{

    private StateSet states;
    private Vocabulary vocabulary;
    private DeltaDFA delta;
    private State start;
    private StateSet goals;

    public DFA(StateSet states, Vocabulary vocabulary, DeltaDFA delta, State start, StateSet goals) {
        this.states = states;
        this.vocabulary = vocabulary;
        this.delta = delta;
        this.start = start;
        this.goals = goals;
    }

    public boolean run(String characters) throws Exception {
        return this.goals.includes(this.result(characters));
    }

    private State result(String characters) throws Exception {
        State current = this.start;
        for(char c : characters.toCharArray()) {
            this.validateInput(c);
            current = this.delta.calculate(current, c);
            this.ensure(current);
        }
        return current;
    }

    private void validateInput(char c) throws Exception {
        if (!this.vocabulary.includes(c))
            throw new Exception("Character not in vocabulary");
    }

    private void ensure(State state) throws Exception {
        if (!this.states.includes(state))
            throw new Exception("Undeclared state met");
    }

}
