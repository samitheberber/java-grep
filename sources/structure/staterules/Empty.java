package structure.staterules;

import structure.staterules.StateRuleAbstract;

public class Empty extends StateRuleAbstract
{
    public Empty() {}

    public boolean match(char c)
    {
        return (c == 0);
    }

    public String toString()
    {
        return "Not empty character";
    }
}
