package fi.cs.helsinki.saada.grep.statemachine;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author stb
 */
public class StateTest {

    @Test
    public void default_name_is_empty() {
        State state = new State();
        assertEquals("", state.toString());
    }

    @Test
    public void named_state_gives_set_name() {
        State state = new State("foobar");
        assertEquals("foobar", state.toString());
    }
}