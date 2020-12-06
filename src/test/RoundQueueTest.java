package test;

import junit.framework.TestCase;
import main.*;
import org.junit.Test;

public class RoundQueueTest extends TestCase {

    @Test
    public void testEnqueue() {
        //Setup test
        RoundQueue testRoundQueue = new RoundQueue();
        testRoundQueue.enqueue(new Round(1));
        var actual = testRoundQueue.getFront();

        //expected
        var expected = 1;
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testDequeue() {
        //Setup test
        RoundQueue testRoundQueue = new RoundQueue();
        testRoundQueue.enqueue(new Round(1));

        testRoundQueue.dequeue();
        var actual = testRoundQueue.getFront();

        //Assert
        assertNull(actual);
    }

    @Test
    public void testGetFront() {
        //Setup test
        RoundQueue testRoundQueue = new RoundQueue();
        testRoundQueue.enqueue(new Round(1));

        var actual = testRoundQueue.getFront();

        //expected
        var expected = new Round(1);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testIsEmpty() {
        //Setup test
        RoundQueue testRoundQueue = new RoundQueue();

        //Assert
        assertEquals(true, testRoundQueue.isEmpty());
    }

    @Test
    public void testClear() {
        //Setup test
        RoundQueue testRoundQueue = new RoundQueue();
        testRoundQueue.enqueue(new Round(1));

        testRoundQueue.clear();
        var actual = testRoundQueue.getFront();

        //Assert
        assertNull(actual);
    }

}