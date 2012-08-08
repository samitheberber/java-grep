package fi.cs.helsinki.saada.grep.statemachine;

public interface Delta {

    public boolean add(State state, char character, DeltaResult result);
    public boolean contains(State state, char character);

}
