package structure.implementation;

public interface StateRule
{
    public boolean match(char c);
    public boolean equals(Object rule);
    public String toString();
}
