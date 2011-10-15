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
            assertEquals("", state.getName());
        }

        public void test__name_is_what_it_should_be() {
            State state = new State("foobar");
            assertEquals("foobar", state.getName());
        }

        public void test__default_works_with_accepting() {
            State state = new State(true);
            assertEquals("", state.getName());
        }

        public void test__custom_works_with_accepting() {
            State state = new State(true, "foobar");
            assertEquals("foobar", state.getName());
        }

    }

    public static class Acceptance extends TestCase {

        public void test__state_is_not_accepting_by_default() {
            State state = new State();
            assertFalse(state.isAccepting());
        }

        public void test__state_is_accepting() {
            State state = new State(true);
            assertTrue(state.isAccepting());
        }

        public void test__state_can_be_set_to_accept() {
            State state = new State();
            assertFalse(state.isAccepting());
            state.setAcceptance(true);
            assertTrue(state.isAccepting());
        }

        public void test__state_can_be_set_to_not_accept() {
            State state = new State(true);
            assertTrue(state.isAccepting());
            state.setAcceptance(false);
            assertFalse(state.isAccepting());
        }

    }

}
