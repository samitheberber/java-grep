package fi.cs.helsinki.saada.grep.statemachine;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author stb
 */
public class AutomatonTest {

    private class AutomatonImpl extends AbstractAutomaton {

        public AutomatonImpl(StateSet states, Vocabulary vocabulary, StateTransition delta, State start, StateSet goals) {
            super(states, vocabulary, delta, start, goals);
        }
    }

    private StateSet states, acceptingStates;
    private State startingState;
    private StateTransition delta;
    private Vocabulary vocabulary;
    private Automaton automaton;

    @Before
    public void setUp() throws Exception {
        this.states = createMock(StateSet.class);
        this.vocabulary = createMock(Vocabulary.class);
        this.delta = createMock(StateTransition.class);
        this.startingState = createMock(State.class);
        this.acceptingStates = createMock(StateSet.class);

        this.automaton = new AutomatonImpl(this.states, this.vocabulary, this.delta, this.startingState, this.acceptingStates);
    }

    @Test
    public void states_should_be_given() {
        assertEquals(states, automaton.getStates());
    }

    @Test
    public void vocabulary_should_be_given() {
        assertEquals(vocabulary, automaton.getVocabulary());
    }

    @Test
    public void delta_should_be_given() {
        assertEquals(delta, automaton.getStateTransition());
    }

    @Test
    public void starting_state_should_be_given() {
        assertEquals(startingState, automaton.getStartingState());
    }

    @Test
    public void accepting_states_should_be_given() {
        assertEquals(acceptingStates, automaton.getAcceptingStates());
    }
}