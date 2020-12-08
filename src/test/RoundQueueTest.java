package test;

import main.CardSlotsBag;
import main.Round;
import main.RoundQueue;
import org.junit.Assert;
import org.junit.Test;

public class RoundQueueTest {

    /**
     * Test RoundQueue enqueue() method
     */
    @Test
    public void enqueue() {

        Round front = new Round(0, new CardSlotsBag());
        RoundQueue roundQueue = new RoundQueue();

        /*
         * Should add Round to the front of the queue
         */

        //Assert is currently null
        Assert.assertNull(roundQueue.getFront());

        roundQueue.enqueue(front);

        var actual = roundQueue.getFront();
        var expected = front;

        //Assert .enqueue(front) added the round.
        Assert.assertEquals(actual, expected);


        /*
         * Should add Round too be the next round after the first Round and be the rear Node.
         */
        Round nextRound = new Round(0, new CardSlotsBag());
        roundQueue.enqueue(nextRound);

        var actual2 = roundQueue.getRear();
        var expected2 = nextRound;

        //Assert that is nextRound is rear
        Assert.assertEquals(actual2, expected2);

    }

    /**
     * Test RoundQueue dequeue() method
     */
    @Test
    public void dequeue() {
        Round front = new Round(0, new CardSlotsBag());
        Round rear = new Round(1, new CardSlotsBag());
        RoundQueue roundQueue = new RoundQueue();
        /*
         * Should not remove a round from an empty queue
         */
        var actual = roundQueue.getFront();

        //Assert roundQueue is null
        Assert.assertNull(actual);

        //Assert roundQueue is still null after a .dequeue null
        roundQueue.dequeue();
        Assert.assertNull(actual);


        /*
         * Should remove a round from a queue with 1 Round in it.
         */
        //Assert queue is not null
        roundQueue.enqueue(front);
        Assert.assertNotNull(roundQueue.getFront());

        //Assert queue is null after calling .dequeue()
        roundQueue.dequeue();
        var actual1 = roundQueue.getFront();
        Assert.assertNull(actual1);


        /*
         * Should remove a round from a queue with 2 Round's in it.
         * and the rear round should now be both the front and rear
         */
        roundQueue.enqueue(front);
        roundQueue.enqueue(rear);

        //Assert front and rear are correct
        Assert.assertEquals(roundQueue.getFront(), front);
        Assert.assertEquals(roundQueue.getRear(), rear);

        //Assert dequeue removes the front round in the que, so that rear is now rear and front
        roundQueue.dequeue();
        Assert.assertEquals(roundQueue.getFront(), rear);
        Assert.assertEquals(roundQueue.getRear(), rear);
    }

    /**
     * Test RoundQueue getFront method
     */
    @Test
    public void getFront() {
        Round front = new Round(0, new CardSlotsBag());
        RoundQueue roundQueue = new RoundQueue();

        /*
          Test getFront() returns the front Round.
         */
        roundQueue.enqueue(front);

        var expected = front;
        var actual = roundQueue.getFront();

        Assert.assertEquals(expected, actual);
    }

    /**
     * Test RoundQueues isEmpty Method
     */
    @Test
    public void isEmpty() {
        Round front = new Round(0, new CardSlotsBag());
        RoundQueue roundQueue = new RoundQueue();
        /*
          Test isEmpty returns true when the RoundQueue is empty.
         */
        var expected = true;
        var actual = roundQueue.isEmpty();

        Assert.assertEquals(expected, actual);

        /*
          Test isEmpty returns false when the RoundQueue is not empty.
         */
        roundQueue.enqueue(front);
        var expected1 = false;
        var actual1 = roundQueue.isEmpty();

        Assert.assertEquals(expected1, actual1);
    }

    /**
     * Test RoundQueues clear() method
     */
    @Test
    public void clear() {
        Round front = new Round(0, new CardSlotsBag());
        RoundQueue roundQueue = new RoundQueue();

        /*
          Test clear clears the RoundQueue, when it was not empty.
         */

        //first add and element and assert is not empty
        roundQueue.enqueue(front);
        Assert.assertEquals(roundQueue.getFront(), front);

        //Assert clear clears the queue and front and rear equal null
        roundQueue.clear();
        Assert.assertNull(roundQueue.getFront());
        Assert.assertNull(roundQueue.getRear());
    }
}