package fi.cs.helsinki.saada.grep.statemachine;

public interface Delta {

    public StateSet calculate(State state, char character);

}
