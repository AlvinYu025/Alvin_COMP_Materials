import java.util.Scanner;

/**
 * @author: ______Dr. Kevin Wang, kevinw@comp.hkbu.edu.hk_________
 * 
 *          For the instruction of the assignment please refer to the assignment
 *          GitHub.
 * 
 *          Plagiarism is a serious offense and can be easily detected. Please
 *          don't share your code to your classmate even
 *          if they are threatening you with your friendship. If they don't have
 *          the ability to work on something that can compile,
 *          they would not be able to change your code to a state that we can't
 *          detect the act of plagiarism. For the first commit
 *          of plagiarism, regardless you shared your code or copied code from
 *          others, you will receive 0 with an addition of 5 mark
 *          penalty. If you commit plagiarism twice, your case will be presented
 *          in the exam board and you will receive a F directly.
 *
 *          If you cannot work out the logic of the assignment, simply contact
 *          us
 *          on Piazza. The teaching team is more the eager to provide
 *          you help. We can extend your submission due if it is really
 *          necessary. Just please, don't give up.
 */
public class Connect4 {

    /**
     * Total number of rows of the game board. Use this constant whenever possible.
     */
    public static final int HEIGHT = 6;
    /**
     * Total number of columns of the game board. Use this constant whenever
     * possible.
     */
    public static final int WIDTH = 8;

    /**
     * Your main program. You don't need to change this part. This has been done for
     * you.
     */
    public static void main(String[] args) {
        new Connect4().runOnce();
    }

    /**
     * Your program entry. You don't need to change this part. This has been done
     * for you.
     */
    void runOnce() {
        // For people who are not familiar with constants - HEIGHT and WIDTH are two
        // constants defined above.
        // These two constants are visible in the entire program. They cannot be further
        // modified, i.e., it is
        // impossible to write HEIGHT = HEIGHT + 1; or WIDTH = 0; anywhere in your code.
        // However, you can use
        // these two constants as a reference, i.e., row = HEIGHT - 1, for example.

        int board[][] = new int[HEIGHT][WIDTH];
        char symbols[] = { '1', '2' };
        int player = 1;
        printBoard(board, symbols);

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!isGameOver(board) && !quit) {
            System.out.println("Player " + player + ", please enter a command. Press 'h' for help");
            char s = scanner.next().charAt(0);
            switch (s) {
                case 'h':
                case 'H':
                    printHelpMenu();
                    break;
                case 'c':
                case 'C':
                    changeSymbol(player, symbols);
                    break;
                case 'q':
                case 'Q':
                    quit = true;
                    System.out.println("Bye~");
                    continue;
                case 'r':
                    restart(board);
                    printBoard(board, symbols);
                    continue;
                default:
                    if (!validate(s, board)) {
                        System.out.println("Wrong input!, please do again");
                        continue;
                    }

                    // TODO
                    // convert the char 's' to the integer 'column', with the value 0 to 7
                    int column = s - '0';

                    fillBoard(board, column, player);
                    printBoard(board, symbols);
                    if (isGameOver(board)) {
                        System.out.println("Player " + player + ", you win!");
                        break;
                    } else if (checkMate(player, board))
                        System.out.println("Check mate!");
                    else if (check(player, board))
                        System.out.println("Check!");

                    // TODO: after each iteration, change the variable "turn" alternatively between
                    // the integers 1 and 2.
                    player = player == 1 ? 2 : 1;

            } // end switch
        } // end while
    }

    /**
     * Reset the board to the initial state
     * 
     * @param board - the game board array
     */
    void restart(int[][] board) {
        for (int r = 0; r < HEIGHT; r++)
            for (int c = 0; c < WIDTH; c++)
                board[r][c] = 0;
    }

    /**
     * It allows a player to choose a new symbol to represents its chess.
     * This method should ask the player to enter a new symbol so that symbol is not
     * the same as its opponent. Otherwise the player will need to enter it again
     * until they are different.
     * 
     * @param player  - the player who is about to change its symbol
     * @param symbols - the symbols array storing the players' symbols.
     */
    void changeSymbol(int player, char[] symbols) {
        Scanner scanner = new Scanner(System.in);
        char opponentSymbol = symbols[(player == 1) ? 1 : 0];
        while (true) {
            System.out.println("Enter the new symbol:");
            char s = scanner.next().charAt(0);
            if (s == opponentSymbol)
                continue;
            symbols[player - 1] = s;
            break;
        }
    }

    /**
     * Kevin's helper method
     * @param board
     * @return clone the board.
     */
    int[][] cloneArray(int[][] board) {
        int[][] clone = new int[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                clone[i][j] = board[i][j];
        return clone;
    }

    /**
     * This method returns true if the player "player" plays immediately, he/she may
     * end the game. This warns the other player to place his/her next block in a
     * correct position.
     * 
     * 
     * @param player - the player who is about to win if the other player does not
     *               stop him
     * @param board  - the 2D array of the game board.
     * @return true if the player is about to win, false if the player is not.
     */
    boolean check(int player, int[][] board) {
        // TODO
        for (int col = 0; col < WIDTH; col++) {
            int[][] clone = cloneArray(board);
            if (clone[0][col] == 0)
                fillBoard(clone, col, player);
            if (isGameOver(clone))
                return true;
        }
        return false;
    }




    /**
     * This method is very similar to the method check. However, a check-mate move
     * means no matter how the other player place his/her next block, in the next
     * turn the player can win the game with certain move.
     * 
     * A check-mate move must be a check move. Not all check moves are check-mate
     * move.
     * 
     * @param player - the player who is about to win no matter what the other
     *               player does
     * @param board  - the 2D array of the game board/
     * @return true if the player is about to win
     */
    boolean checkMate(int player, int[][] board) {
        // TODO


        int oppturn = (player == 1 ? 2 : 1);

        if (!check(player, board)) // checkMate implies check.
            return false;

        if (check(oppturn, board)) // if his opponent can win, it is not checkMate.
            return false;

        boolean checkmate = true;
        for (int col = 0; col < WIDTH; col++) {
            int[][] clone = cloneArray(board);
            if (clone[0][col] == 0)
                fillBoard(clone, col, oppturn);
            if (!check(player, clone))
                checkmate = false;
        }
        return checkmate;
    }

    /**
     * Validate if the input is valid. This input should be one of the character
     * '0', '1', '2', '3,' ..., '7'. The column corresponding to that input should
     * not be full.
     * 
     * @param input - the character of the column that the block is intended to
     *              place
     * @param board - the game board
     * @return - true if it is valid, false if it is invalid (e.g., '8', 'c', '@',
     *         EOT (which has an unicode 4) )
     */
    boolean validate(char input, int[][] board) {
        int s = input - '0';
        if (s < 0 || s >= WIDTH || board[0][s] != 0) {
            return false;
        }
        return true;
    }

    /**
     * Given the column (in integer) that a player wish to place his/her block,
     * update the gameboard. You may assume that the input has been validated before
     * calling this method, i.e., there always has room to place the block when
     * calling this method.
     * 
     * @param board  - the game board
     * @param column - the column that the player want to places its block
     * @param player - 1 or 2, the player.
     */
    void fillBoard(int[][] board, int column, int player) {
        int i;
        for (i = HEIGHT - 1; i >= 0 && board[i][column] != 0; i--)
            ;
        board[i][column] = player;
        return;
    }

    /**
     * Print the Help Menu. Please try to understand the switch case in runOnce and
     * Provide a one line comment about the purpose of each symbol.
     */
    void printHelpMenu() {
        System.out.println("Help Menu:");
        System.out.println("------------------");
        System.out.println("h/H\t\tPrint Help Menu");
        System.out.println("This is part of the assignment, please complete it on your own...");
        // System.out.println("c/C\t\tChange symbol");
        // System.out.println("q/Q\t\tQuit the program");
        // System.out.println("r\t\tRestart the game board");
        // System.out.println("0-7\t\tPlace the block");

    }

    /**
     * Determine if the game is over. Game is over if and only if one of the player
     * has a connect-4 or the entire gameboard is fully filled.
     * 
     * @param board - the game board
     * @return - true if the game is over, false other wise.
     */
    boolean isGameOver(int[][] board) {
        boolean full = true;
        for (int r = 0; r < HEIGHT; r++) {
            for (int c = 0; c < WIDTH; c++) {
                if (board[r][c] == 0) {
                    full = false;
                    continue;
                }
                if (c < WIDTH - 3 &&
                        board[r][c] == board[r][c + 1] &&
                        board[r][c] == board[r][c + 2] &&
                        board[r][c] == board[r][c + 3])
                    return true;
                if (r < HEIGHT - 3 &&
                        board[r][c] == board[r + 1][c] &&
                        board[r][c] == board[r + 2][c] &&
                        board[r][c] == board[r + 3][c])
                    return true;
                if (r < HEIGHT - 3 && c < WIDTH - 3 &&
                        board[r][c] == board[r + 1][c + 1] &&
                        board[r][c] == board[r + 2][c + 2] &&
                        board[r][c] == board[r + 3][c + 3])
                    return true;
                if (r < HEIGHT - 3 && c >= 3 &&
                        board[r][c] == board[r + 1][c - 1] &&
                        board[r][c] == board[r + 2][c - 2] &&
                        board[r][c] == board[r + 3][c - 3])
                    return true;

            }
        }
        return full;
    }

    /**
     * Print the game board in a particular format. The instruction can be referred
     * to the GitHub or the demo program. By default, Player 1 uses the character
     * '1' to represent its block. Player 2 uses the character '2'. They can be
     * overrided by the value of symbols array. This method does not change the
     * value of the gameboard nor the symbols array.
     *
     * @param board   - the game board to be printed.
     * @param symbols - the symbols that represents player 1 and player 2.
     */
    void printBoard(int[][] board, char[] symbols) {
        System.out.print(' ');
        for (int c = 0; c < WIDTH; c++) {
            System.out.print(c);
        }
        System.out.println();
        System.out.println(" --------");
        for (int r = 0; r < HEIGHT; r++) {
            System.out.print('|');
            for (int c = 0; c < WIDTH; c++) {
                System.out.print(board[r][c] == 0 ? ' ' : (board[r][c] == 1 ? symbols[0] : symbols[1]));
            }
            System.out.println('|');
        }
        System.out.println(" --------");
    }

}
