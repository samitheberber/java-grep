package fi.cs.helsinki.saada.grep.statemachine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.easymock.EasyMock.*;

public class DFATest {

    public static Test suite() {
        return new TestSuite(DFATest.class.getDeclaredClasses());
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
            expect(this.delta.calculate(this.q0, 'f')).andReturn(this.q0);
            expect(this.delta.calculate(this.q0, 'o')).andReturn(this.q1);
            expect(this.delta.calculate(this.q1, 'o')).andReturn(this.q3);
            expect(this.delta.calculate(this.q3, 'b')).andReturn(this.q1);
            expect(this.delta.calculate(this.q1, 'a')).andReturn(this.q0);
            expect(this.delta.calculate(this.q0, 'r')).andReturn(this.q2);
            replay(this.delta);
            stateSetIncludes(F, q2);
            replay(this.F);
            assertTrue(this.automate.run("foobar"));
            verify(this.delta);
            verify(this.F);
        }

        public void test__further_state_rejects_and_automate_runs_unsuccessful_with_string_that_doesnt_belong_to_the_language() throws Exception {
            expect(this.delta.calculate(this.q0, 'f')).andReturn(this.q0);
            expect(this.delta.calculate(this.q0, 'o')).andReturn(this.q1);
            expect(this.delta.calculate(this.q1, 'b')).andReturn(this.q3);
            expect(this.delta.calculate(this.q3, 'a')).andReturn(this.q2);
            expect(this.delta.calculate(this.q2, 'r')).andReturn(this.q3);
            replay(this.delta);
            stateSetIncludesNot(F, q3);
            replay(this.F);
            assertFalse(this.automate.run("fobar"));
            verify(this.delta);
            verify(this.F);
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
            expect(set.contains(state)).andReturn(including);
        }

        private void stateSetIncludes(StateSet set, State state) {
            stateSetIncludes(set, state, true);
        }

        private void stateSetIncludesNot(StateSet set, State state) {
            stateSetIncludes(set, state, false);
        }

    }

}
