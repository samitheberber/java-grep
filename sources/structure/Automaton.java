package structure;

import structure.List;
import structure.implementation.ListItem;
import structure.State;

public class Automaton
{
    protected List states;
    protected State first;

    public Automaton(State first)
    {
        this.first = first;
        this.states = new List();
    }

    public void rewind()
    {
        this.states = new List();
    }

    public boolean accepts()
    {
        State current;
        if ( this.states.isEmpty() )
            return false;
        while (this.states.hasNext())
            if ( ( current = (State) this.states.next() ).accepting() ) {
                if ( !current.getName().equals("START") )
                    return true;
            }
        return false;
    }

    public boolean input(char c)
        throws Exception
    {
        List currentStates = new List();
        if ( this.states.isEmpty() ) {
            this.addStates( this.first.walk(c) );
        } else {
            while (this.states.hasNext()) {
                currentStates.insert( ((State) this.states.next()).walk(c) );
            }
            this.states.rewind();

            this.states = new List();
            while (currentStates.hasNext()) {
                this.addStates( (List) currentStates.next() );
            }
        }
        return this.states.isEmpty();
    }

    protected void addStates(List currentList)
    {
        State current = null;
        while (currentList.hasNext()) {
            current = (State) currentList.next();
            if ( current != null )
                this.states.insert( current );
        }
    }
}
