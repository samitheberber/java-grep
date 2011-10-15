package fi.cs.helsinki.saada.grep.statemachine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StateTest {

  public static Test suite() {
    return new TestSuite(StateTest.class.getDeclaredClasses());
  }

  public static class StateAcceptance extends TestCase {

    public void testStateIsNotAcceptingByDefault() {
      State state = new State();
      assertFalse(state.isAccepting());
    }

    public void testStateIsAccepting() {
      State state = new State(true);
      assertTrue(state.isAccepting());
    }

    public void testStateCanBeSetToAccept() {
      State state = new State();
      assertFalse(state.isAccepting());
      state.setAcceptance(true);
      assertTrue(state.isAccepting());
    }

    public void testStateCanBeSetToNotAccept() {
      State state = new State(true);
      assertTrue(state.isAccepting());
      state.setAcceptance(false);
      assertFalse(state.isAccepting());
    }

  }

}
