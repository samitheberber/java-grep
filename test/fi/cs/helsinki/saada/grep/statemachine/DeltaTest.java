package fi.cs.helsinki.saada.grep.statemachine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.easymock.EasyMock.createMock;

public class DeltaTest {

    public static Test suite() {
        return new TestSuite(DeltaTest.class.getDeclaredClasses());
    }

    public static class Creation extends TestCase {

        private class PlainDelta extends AbstractDelta {

            public PlainDelta() {
            }

        }

        private Delta delta;

        @Override
        public void setUp() {
            this.delta = new PlainDelta();
        }

        public void test__rules_can_be_added() {
            State q0 = createMock(State.class);
            DeltaResult q1 = createMock(State.class);
            assertTrue(this.delta.add(q0, 'a', q1));
            assertTrue(this.delta.contains(q0, 'a'));
        }

    }

    public static class Calculation extends TestCase {
        public void test__placeholder() {
            assertTrue(true);
        }
    }

    public static class DeltaStar extends TestCase {
        public void test__placeholder() {
            assertTrue(true);
        }
    }

}