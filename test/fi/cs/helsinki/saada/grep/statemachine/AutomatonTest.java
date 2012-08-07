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

    public static class DefineDFA extends TestCase {

        private StateSet Q;
        private StateSet F;
        private Vocabulary sigma;
        private DeltaDFA delta;
        private State q0;
        private State q1;
        private State q2;
        private State q3;
        private DFA automate;

        public void setUp() {
            this.Q = createMock(StateSet.class);
            this.F = createMock(StateSet.class);
            this.sigma = createMock(Vocabulary.class);
            this.delta = createMock(DeltaDFA.class);
            this.q0 = createMock(State.class);
            this.q1 = createMock(State.class);
            this.q2 = createMock(State.class);
            this.q3 = createMock(State.class);
            this.automate = new DFA(Q, sigma, delta, q0, F);
        }

        public void test__we_can_create_dfa() {
            assertNotNull(this.automate);
        }

        public void test__first_state_accepts_and_automate_runs_successful_with_empty_string() throws Exception {
            stateSetIncludes(F, q0);
            replay(this.F);
            assertTrue(this.automate.run(""));
            verify(this.F);
        }

        public void test__first_state_rejects_and_automate_runs_unsuccessful_with_empty_string() throws Exception {
            stateSetIncludesNot(F, q0);
            replay(this.F);
            assertFalse(this.automate.run(""));
            verify(this.F);
        }

        public void test__further_state_accepts_and_automate_runs_successful_with_string_that_belongs_to_the_language() throws Exception {
            belongsToVocabulary("foobar");
            replay(this.sigma);
            expect(this.delta.calculate(this.q0, 'f')).andReturn(this.q0);
            expect(this.delta.calculate(this.q0, 'o')).andReturn(this.q1);
            expect(this.delta.calculate(this.q1, 'o')).andReturn(this.q3);
            expect(this.delta.calculate(this.q3, 'b')).andReturn(this.q1);
            expect(this.delta.calculate(this.q1, 'a')).andReturn(this.q0);
            expect(this.delta.calculate(this.q0, 'r')).andReturn(this.q2);
            replay(this.delta);
            State[] q_states = {q0, q0, q1, q1, q2, q3};
            stateSetIncludes(Q, q_states);
            replay(this.Q);
            stateSetIncludes(F, q2);
            replay(this.F);
            assertTrue(this.automate.run("foobar"));
            verify(this.sigma);
            verify(this.delta);
            verify(this.Q);
            verify(this.F);
        }

        public void test__further_state_rejects_and_automate_runs_unsuccessful_with_string_that_doesnt_belong_to_the_language() throws Exception {
            belongsToVocabulary("fobar");
            replay(this.sigma);
            expect(this.delta.calculate(this.q0, 'f')).andReturn(this.q0);
            expect(this.delta.calculate(this.q0, 'o')).andReturn(this.q1);
            expect(this.delta.calculate(this.q1, 'b')).andReturn(this.q3);
            expect(this.delta.calculate(this.q3, 'a')).andReturn(this.q2);
            expect(this.delta.calculate(this.q2, 'r')).andReturn(this.q3);
            replay(this.delta);
            State[] q_states = {q0, q1, q2, q3, q3};
            stateSetIncludes(Q, q_states);
            replay(this.Q);
            stateSetIncludesNot(F, q3);
            replay(this.F);
            assertFalse(this.automate.run("fobar"));
            verify(this.sigma);
            verify(this.delta);
            verify(this.Q);
            verify(this.F);
        }

        public void test__met_not_belonging_state_so_panic() {
            State q4 = createMock(State.class);
            belongsToVocabulary("fu");
            replay(this.sigma);
            expect(this.delta.calculate(this.q0, 'f')).andReturn(this.q0);
            expect(this.delta.calculate(this.q0, 'u')).andReturn(q4);
            replay(this.delta);
            stateSetIncludes(Q, q0);
            stateSetIncludesNot(Q, q4);
            replay(this.Q);
            try {
                this.automate.run("fubar");
                fail("Exception must be raised");
            } catch(Exception e) {
                verify(this.sigma);
                verify(this.delta);
                verify(this.Q);
            }
        }

        public void test__met_not_belonging_char_so_panic() {
            State q4 = createMock(State.class);
            belongsToVocabulary("f");
            notBelongsToVocabulary("u");
            replay(this.sigma);
            expect(this.delta.calculate(this.q0, 'f')).andReturn(this.q0);
            replay(this.delta);
            stateSetIncludes(Q, q0);
            replay(this.Q);
            try {
                this.automate.run("fubar");
                fail("Exception must be raised");
            } catch(Exception e) {
                verify(this.sigma);
                verify(this.delta);
                verify(this.Q);
            }
        }

        private void belongsToVocabulary(String string, boolean including) {
            char[] letters = string.toCharArray();
            for (char letter : letters) {
                expect(this.sigma.includes(letter)).andReturn(including);
            }
        }

        private void belongsToVocabulary(String string) {
            belongsToVocabulary(string, true);
        }

        private void notBelongsToVocabulary(String string) {
            belongsToVocabulary(string, false);
        }

        private void stateSetIncludes(StateSet set, State[] states, boolean including) {
            for (State state : states) {
                stateSetIncludes(set, state, including);
            }
        }

        private void stateSetIncludes(StateSet set, State[] states) {
            stateSetIncludes(set, states, true);
        }

        private void stateSetIncludesNot(StateSet set, State[] states) {
            stateSetIncludes(set, states, false);
        }

        private void stateSetIncludes(StateSet set, State state, boolean including) {
            expect(set.includes(state)).andReturn(including);
        }

        private void stateSetIncludes(StateSet set, State state) {
            stateSetIncludes(set, state, true);
        }

        private void stateSetIncludesNot(StateSet set, State state) {
            stateSetIncludes(set, state, false);
        }

    }

}
