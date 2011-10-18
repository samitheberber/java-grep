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

            private StateSet states;
            private Delta delta;
            private State start;
            private StateSet goals;

            public Automaton(StateSet states, Vocabulary vocabulary, Delta delta, State start, StateSet goals) {
                this.states = states;
                this.delta = delta;
                this.start = start;
                this.goals = goals;
            }

            public boolean run(String characters) throws Exception {
                return this.goals.includes(this.result(characters));
            }

            private State result(String characters) throws Exception {
                State current = this.start;
                for(char c : characters.toCharArray()) {
                    current = this.delta.calculate(current, c);
                    this.ensure(current);
                }
                return current;
            }

            private void ensure(State state) throws Exception {
                if (!this.states.includes(state))
                    throw new Exception("Undeclared state met");
            }

        }

        private class Delta {

            public State calculate(State state, char character) {
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

        public void test__first_state_accepts_and_automate_runs_successful_with_empty_string() throws Exception {
            expect(this.F.includes(this.q0)).andReturn(true);
            replay(this.F);
            assertTrue(this.automate.run(""));
            verify(this.F);
        }

        public void test__first_state_rejects_and_automate_runs_unsuccessful_with_empty_string() throws Exception {
            expect(this.F.includes(this.q0)).andReturn(false);
            replay(this.F);
            assertFalse(this.automate.run(""));
            verify(this.F);
        }

        public void test__further_state_accepts_and_automate_runs_successful_with_string_that_belongs_to_the_language() throws Exception {
            expect(this.delta.calculate(this.q0, 'f')).andReturn(this.q0);
            expect(this.delta.calculate(this.q0, 'o')).andReturn(this.q1);
            expect(this.delta.calculate(this.q1, 'o')).andReturn(this.q3);
            expect(this.delta.calculate(this.q3, 'b')).andReturn(this.q1);
            expect(this.delta.calculate(this.q1, 'a')).andReturn(this.q0);
            expect(this.delta.calculate(this.q0, 'r')).andReturn(this.q2);
            replay(this.delta);
            expect(this.Q.includes(this.q0)).andReturn(true);
            expect(this.Q.includes(this.q0)).andReturn(true);
            expect(this.Q.includes(this.q1)).andReturn(true);
            expect(this.Q.includes(this.q1)).andReturn(true);
            expect(this.Q.includes(this.q2)).andReturn(true);
            expect(this.Q.includes(this.q3)).andReturn(true);
            replay(this.Q);
            expect(this.F.includes(this.q2)).andReturn(true);
            replay(this.F);
            assertTrue(this.automate.run("foobar"));
            verify(this.delta);
            verify(this.Q);
            verify(this.F);
        }

        public void test__further_state_rejects_and_automate_runs_unsuccessful_with_string_that_doesnt_belong_to_the_language() throws Exception {
            expect(this.delta.calculate(this.q0, 'f')).andReturn(this.q0);
            expect(this.delta.calculate(this.q0, 'o')).andReturn(this.q1);
            expect(this.delta.calculate(this.q1, 'b')).andReturn(this.q3);
            expect(this.delta.calculate(this.q3, 'a')).andReturn(this.q2);
            expect(this.delta.calculate(this.q2, 'r')).andReturn(this.q3);
            replay(this.delta);
            expect(this.Q.includes(this.q0)).andReturn(true);
            expect(this.Q.includes(this.q1)).andReturn(true);
            expect(this.Q.includes(this.q2)).andReturn(true);
            expect(this.Q.includes(this.q3)).andReturn(true);
            expect(this.Q.includes(this.q3)).andReturn(true);
            replay(this.Q);
            expect(this.F.includes(this.q3)).andReturn(false);
            replay(this.F);
            assertFalse(this.automate.run("fobar"));
            verify(this.delta);
            verify(this.Q);
            verify(this.F);
        }

        public void test__met_not_belonging_state_so_panic() {
            State q4 = createMock(State.class);
            expect(this.delta.calculate(this.q0, 'f')).andReturn(this.q0);
            expect(this.Q.includes(this.q0)).andReturn(true);
            expect(this.delta.calculate(this.q0, 'u')).andReturn(q4);
            expect(this.Q.includes(q4)).andReturn(false);
            replay(this.delta);
            replay(this.Q);
            try {
                this.automate.run("fubar");
                fail("Exception must be raised");
            } catch(Exception e) {
                verify(this.delta);
                verify(this.Q);
            }
        }

    }

}
