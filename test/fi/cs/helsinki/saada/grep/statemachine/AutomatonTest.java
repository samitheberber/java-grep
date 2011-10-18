package fi.cs.helsinki.saada.grep.statemachine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.easymock.EasyMock.*;

//TODO: Move this away
class Vocabulary {
}

public class AutomatonTest {

    public static Test suite() {
        return new TestSuite(AutomatonTest.class.getDeclaredClasses());
    }

    public static class ExampleAutomaton extends TestCase {

        private class Delta {

            public StateSet calculate(State state, String characters) {
                return null;
            }
        }

        private class Automaton {

            private StateSet states;
            private Vocabulary vocabulary;
            private Delta delta;
            private StateSet acceptingStates;
            private StateSet currentStates;

            public Automaton(
                    StateSet states,
                    Vocabulary vocabulary,
                    Delta delta,
                    State startingState,
                    StateSet acceptingStates
                    ) {
                this.states = states;
                this.vocabulary = vocabulary;
                this.delta = delta;
                this.acceptingStates = acceptingStates;
                this.currentStates = this.delta.calculate(startingState, null);
            }

            public StateSet currentStates() {
                return this.currentStates;
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
            this.delta = createMock(Delta.class);
            this.vocabulary = createMock(Vocabulary.class);
        }

        public void test__starting_state_should_belongs_to_first_states() throws Exception {
            StateSet startingStates = new StateSet();
            startingStates.add(this.startingState);
            expect(this.delta.calculate(this.startingState, null)).andReturn(startingStates);
            replay(this.delta);
            Automaton automate = new Automaton(this.states, this.vocabulary, this.delta, this.startingState, this.acceptingStates);
            assertNotNull(automate.currentStates());
            assertTrue(automate.currentStates().includes(this.startingState));
            verify(this.delta);
        }

    }

    public static class DefineDFA extends TestCase {

        private class Automaton {

            public Automaton(StateSet states, Vocabulary vocabulary, Delta delta, State start, StateSet goals) {
            }

        }

        private class Delta {
        }

        public void test__we_can_create_dfa() {
            StateSet Q = createMock(StateSet.class);
            StateSet F = createMock(StateSet.class);
            Vocabulary sigma = createMock(Vocabulary.class);
            Delta delta = createMock(Delta.class);
            State q0 = createMock(State.class);
            State q1 = createMock(State.class);
            State q2 = createMock(State.class);
            State q3 = createMock(State.class);
            Automaton automate = new Automaton(Q, sigma, delta, q0, F);
            assertNotNull(automate);
        }
    }

}
