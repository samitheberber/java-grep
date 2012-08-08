package fi.cs.helsinki.saada.grep.statemachine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.easymock.EasyMock.*;

public class AutomatonTest {

    public static Test suite() {
        return new TestSuite(AutomatonTest.class.getDeclaredClasses());
    }

    public static class ExampleAutomaton extends TestCase {

        private class ExampleDelta extends AbstractDelta {
        }

        private class PlainAutomaton extends AbstractAutomaton {

            public PlainAutomaton(
                    StateSet states,
                    Vocabulary vocabulary,
                    Delta delta,
                    State startingState,
                    StateSet acceptingStates
                    ) {
                super(states, vocabulary, delta, startingState, acceptingStates);
            }

        }

        private StateSet states;
        private StateSet acceptingStates;
        private State startingState;
        private Delta delta;
        private Vocabulary vocabulary;

        public void setUp() {
            this.startingState = createMock(State.class);
            this.states = createMock(StateSet.class);
            this.acceptingStates = createMock(StateSet.class);
            this.delta = createMock(ExampleDelta.class);
            this.vocabulary = createMock(Vocabulary.class);
        }

        public void test__can_be_constructed() throws Exception {
            Automaton automate = new PlainAutomaton(this.states, this.vocabulary, this.delta, this.startingState, this.acceptingStates);
        }

    }

}
