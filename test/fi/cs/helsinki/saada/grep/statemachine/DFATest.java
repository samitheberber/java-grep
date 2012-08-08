package fi.cs.helsinki.saada.grep.statemachine;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author stb
 */
public class DFATest {

    private StateSet Q;
    private StateSet F;
    private Vocabulary sigma;
    private DeltaDFA delta;
    private State q0;
    private DFA dfa;

    @Before
    public void setUp() throws Exception {
        this.Q = createMock(StateSet.class);
        this.sigma = createMock(Vocabulary.class);
        this.delta = createMock(DeltaDFA.class);
        this.q0 = createMock(State.class);
        this.F = createMock(StateSet.class);
        this.dfa = new DFA(Q, sigma, delta, q0, F);
    }

    @Test
    public void dfa_can_be_created() {
        assertNotNull(dfa);
    }
}