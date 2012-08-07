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

        private class ExampleDelta implements Delta {

            public StateSet calculate(State state, char character) {
                return null;
            }
        }

        private class PlainAutomaton extends AbstractAutomaton {

            public PlainAutomaton(
                    StateSet states,
                    Vocabulary vocabulary,
                    Delta delta,
                    State startingState,
                    StateSet acceptingStates
                    ) {
                this.setStates(states);
                this.setVocabulary(vocabulary);
                this.setDelta(delta);
                this.setAcceptingStates(acceptingStates);
                this.setCurrentStates(delta.calculate(startingState, Vocabulary.EPSILON));
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
            expect(this.delta.calculate(this.startingState, Vocabulary.EPSILON)).andReturn(startingStates);
            replay(this.delta);
            Automaton automate = new PlainAutomaton(this.states, this.vocabulary, this.delta, this.startingState, this.acceptingStates);
            assertNotNull(automate.currentStates());
            assertTrue(automate.currentStates().includes(this.startingState));
            verify(this.delta);
        }

    }

}
