package fi.cs.helsinki.saada.grep.statemachine;

public class State implements DeltaResult {

    private String name;

    public State() {
        this("");
    }

    public State(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
