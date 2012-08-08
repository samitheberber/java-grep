package fi.cs.helsinki.saada.grep.statemachine;

public class StateSet implements DeltaResult {

    private int count;
    private State[] states;

    public StateSet() {
        this.count = 0;
        this.states = new State[1];
    }

    public int count() {
        return this.count;
    }

    public void add(State state) throws Exception {
        if (this.includes(state))
            throw new Exception("state already found");
        this.insert(state);
    }

    public boolean includes(State state) {
        return (this.index(state) != -1);
    }

    public void delete(State state) throws Exception {
        if (!this.includes(state))
            throw new Exception("state not found");
        this.remove(state);
    }

    private void insert(State state) {
        this.states[this.count()] = state;
        this.count++;
        this.checkStates();
    }

    private void remove(State state) {
        int removed_index = this.index(state);
        for(int i=removed_index; i < this.count(); i++) {
            this.states[i] = this.states[i+1];
        }
        this.count--;
        this.checkStates();
    }

    private int index(State state) {
        for(int i=0; i < this.count(); i++) {
            if (this.states[i] == state)
                return i;
        }
        return -1;
    }

    private void checkStates() {
        if (this.count() == this.states.length)
            this.alterStates(this.states.length*2);
        else if (this.count() < this.states.length/2-1)
            this.alterStates(this.states.length/2);
    }

    private void alterStates(int size) {
        State[] newStates = new State[size];
        for(int i=0; i < this.count(); i++) {
            newStates[i] = this.states[i];
        }
        this.states = newStates;
    }

}
