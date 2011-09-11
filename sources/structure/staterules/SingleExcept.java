package structure.staterules;

import structure.implementation.StateRule;
import structure.staterules.StateRuleAbstract;

public class SingleExcept extends StateRuleAbstract
{
    protected char searched;

    public SingleExcept(char c)
    {
        this.searched = c;
    }

    public boolean match(char c)
    {
        return (this.searched != c);
    }

    public boolean equals(Object rule)
    {
        return (super.equals(rule)) ? !((StateRule) rule).match(this.searched) : false;
    }

    public String toString()
    {
        return "Any except " + this.searched;
    }
}
