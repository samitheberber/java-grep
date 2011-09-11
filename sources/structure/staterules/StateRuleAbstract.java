package structure.staterules;

import structure.implementation.StateRule;

public abstract class StateRuleAbstract implements StateRule
{
    public boolean equals(Object rule)
    {
        return ( this.getClass().getName().equals( rule.getClass().getName() ) );
    }
}
