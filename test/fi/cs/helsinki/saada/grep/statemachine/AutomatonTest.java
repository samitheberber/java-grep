package fi.cs.helsinki.saada.grep.statemachine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.easymock.EasyMock.*;

public class AutomatonTest {

    public static Test suite() {
        return new TestSuite(AutomatonTest.class.getDeclaredClasses());
    }

    public static class Initializing extends TestCase {

        private class Delta {

            public StateSet calculate(State state, String characters) {
                return null;
            }
        }

        private class Language {
        }

        private class Automaton {

            private StateSet states;
            private Language language;
            private Delta delta;
            private StateSet acceptingStates;
            private StateSet currentStates;

            public Automaton(
                    StateSet states,
                    Language language,
                    Delta delta,
                    State startingState,
                    StateSet acceptingStates
                    ) {
                this.states = states;
                this.language = language;
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
        private Language language;

        public void setUp() {
            this.startingState = createMock(State.class);
            this.states = createMock(StateSet.class);
            this.acceptingStates = createMock(StateSet.class);
            this.delta = createMock(Delta.class);
            this.language = createMock(Language.class);
        }

        public void test__starting_state_should_belongs_to_first_states() throws Exception {
            StateSet startingStates = new StateSet();
            startingStates.add(this.startingState);
            expect(this.delta.calculate(this.startingState, null)).andReturn(startingStates);
            replay(this.delta);
            Automaton automate = new Automaton(this.states, this.language, this.delta, this.startingState, this.acceptingStates);
            assertNotNull(automate.currentStates());
            assertTrue(automate.currentStates().includes(this.startingState));
            verify(this.delta);
        }

    }

}
