package fi.cs.helsinki.saada.grep.statemachine;

public abstract class AbstractDelta implements Delta {

    public AbstractDelta() {
    }

    public boolean add(State state, char character,  DeltaResult result) {
        return true;
    }

    public boolean contains(State state, char character) {
        return true;
    }

}
