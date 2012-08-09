/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.cs.helsinki.saada.grep.statemachine.state_transition;

import fi.cs.helsinki.saada.grep.statemachine.State;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author stb
 */
public class StateTransitionTest {

    private class StateTransitionImpl extends AbstractStateTransition {

        public StateTransitionImpl(StateTransitionStructure structure) {
            super(structure);
        }

    }
    private StateTransitionStructure structure;
    private StateTransition stateTransition;

    @Before
    public void setUp() throws Exception {
        this.structure = createMock(StateTransitionStructure.class);
        this.stateTransition = new StateTransitionImpl(this.structure);
    }

    @Test
    public void rules_can_be_added_and_found_afterwards() {
        State q0 = createMock(State.class);
        StateTransitionResult q1 = createMock(State.class);
        expect(structure.add(q0, 'a', q1)).andReturn(true);
        expect(structure.contains(q0, 'a')).andReturn(true);
        replay(structure);
        assertTrue(stateTransition.add(q0, 'a', q1));
        assertTrue(stateTransition.contains(q0, 'a'));
        verify(structure);
    }
}