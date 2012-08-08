package fi.cs.helsinki.saada.grep.statemachine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.easymock.EasyMock.createMock;

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

        @Override
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

    }

}