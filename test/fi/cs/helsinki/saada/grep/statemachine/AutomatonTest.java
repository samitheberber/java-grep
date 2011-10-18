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

            private Delta delta;
            private State start;
            private StateSet goals;

            public Automaton(StateSet states, Vocabulary vocabulary, Delta delta, State start, StateSet goals) {
                this.delta = delta;
                this.start = start;
                this.goals = goals;
            }

            public boolean run(String characters) {
                return this.goals.includes(this.delta.calculate(this.start, characters));
            }

        }

        private class Delta {

            public State calculate(State state, String characters) {
                return null;
            }

        }

        private StateSet Q;
        private StateSet F;
        private Vocabulary sigma;
        private Delta delta;
        private State q0;
        private State q1;
        private State q2;
        private State q3;
        private Automaton automate;

        public void setUp() {
            this.Q = createMock(StateSet.class);
            this.F = createMock(StateSet.class);
            this.sigma = createMock(Vocabulary.class);
            this.delta = createMock(Delta.class);
            this.q0 = createMock(State.class);
            this.q1 = createMock(State.class);
            this.q2 = createMock(State.class);
            this.q3 = createMock(State.class);
            this.automate = new Automaton(Q, sigma, delta, q0, F);
        }

        public void test__we_can_create_dfa() {
            assertNotNull(this.automate);
        }

        public void test__first_state_accepts_and_automate_run_successful_with_empty_string() {
            expect(this.delta.calculate(this.q0, "")).andReturn(this.q0);
            replay(this.delta);
            expect(this.F.includes(this.q0)).andReturn(true);
            replay(this.F);
            assertTrue(this.automate.run(""));
            verify(this.delta);
            verify(this.F);
        }

    }

}
