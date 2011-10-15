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

    public void delete(State state) throws Exception {
        if (this.count() == 0)
            throw new Exception("no more states");
        this.count--;
    }

}
