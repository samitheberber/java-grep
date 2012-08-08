package fi.cs.helsinki.saada.grep.statemachine;

public class State implements DeltaResult {

    private boolean accepting;
    private String name;

    public State() {
        this(false);
    }

    public State(boolean accepting) {
        this(accepting, "");
    }

    public State(String name) {
        this(false, name);
    }

    public State(boolean accepting, String name) {
        this.setAcceptance(accepting);
        this.name = name;
    }

    public boolean isAccepting() {
        return this.accepting;
    }

    public void setAcceptance(boolean accepting) {
        this.accepting = accepting;
    }

    public String getName() {
        return this.name;
    }

}
