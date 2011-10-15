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

        public void testItShouldAddCount() throws Exception {
            this.set.add(this.state1);
            assertEquals(1, this.set.count());
            this.set.add(this.state2);
            assertEquals(2, this.set.count());
        }

        public void testItShouldNotAddAlreadyAddedState() throws Exception {
            this.set.add(this.state1);
            assertEquals(1, this.set.count());
            try {
                this.set.add(this.state1);
                fail("Should throw exception");
            } catch(Exception e) {
            }
            assertEquals(1, this.set.count());
        }

    }

    public static class Including extends TestCase {

        private State state1, state2;
        private StateSet set;

        public void setUp() {
            this.state1 = createMock(State.class);
            this.state2 = createMock(State.class);
            this.set = new StateSet();
        }

        public void testItShouldIncludeAddedState() throws Exception {
            this.set.add(this.state1);
            assertTrue(this.set.includes(this.state1));
        }

        public void testItShouldNotIncludeNotAddedState() throws Exception {
            assertFalse(this.set.includes(this.state1));
            this.set.add(this.state1);
            assertFalse(this.set.includes(this.state2));
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
            try {
                this.set.delete(this.state1);
            } catch(Exception e) {
                fail("First state deleting failed");
            }
            assertEquals(1, this.set.count());
            try {
                this.set.delete(this.state2);
            } catch(Exception e) {
                fail("Second state deleting failed");
            }
            assertEquals(0, this.set.count());
        }

        public void testItShouldRaiseErrorWhenDeletingNonExistState() throws Exception {
            this.set.add(this.state1);
            assertEquals(1, this.set.count());
            try {
                this.set.delete(this.state2);
                fail("Should throw exception");
            } catch(Exception e) {
            }
            assertEquals(1, this.set.count());
        }

    }

}
