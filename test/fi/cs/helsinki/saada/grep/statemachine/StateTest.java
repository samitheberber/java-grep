package fi.cs.helsinki.saada.grep.statemachine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StateTest {

  public static Test suite() {
    return new TestSuite(StateTest.class.getDeclaredClasses());
  }

  public static class StateClass extends TestCase {

    public void testStateIsDefined() {
      State state = new State();
    }

  }

}
