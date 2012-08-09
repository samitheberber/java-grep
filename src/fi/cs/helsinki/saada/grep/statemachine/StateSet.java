package fi.cs.helsinki.saada.grep.statemachine;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class StateSet implements StateTransitionResult, Set<State> {

    private int count;
    private State[] states;

    public StateSet() {
        clear();
    }

    @Override
    public void clear() {
        this.count = 0;
        this.states = new State[1];
    }

    @Override
    public boolean removeAll(Collection states) {
        boolean changed_state = false;
        for (Object state : states) {
            changed_state = this.remove(state) || changed_state;
        }
        return changed_state;
    }

    @Override
    public boolean retainAll(Collection states) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends State> states) {
        boolean changed_state = false;
        for (State state : states) {
            changed_state = this.add(state) || changed_state;
        }
        return changed_state;
    }

    @Override
    public boolean containsAll(Collection states) {
        for (Object state : states) {
            if (!this.contains(state))
                return false;
        }
        return true;
    }

    @Override
    public boolean remove(Object state) {
        int removed_index = this.index(state);
        if (removed_index == -1)
            return false;
        for(int i=removed_index; i < this.size(); i++) {
            this.states[i] = this.states[i+1];
        }
        this.count--;
        this.checkStates();
        return true;
    }

    @Override
    public boolean add(State state) {
        if (this.contains(state)) {
            return false;
        } else {
            this.insert(state);
            return true;
        }
    }

    @Override
    public <T> T[] toArray(T[] type_array) {
        T[] states_copy = (T[]) new Object[this.size()];
        for(int i=0; i < this.size(); i++) {
            states_copy[i] = (T) this.states[i];
        }
        return states_copy;
    }

    @Override
    public Object[] toArray() {
        return toArray(new Object[0]);
    }

    @Override
    public Iterator<State> iterator() {
        return new Iterator<State>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size() && states[currentIndex] != null;
            }

            @Override
            public State next() {
                return states[currentIndex++];
            }

            @Override
            public void remove() {
                // TODO Auto-generated method stub
            }

        };
    }

    @Override
    public boolean contains(Object state) {
        return (this.index(state) != -1);
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public int size() {
        return this.count;
    }

    private void insert(State state) {
        this.states[this.size()] = state;
        this.count++;
        this.checkStates();
    }

    private int index(Object state) {
        for(int i=0; i < this.size(); i++) {
            if (this.states[i] == state)
                return i;
        }
        return -1;
    }

    private void checkStates() {
        if (this.size() == this.states.length)
            this.alterStates(this.states.length*2);
        else if (this.size() < this.states.length/2-1)
            this.alterStates(this.states.length/2);
    }

    private void alterStates(int size) {
        State[] newStates = new State[size];
        System.arraycopy(this.states, 0, newStates, 0, this.size());
        this.states = newStates;
    }

}