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

        public void test__it_should_be_empty() {
            StateSet set = new StateSet();
            assertEquals(0, set.size());
            assertTrue(set.isEmpty());
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

        public void test__it_should_add_size() throws Exception {
            this.set.add(this.state1);
            assertEquals(1, this.set.size());
            assertTrue(this.set.contains(this.state1));
            this.set.add(this.state2);
            assertEquals(2, this.set.size());
            assertTrue(this.set.contains(this.state2));
        }

        public void test__it_should_not_add_already_added_state() throws Exception {
            this.set.add(this.state1);
            assertEquals(1, this.set.size());
            this.set.add(this.state1);
            assertEquals(1, this.set.size());
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

        public void test__it_should_include_added_state() throws Exception {
            this.set.add(this.state1);
            assertTrue(set.contains(state1));
        }

        public void test__it_should_not_include_not_added_state() throws Exception {
            assertFalse(this.set.contains(this.state1));
            this.set.add(this.state1);
            assertFalse(this.set.contains(this.state2));
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

        public void test__it_should_reduce_size() throws Exception {
            this.set.add(this.state1);
            this.set.add(this.state2);
            assertEquals(2, this.set.size());
            if (!this.set.remove(this.state1))
                fail("First state deleting failed");
            assertEquals(1, this.set.size());
            if (!this.set.remove(this.state2))
                fail("Second state deleting failed");
            assertEquals(0, this.set.size());
        }

        public void test__it_should_raise_error_when_deleting_non_existing_state() throws Exception {
            this.set.add(this.state1);
            assertEquals(1, this.set.size());
            if (this.set.remove(this.state2))
                fail("Should not remove");
            assertEquals(1, this.set.size());
        }

    }

}
