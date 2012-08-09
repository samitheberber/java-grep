/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.cs.helsinki.saada.grep.statemachine;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author stb
 */
public class StateTransitionTest {

    private class StateTransitionStructure {

        public StateTransitionStructure() {
        }

        public boolean add(State from, char character, StateTransitionResult to) {
            return true;
        }
    }

    private class DeltaImpl extends AbstractStateTransition {
        private StateTransitionStructure structure;

        public DeltaImpl(StateTransitionStructure structure) {
            this.structure = structure;
        }

        @Override
        public boolean add(State from, char character, StateTransitionResult to) {
            return structure.add(from, character, to);
        }

    }
    private StateTransitionStructure structure;
    private StateTransition delta;

    @Before
    public void setUp() throws Exception {
        this.structure = createMock(StateTransitionStructure.class);
        this.delta = new DeltaImpl(this.structure);
    }

    @Test
    public void rules_can_be_added_and_found_afterwards() {
        State q0 = createMock(State.class);
        StateTransitionResult q1 = createMock(State.class);
        expect(structure.add(q0, 'a', q1)).andReturn(true);
        replay(structure);
        assertTrue(this.delta.add(q0, 'a', q1));
        assertTrue(this.delta.contains(q0, 'a'));
        verify(structure);
    }
}