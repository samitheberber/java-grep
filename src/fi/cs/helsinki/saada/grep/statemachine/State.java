package fi.cs.helsinki.saada.grep.statemachine;

public class State {

    private boolean accepting;

    public State() {
        this(false);
    }

    public State(boolean accepting) {
        this.setAcceptance(accepting);
    }

    public boolean isAccepting() {
        return this.accepting;
    }

    public void setAcceptance(boolean accepting) {
        this.accepting = accepting;
    }

}
