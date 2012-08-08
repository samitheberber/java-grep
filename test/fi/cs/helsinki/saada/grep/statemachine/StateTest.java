package fi.cs.helsinki.saada.grep.statemachine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StateTest {

    public static Test suite() {
        return new TestSuite(StateTest.class.getDeclaredClasses());
    }

    public static class Name extends TestCase {

        public void test__default_name_is_empty() {
            State state = new State();
            assertEquals("", state.toString());
        }

        public void test__name_is_what_it_should_be() {
            State state = new State("foobar");
            assertEquals("foobar", state.toString());
        }

    }

}
