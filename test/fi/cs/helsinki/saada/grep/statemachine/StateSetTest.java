package fi.cs.helsinki.saada.grep.statemachine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.easymock.EasyMock.*;

public class StateSetTest {

    public static Test suite() {
        return new TestSuite(StateSetTest.class.getDeclaredClasses());
    }

    public static class Initializing extends TestCase {

        public void testItShouldBeEmpty() {
            StateSet set = new StateSet();
            assertEquals(0, set.count());
        }

    }

    public static class Inserting extends TestCase {

        private State state1, state2;
        private StateSet set;

        public void setUp() {
            this.state1 = createMock(State.class);
            this.state2 = createMock(State.class);
            this.set = new StateSet();
        }

        public void testItShouldAddCount() {
            this.set.add(this.state1);
            assertEquals(1, this.set.count());
            this.set.add(this.state2);
            assertEquals(2, this.set.count());
        }

    }

    public static class Deleting extends TestCase {

        private State state1, state2;
        private StateSet set;

        public void setUp() {
            this.state1 = createMock(State.class);
            this.state2 = createMock(State.class);
            this.set = new StateSet();
        }

        public void testItShouldReduceCount() throws Exception {
            this.set.add(this.state1);
            this.set.add(this.state2);
            assertEquals(2, this.set.count());
            this.set.delete(this.state1);
            assertEquals(1, this.set.count());
            this.set.delete(this.state2);
            assertEquals(0, this.set.count());
        }

        public void testItShouldRaiseErrorWhenDeletingNonExistState() {
            assertEquals(0, this.set.count());
            try {
                this.set.delete(this.state1);
                fail("Should throw exception");
            } catch(Exception e) {
            }
            assertEquals(0, this.set.count());
        }

    }

}
