import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Table {
    /**
     * Total number of player. Use this variable whenever possible
     */
    private static final int NUM_OF_PLAYERS = 4;
    /**
     * Total number of cards used in this game. Use this variable whenever possible
     */
    private static final int TOTAL_NUMBER_OF_CARD = 104;
    /**
     * The four stacks of cards on the table.
     */
    private Card[][] stacks = new Card[4][6];
    /**
     * This number of cards of each stack on the table. For example, if the variable
     * stacks stores
     * -------------------------
     * | 0 | 10 13 14 -- -- -- |
     * | 1 | 12 45 -- -- -- -- |
     * | 2 | 51 55 67 77 88 90 |
     * | 3 | 42 -- -- -- -- -- |
     * -------------------------
     * 
     * stacksCount should be {3, 2, 6, 1}.
     * 
     * You are responsible to maintain the data consistency.
     */
    private int[] stacksCount = new int[4];
    /**
     * The array of players
     */
    private Player[] players = new Player[NUM_OF_PLAYERS];

    /**
     * Default constructor
     * 
     * In the constructor, you should perform the following tasks:
     * 
     * 1. Initialize cards for play. You should construct enough number of cards
     * to play. These cards should be unique (i.e., no two cards share the same
     * number). The value of card must be between 1 to 104. The number of bullHead
     * printed on each card can be referred to the rule.
     * 
     * 2. Initialize four player. The first player should be a human player, call
     * "Kevin". The other player should be a computer player. These computer player
     * should have the name "Computer #1", "Computer #2", "Computer #3".
     * 
     * 3. Deal randomly 10 cards to each player. A card can only be dealt to one
     * player. That is, no two players can have the same card.
     * 
     * 4. Deal a card on each stack. The card dealt on the stack should not be dealt
     * to any player. Card dealt on each stack should also be unique (no two stack
     * have the same card).
     * 
     */
    public Table() {

        Card[] cards = generateCard();
        boolean[] used = new boolean[TOTAL_NUMBER_OF_CARD];

        // Create Player and deal cards
        for (int i = 0; i < NUM_OF_PLAYERS; i++) {
            if (i == 0) // human player
                players[i] = new Player("Kevin");
            else
                players[i] = new Player();

            for (int j = 0; j < 10; j++) {
                int r;
                do {
                    r = ThreadLocalRandom.current().nextInt(0, TOTAL_NUMBER_OF_CARD);
                } while (used[r]);
                used[r] = true;
                players[i].dealCard(cards[r]);
            }
        }
        // flip card on each stack.
        for (int i = 0; i < 4; i++) {
            int r;
            do {
                r = ThreadLocalRandom.current().nextInt(0, TOTAL_NUMBER_OF_CARD);
            } while (used[r]);
            used[r] = true;
            stacks[i][0] = cards[r];
            stacksCount[i] = 1;
        }

    }

    /**
     * This method is to find the correct stack that a card should be added to
     * according to the rule.
     * It should return the stack among which top-card of that stack is the largest
     * of those smaller than
     * the card to be placed. (If the rule sounds complicate to you, please refer to
     * the game video.)
     * 
     * In case the card to be place is smaller than the top cards of all stacks,
     * return -1.
     * 
     * @param card - the card to be placed
     * @return the index of stack (0,1,2,3) that the card should be place or -1 if
     *         the card is smaller than all top cards
     */
    public int findStackToAdd(Card card) {
        int toAdd = -1;
        int value = -1;
        for (int i = 0; i < 4; i++) {
            Card topcard = stacks[i][stacksCount[i] - 1];
            if (topcard.getNumber() < card.getNumber() && topcard.getNumber() > value) {
                value = topcard.getNumber();
                toAdd = i;
            }
        }
        return toAdd; // return -1 if none of the stack is smaller than the card.
    }

    /**
     * To print the stacks on the table. Please refer to the demo program for the
     * format.
     * Within each stack, the card should be printed in ascending order, left to
     * right.
     * However, there is no requirement on the order of stack to print.
     */
    public void print() {
        System.out.println("----------Table----------");
        for (int i = 0; i < 4; i++) {
            System.out.print("Stack " + i + ":");
            for (int j = 0; j < stacksCount[i]; j++) 
                stacks[i][j].print();
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    /**
     * This method is the main logic of the game. You should create a loop for 10
     * times (running 10 rounds). In each round all players will need to play a
     * card. These cards will be placed to the stacks from small to large according
     * to the rule of the game.
     * 
     * In case a player plays a card smaller than all top cards, he will be
     * selecting one of the stack of cards and take them to his/her own score pile.
     * If the player is a human player, he will be promoted for selection. If the
     * player is a computer player, the computer player will select the "cheapest"
     * stack, i.e. the stack that has fewest bull heads. If there are more than
     * one stack having fewest bull heads, selecting any one of them.
     */
    public void runApp() {
        for (int turn = 0; turn < 10; turn++) {
            // print Table
            print();

            Card[] thisRound = new Card[4];

            for (int i = 0; i < 4; i++) {
                thisRound[i] = i == 0 ? players[i].playCard() : players[i].playCardRandomly();
                // Print the card played by each player
                System.out.print(players[i].getName() + ":");
                thisRound[i].print();
                System.out.println();
            }

            // for each card, place it.
            int lastPlacedCard = 0;
            for (int iter = 0; iter < 4; iter++) {
                int min = TOTAL_NUMBER_OF_CARD;
                int player = -1;

                for (int i = 0; i < 4; i++) {
                    if (thisRound[i].getNumber() <= min && thisRound[i].getNumber() > lastPlacedCard) {
                        min = thisRound[i].getNumber();
                        player = i;
                    }
                }

                Card cardToPlace = thisRound[player];
                System.out.println("Place the card " + cardToPlace + " for " + players[player].getName());
                int stackToAdd = findStackToAdd(cardToPlace);

                if (stackToAdd == -1) { // pick a stack
                    if (player == 0) {
                        Scanner s = new Scanner(System.in);
                        do {
                            System.out.println("Pick a stack to collect the cards");
                            stackToAdd = s.nextInt();
                        } while (stackToAdd < 0 || stackToAdd > 3);
                    } else { // pick the cheapest stack
                        int score = getScore(0);
                        stackToAdd = 0;
                        for (int i = 1; i < 4; i++)
                            if (score > getScore(i)) {
                                score = getScore(i);
                                stackToAdd = i;
                            }
                    }
                    // this will also empty stacks[stackToAdd]
                    players[player].moveToPile(stacks[stackToAdd], stacksCount[stackToAdd]); 
                    stacksCount[stackToAdd] = 0;
                }
                if (stacksCount[stackToAdd] == 5) { // full
                    // this will also empty stacks[stackToAdd]
                    players[player].moveToPile(stacks[stackToAdd], stacksCount[stackToAdd]); 

                    stacksCount[stackToAdd] = 0;
                }
                stacks[stackToAdd][stacksCount[stackToAdd]++] = cardToPlace;
                lastPlacedCard = cardToPlace.getNumber();
            }
        }
        for (Player p : players) {
            System.out.println(p.getName() + " has a score of " + p.getScore());
            p.printPile();
        }
    }

    /**
     * Programme main. No use
     * 
     * @param args - no use.
     */
    public static void main(String[] args) {
        new Table().runApp();
    }

    // private helper method by Kevin
    private Card[] generateCard() {
        Card[] cards = new Card[TOTAL_NUMBER_OF_CARD];
        for (int i = 1; i < TOTAL_NUMBER_OF_CARD + 1; i++) {
            cards[i - 1] = new Card(i);
        }
        return cards;
    }

    // private helper method by Kevin
    private int getScore(int index) {
        int sum = 0;
        for (int i = 0; i < stacksCount[index]; i++)
            sum += stacks[index][i].getBullHead();
        return sum;
    }

}
