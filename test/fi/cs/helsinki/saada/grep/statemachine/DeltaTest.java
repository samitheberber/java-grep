/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.cs.helsinki.saada.grep.statemachine;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author stb
 */
public class DeltaTest {

    private class DeltaImpl extends AbstractDelta {
    }
    private Delta delta;

    @Before
    public void setUp() throws Exception {
        this.delta = new DeltaImpl();
    }

    @Test
    public void rules_can_be_added_and_found_afterwards() {
        State q0 = createMock(State.class);
        DeltaResult q1 = createMock(State.class);
        assertTrue(this.delta.add(q0, 'a', q1));
        assertTrue(this.delta.contains(q0, 'a'));
    }
}