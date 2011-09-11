package structure.staterules;

import structure.staterules.StateRuleAbstract;
import structure.AVLTree;

public class Many extends StateRuleAbstract
{
    protected AVLTree chars;

    public Many(AVLTree chars)
    {
        this.chars = chars;
    }

    public boolean match(char c)
    {
        return this.chars.searchExists((int)c);
    }

    public boolean equals(Object rule)
    {
        return super.equals(rule); //FIXME: add chars match
    }
}
