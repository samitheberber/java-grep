package structure.staterules;

import structure.staterules.StateRuleAbstract;

public class Any extends StateRuleAbstract
{
    public Any() {}

    public boolean match(char c)
    {
        return true;
    }

    public String toString()
    {
        return "Any character";
    }
}
