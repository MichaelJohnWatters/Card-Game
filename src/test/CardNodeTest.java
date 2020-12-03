        package test;

        import junit.framework.TestCase;
        import main.Card;
        import main.CardNode;
        import main.House;
        import main.Rank;

public class CardNodeTest extends TestCase {
    private Card testCard = new Card(House.CLUBS, Rank.ACE);
    private CardNode cardNodeTest = new CardNode(null);
    private CardNode topNode;

    public void testGetData() {

        var expected = new Card(House.CLUBS, Rank.ACE);
        cardNodeTest.setData(testCard);
        assertEquals(expected.toString(), cardNodeTest.getData().toString());
    }

    public void testSetData() {
        var expected = new Card(House.CLUBS, Rank.ACE);
        cardNodeTest.setData(testCard);
        assertEquals(expected.toString(), cardNodeTest.getData().toString());
    }

    public void testGetNext() {
        var expected = cardNodeTest;
        cardNodeTest.setData(testCard);
        cardNodeTest.setNext(topNode = cardNodeTest);
        var actual = cardNodeTest.getNext();
        assertEquals(expected.toString(), actual.toString());
    }

    public void testSetNext() {
        var expected = cardNodeTest;
        cardNodeTest.setData(testCard);
        cardNodeTest.setNext(topNode = cardNodeTest);
        var actual = cardNodeTest.getNext();
        assertEquals(expected.toString(), actual.toString());
    }
}