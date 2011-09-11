package structure;

import structure.Automaton;
import structure.State;
import structure.AVLTree;
import structure.implementation.StateRule;
import structure.staterules.*;

public final class RegularExpression
{

    protected Automaton automaton;

    protected RegularExpression(State first)
    {
        this.automaton = new Automaton(first);
    }

    public boolean check(String word)
        throws Exception
    {
        this.automaton.rewind();
        char[] characters = word.toCharArray();
        for (char c : characters) {
            if (this.automaton.input(c))
                return false;
        }
        return automaton.accepts();
    }

    /* Static section */

    private static RegularExpression regExpObject = null;
    private static StateRule prevRule = null;
    private static StateRule prevNotRule = null;
    private static State orgPrevState = null;

    public static State parseStatement(String word)
        throws Exception
    {
        State state_q0 = new State("START");
        State prevState = state_q0;
        State currentState = null;
        char c;
        AVLTree storedChars = null;
        boolean bracketNot = false, iQ = false;
        Exception notRE = new Exception("Invalid regular expression.");
        /* Splits word in character-array */
        char[] characters = word.toCharArray();
        int length = characters.length;
        //prevState = stateBraces(prevState, null, characters);
        for (int i=0; i<length; i++) {

            c = characters[i];

            /* Special case for the first character */
            if (i == 0 && c == '^') {
                continue;
            } else if (i == 0 && c != '^') {
                prevState.addRoute(new Any(), prevState);
                if (!prevState.accepting())
                    prevState.accepts(true);
            }

            /* Special case for the last character */
            if (i == length-1 && c == '$') {
                if (!prevState.accepting())
                    prevState.accepts(true);
                prevState.addRoute(new Any(), null);
                return state_q0; // Prevent any wrong causes
            }

            if (c == '*') {
                if (iQ || orgPrevState == null)
                    throw notRE;
                prevState = stateStar();
            } else if (c == '+') {
                if (iQ || orgPrevState == null)
                    throw notRE;
                prevState = statePlus(prevState);
            } else if (c == '.') {
                prevState = stateDot(prevState, iQ);
                iQ = false;
            } else if (c == '?') {
                if (iQ)
                    throw notRE;
                iQ = true;
                continue;
            } else if (c == '[') {
                i++;
                if (characters[i] == '^') {
                    bracketNot = true;
                    i++;
                } else
                    bracketNot = false;
                storedChars = new AVLTree();
                while (i<length && characters[i] != ']') {
                    storedChars.add((int)characters[i]);
                    i++;
                }
                prevState = stateBracket(prevState, storedChars, bracketNot, iQ);
                iQ = false;
            } else {
                prevState = stateChar(prevState, c, iQ);
                iQ = false;
            }

        }
        currentState = new State("Accept any", true);
        prevState.addRoute(new Any(), currentState);
        currentState.addRoute(new Any(), currentState);
        if (!prevState.accepting())
            prevState.accepts(true);
        return state_q0;
    }

    private static State stateDot(State prevState, boolean iQ)
    {
        State currentState = new State();
        prevRule = new Any();
        prevNotRule = new Empty();
        prevState.addRoute(prevRule, currentState);
        if (iQ)
            orgPrevState.addRoute(prevRule, currentState);
        orgPrevState = prevState;
        return currentState;
    }

    private static State stateChar(State prevState, char c, boolean iQ)
    {
        State currentState = new State();
        prevRule = new Single(c);
        prevNotRule = new SingleExcept(c);
        prevState.addRoute(prevRule, currentState);
        prevState.addRoute(prevNotRule, null);
        if (iQ)
            orgPrevState.addRoute(prevRule, currentState);
        orgPrevState = prevState;
        return currentState;
    }

    private static State stateStar()
    {
        State prevState = orgPrevState;
        prevState.removeRoute(prevRule);
        prevState.removeRoute(prevNotRule);
        prevState.addRoute(prevRule, prevState);
        prevRule = null;
        prevNotRule = null;
        orgPrevState = null;
        return prevState;
    }

    private static State stateBracket(State prevState, AVLTree chars, boolean not, boolean iQ)
    {
        State currentState = new State();
        if (not) {
            prevRule = new NotMany(chars);
            prevNotRule = new Many(chars);
        } else {
            prevRule = new Many(chars);
            prevNotRule = new NotMany(chars);
        }
        prevState.addRoute(prevRule, currentState);
        prevState.addRoute(prevNotRule, null);
        if (iQ)
            orgPrevState.addRoute(prevRule, currentState);
        orgPrevState = prevState;
        return currentState;
    }

    private static State statePlus(State prevState)
    {
        State currentState = new State();
        prevState.addRoute(prevRule, currentState);
        prevState.addRoute(prevNotRule, null);
        orgPrevState = prevState;
        prevState = currentState;
        return stateStar();
    }

    public static void setExpression(String expression)
        throws Exception
    {
        regExpObject = new RegularExpression( parseStatement(expression) );
    }

    public static boolean checking(String text)
        throws Exception
    {
        if (regExpObject == null)
            throw new Exception("Regular expression isn't set.");

        return regExpObject.check(text);
    }

    private static char[] addToCharArray(char[] chars, char c)
    {
        for (int i=0; i<chars.length; i++)
            if (chars[i] == 0) {
                chars[i] = c;
                return chars;
            }
        int newLength = 2*chars.length;
        char[] newChars = new char[newLength];
        for (int i=0; i<chars.length; i++)
            newChars[i] = chars[i];
        newChars[chars.length] = c;
        return newChars;
    }
}
