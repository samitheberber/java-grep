/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.cs.helsinki.saada.grep.statemachine;

import java.util.Iterator;
import java.util.LinkedList;
import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author stb
 */
public class StateSetTest {

    private State state1, state2;
    private StateSet states;
    private LinkedList<State> collection;

    @Before
    public void setUp() throws Exception {
        this.state1 = createMock(State.class);
        this.state2 = createMock(State.class);
        this.states = new StateSet();
        this.collection = new LinkedList<State>();
        this.collection.add(state1);
        this.collection.add(state2);
    }

    @Test
    public void empty_state_set_should_be_empty() {
        assertTrue(states.isEmpty());
        assertEquals(0, states.size());
    }

    @Test
    public void add_new_element_to_state_set_will_change_the_state() {
        assertTrue(states.add(state1));
        assertFalse(states.isEmpty());
        assertEquals(1, states.size());
        assertTrue(states.contains(state1));
    }

    @Test
    public void add_same_element_second_time_wont_alter_set() {
        assertTrue(states.add(state1));
        assertEquals(1, states.size());
        assertFalse(states.add(state1));
        assertEquals(1, states.size());
    }

    @Test
    public void add_multiple_elements_in_row() {
        assertTrue(states.add(state1));
        assertTrue(states.add(state2));
        assertEquals(2, states.size());
        assertTrue(states.contains(state1));
        assertTrue(states.contains(state2));
    }

    @Test
    public void add_multiple_elements_in_same_time() {
        assertTrue(states.addAll(collection));
        assertEquals(2, states.size());
        assertTrue(states.containsAll(collection));
    }

    @Test
    public void state_set_should_not_contain_non_added_element() {
        assertFalse(states.contains(state1));
    }

    @Test
    public void state_set_should_not_contain_all_if_only_part_of_collection_includes_if_set() {
        states.add(state1);
        assertFalse(states.containsAll(collection));
    }

    @Test
    public void state_set_can_be_cleared() {
        states.add(state1);
        states.clear();
        assertTrue(states.isEmpty());
    }

    @Test
    public void state_can_be_removed_from_set() {
        states.add(state1);
        assertTrue(states.remove(state1));
        assertTrue(states.isEmpty());
    }

    @Test
    public void size_is_reduced_correctly_when_removed() {
        states.add(state1);
        states.add(state2);
        states.remove(state2);
        assertEquals(1, states.size());
        states.remove(state1);
        assertEquals(0, states.size());
    }

    @Test
    public void non_existing_element_cannot_be_removed() {
        assertFalse(states.remove(state1));
    }

    @Test
    public void removing_from_collection_will_notice_if_set_is_changes() {
        states.add(state1);
        assertTrue(states.removeAll(collection));
    }

    @Test
    public void collection_of_non_existing_elements_wont_change_state_if_removed() {
        assertFalse(states.removeAll(collection));
    }

    @Test
    public void array_can_be_formed() {
        assertEquals(0, states.toArray().length);
        states.add(state1);
        assertEquals(1, states.toArray().length);
        assertEquals(state1, states.toArray()[0]);
    }

    @Test
    public void state_set_can_be_iterated() {
        states.add(state1);
        states.add(state2);
        for (State state : states) {
            assertNotNull(state);
        }
    }
}