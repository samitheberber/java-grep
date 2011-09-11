package structure;

import structure.List;
import structure.implementation.ListItem;
import structure.implementation.StateRule;

public class State implements ListItem, Cloneable
{
    protected class Route implements ListItem
    {
        protected StateRule cond;
        protected State next;

        public Route(StateRule cond, State state)
        {
            this.cond = cond;
            this.next = state;
        }

        public StateRule getCond()
        {
            return this.cond;
        }

        public State getState()
        {
            return this.next;
        }

        public String toString()
        {
            return this.cond.toString() + " -> " + ((this.next == null)? "null" : this.next.getName());
        }

        public boolean equals(Object comparedRoute)
        {
            return (this.cond.equals(((Route) comparedRoute).getCond()));
        }
    }
    protected boolean accepts;
    protected List routes;
    protected int id;
    protected String name;

    public State()
    {
        this(null, false);
    }

    public State(String name)
    {
        this(name, false);
    }

    public State(boolean accepts)
    {
        this(null, accepts);
    }

    public State(String name, boolean accepts)
    {
        this.accepts = accepts;
        this.routes = new List();
        this.id = statistic++;
        this.name = name;
    }

    public void removeRoute(StateRule rule)
    {
        this.routes.delete(new Route(rule, null));
    }

    public void accepts(boolean value)
    {
        this.accepts = value;
    }

    public boolean accepting()
    {
        return this.accepts;
    }

    public void addRoute(StateRule rule, State nextState)
    {
        this.routes.insert(new Route(rule, nextState));
    }

    public List walk(char c)
        throws Exception
    {
        List nextStates = new List();
        if (this.routes == null)
            return null;
        Route currentRoute = null;
        while (this.routes.hasNext()) {
            currentRoute = (Route) this.routes.next();
            if (currentRoute.getCond().match(c)) {
                nextStates.insert(currentRoute.getState());
            }
        }
        this.routes.rewind();
        return nextStates;
    }

    public String getName()
    {
        return (this.name == null) ? "q" + this.id : this.name;
    }

    public String toString()
    {
        String text = new String();
        text += this.getName() + ". Accepting: " + (String)((this.accepts) ? "true" : "false");
        if (this.routes != null) {
            text += ". " + this.routes.toString();
        }
        return text;
    }

    private static int statistic = 0;
}
