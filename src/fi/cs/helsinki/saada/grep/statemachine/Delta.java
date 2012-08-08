package fi.cs.helsinki.saada.grep.statemachine;

public interface Delta {

    public DeltaResult calculate(State state, char character);

}
