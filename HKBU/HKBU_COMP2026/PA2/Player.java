import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    /**
     * The cards held on a player hand
     */
    private Card[] hand;
    /**
     * The number of card held by the player. This variable should be maintained
     * to match array hand.
     */
    private int handCount;
    /**
     * A dynamic array that holds the score pile.
     */
    private Card[] pile;
    /**
     * The name of the player
     */
    private String name;
    /**
     * A static variable that tells how many player has been initialized
     */
    private static int count = 1;

    /**
     * Other constructor that specify the name of the player.
     * 
     * You need to initialize your data member properly.
     */
    public Player(String name) {
        this.name = name;
        hand = new Card[10];
        handCount = 0;
        pile = new Card[0];

    }

    /**
     * Default constructor that set the name of the player as "Computer #1",
     * "Computer #2", "Computer #3"...
     * The number grows when there are more computer players being created.
     * 
     * You need to initialize your data member properly.
     */
    public Player() {
        this("Computer #" + count++);
    }

    /**
     * Getter of name
     * 
     * @return - the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * This method is called when a player is required to take the card from a stack
     * to his score pile. The variable pile should be treated as a dynamic array so
     * that the array will auto-resize to hold enough number of cards. The length of
     * pile should properly record the total number of cards taken by a player.
     * 
     * Important: at the end of this method, you should also help removing all cards
     * from the parameter array "cards".
     * 
     * @param cards - an array of cards taken from a stack
     * @param count - number of cards taken from the stack
     */
    public void moveToPile(Card[] cards, int count) {
        Card[] newPile = new Card[pile.length + count];
        System.out.print(name + " takes the card:");
        for (int i = 0; i < pile.length; i++)
            newPile[i] = pile[i];
        for (int j = pile.length, i = 0; j < pile.length + count; j++, i++) {
            newPile[j] = cards[i];
            cards[i].print();
            cards[i] = null;
        }
        pile = newPile;
        System.out.println("\t //Printing this message is optional");
    }

    /**
     * To print the score pile. This method has been completed for you
     * 
     * You don't need to modify it and you should not modify it.
     */
    public void printPile() {
        for (Card c : pile) {
            c.print();
        }
        System.out.println();
    }

    /**
     * This is a getter of hand's card. This method has been completed for you
     *
     * You don't need to modify it and you should not modify it.
     * @param index - the index of card to take
     * @return - the card from the hand or null
     */
    public Card getHandCard(int index) {
        if (index < 0 || index >= handCount) 
            return null;
        return hand[index];
    }


    /**
     * This method prompts a human player to play a card. It first print
     * all cards on his hand. Then the player will need to select a card
     * to play by entering the INDEX of the card.
     * 
     * @return - the card to play
     */
    public Card playCard() {
        while (true) {
            for (int i = 0; i < handCount; i++) {
                System.out.print(i + ": ");
                hand[i].print();
                System.out.println();
            }
            System.out.println(name + ", please select a card to play: ");
            Scanner s = new Scanner(System.in);
            Card c = playCard(s.nextInt());
            if (c != null)
                return c;
        }
    }

    /**
     * This method let a computer player to play a card randomly. The computer
     * player will pick any avaliable card to play in a random fashion.
     * 
     * @return - card to play
     */
    public Card playCardRandomly() {
        return playCard(ThreadLocalRandom.current().nextInt(0, handCount));
    }

    /**
     * Deal a card to a player. This should add a card to the variable hand and
     * update the variable handCount. During this method, you do not need to resize
     * the array. You can assume that a player will be dealt with at most 10 cards.
     * That is, the method will only be called 10 times on a player.
     * 
     * After each call of this method, the hand should be sorted properly according
     * to the number of the card.
     * 
     * @param card - a card to be dealt
     */
    public void dealCard(Card card) {
        if (handCount == 10) // too much
            return;
        int i;
        for (i = 0; i < handCount; i++) {
            if (card.getNumber() < hand[i].getNumber()) { // insert here
                // step 1, move all cards from i one index up
                for (int j = handCount - 1; j >= i; j--)
                    hand[j + 1] = hand[j];
                break;
            }
        }
        // step 2, insert the card there.
        hand[i] = card;
        // step 3, increase the hand count.
        handCount++;
    }

    /**
     * Get the score of the player by counting the total number of Bull Head in the
     * score pile.
     * 
     * @return - score, 0 or a positive integer
     */
    public int getScore() {
        int sum = 0;
        for (Card c : pile)
            sum += c.getBullHead();
        return sum;
    }

    // Kevin's helper method
    private Card playCard(int index) {
        if (index < 0 || index >= handCount)
            return null; // invalid index
        Card result = hand[index];
        for (int i = index; i < handCount - 1; i++)
            hand[i] = hand[i + 1];
        hand[--handCount] = null;

        return result;
    }

}
