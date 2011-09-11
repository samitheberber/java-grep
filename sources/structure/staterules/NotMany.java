package structure.staterules;

import structure.staterules.StateRuleAbstract;
import structure.AVLTree;

public class NotMany extends StateRuleAbstract
{
    protected AVLTree chars;

    public NotMany(AVLTree chars)
    {
        this.chars = chars;
    }

    public boolean match(char c)
    {
        return !this.chars.searchExists((int)c);
    }

    public boolean equals(Object rule)
    {
        return super.equals(rule); //FIXME: add chars match
    }
}
