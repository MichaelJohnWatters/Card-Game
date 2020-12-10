package test;

import main.Game;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for Game Class
 *
 * Developer Note: unit tests here are bare as we do not have many Setters for this class.
 * As they are not required for this object.
 *
 * Methods that have only be tested Manually, as we cannot via unit test without illegal libraries.
 * computerDemonstrationGame()
 * userPlayableGame()
 * getDeck()
 * getDiscardDeck()
 */
public class GameTest {

    /**
     * Test 1 getRoundQueue should return null, when a new game is created as no round Queue has been created
     */
    @Test
    public void getRoundQueue() {
        Game game = new Game();

        //Test 1 getRoundQueue should return null, when a new game is created as no rounds have be generated
        assertNull(game.getRoundQueue());
    }

    /**
     * Test 1 getCurrentRound should return null, when a new game is created as no rounds has been created
     */
    @Test
    public void getCurrentRound() {
        Game game = new Game();

        //Test 1 getCurrentRound should return null, when a new game is created as no rounds have be generated
        assertNull(game.getCurrentRound());
    }

    /**
     * Test 1 .getGameResult() returns false when a unplayed game object is created.
     */
    @Test
    public void getGameResult() {
        Game game = new Game();

        //Test 1 .getGameResult() returns false when a unplayed game object is created.
        Assert.assertFalse(game.getGameResult());
    }
}