package fi.cs.helsinki.saada.grep.statemachine;

public class StateSet {

    private int count;

    public StateSet() {
        this.count = 0;
    }

    public int count() {
        return this.count;
    }

    public void add(State state) {
        this.count++;
    }

}
