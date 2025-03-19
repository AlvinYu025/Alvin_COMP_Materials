import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCase {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Connect4 game = new Connect4();
        try{
            list.add(testCheck(game));
        }
        catch (Exception e){
            list.add(0);
            System.out.println("Test check failed");
        }
        try{
            list.add(testCheckmate(game));
        }
        catch (Exception e){
            list.add(0);
            System.out.println("Test checkmate failed");
        }
        try{
            list.add(testValidate(game));
        }
        catch (Exception e){
            list.add(0);
            System.out.println("Test validate failed");
        }
        try{
            list.add(testRestart(game));
        }
        catch (Exception e){
            list.add(0);
            System.out.println("Test restart failed");
        }
        try{
            list.add(testFillBoard(game));
        }
        catch (Exception e){
            list.add(0);
            System.out.println("Test fill board failed");
        }
        try{
            list.add(testIsGameOver(game));
        }
        catch (Exception e){
            list.add(0);
            System.out.println("Test is game over failed");
        }
        try{
            testPrintBoard(game);
        }
        catch (Exception e){
            list.add(0);
            System.out.println("Test print board failed");
        }
        try{
            game.printHelpMenu();
            System.out.println();
        }
        catch (Exception e){
            list.add(0);
            System.out.println("Test print help menu failed");
        }
        try{
            testChangeSymbol(game);
            System.out.println();
        }
        catch (Exception e){
            
            System.out.println("Test change symbol failed");
        }
        
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

    }

    /**
     * test the method check
     * 
     */
    static int testCheck(Connect4 game) {
        // The following are positive tests
        // a test board that player 1 is in check
        int[][] testBoard01 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 1, 1, 0, 0, 0, 0, 0 } };
        int[][] testBoard02 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 } };
        int[][] testBoard03 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 2, 2, 1 } };
        int[][] testBoard04 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 2, 2, 0, 0, 0, 0, 0 } };
        int[][] testBoard05 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 2, 2, 1, 0 },
                { 0, 0, 0, 0, 1, 2, 2, 1 } };
        int[][] testBoard06 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 2, 2, 1 },
                { 0, 0, 0, 0, 0, 1, 1, 2 },
                { 0, 0, 0, 0, 0, 2, 1, 2 } };
        int[][] testBoard07 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 2, 2, 0, 0, 0, 0, 0 },
                { 2, 1, 1, 0, 0, 0, 0, 0 },
                { 2, 1, 2, 0, 0, 0, 0, 0 } };

        // The following are negative tests
        // a test board that player 1 is not in check
        int[][] testBoard08 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 1, 0, 0, 0, 0, 0, 0 } };
        int[][] testBoard09 = { { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0, 0 } };
        int[][] testBoard10 = { { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 2, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0, 0 },
                { 2, 1, 2, 0, 0, 0, 0, 0 } };
        int[][] testBoard11 = { { 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 2 },
                { 0, 0, 0, 0, 0, 0, 2, 1 },
                { 0, 0, 0, 0, 0, 2, 1, 2 } };

        int[][][] positiveTests = { testBoard01, testBoard02, testBoard03, testBoard04, testBoard05, testBoard06,
                testBoard07 };
        int[][][] negativeTests = { testBoard08, testBoard09, testBoard10, testBoard11 };

        System.out.println("Test case for check:");
        // Test positive tests
        int score = 0;
        for (int i = 0; i < positiveTests.length; i++) {
            try {
                if (game.check(1, positiveTests[i])) {
                    score++;
                } else {
                    System.out.println("Positive test case " + i + " failed.");
                    printBoard(positiveTests[i], new char[] { '1', '2' });
                }
            } catch (Exception e) {
                System.out.println("Positive test case " + i + " failed.");
                System.out.println("Exception: " + e.getMessage());
                printBoard(positiveTests[i], new char[] { '1', '2' });
            }
            
        }

        // Test negative tests
        for (int i = 0; i < negativeTests.length; i++) {
            try {
                if (!game.check(1, negativeTests[i])) {
                    score++;
                } else {
                    System.out.println("Negative test case " + i + " failed.");
                    printBoard(negativeTests[i], new char[] { '1', '2' });
                }
            } catch (Exception e) {
                System.out.println("Negative test case " + i + " failed.");
                System.out.println("Exception: " + e.getMessage());
                printBoard(negativeTests[i], new char[] { '1', '2' });
            }

        }

        System.out.println("Test case for checkmate:");
        System.out.println("Score: " + score + "/" + (positiveTests.length + negativeTests.length));
        return score;
    }

    /**
     * Test case for checkmate
     * @param game the game object
     * @return the score of the test case
     */
    static int testCheckmate(Connect4 game){
        // The following are positive tests
        // a test board that player 1 is in checkmate
        int[][] testBoard01 = {{0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {1, 2, 2, 0, 0, 0, 0, 0},
                             {1, 2, 2, 0, 0, 0, 0, 0},
                             {1, 1, 1, 0, 0, 0, 0, 0}};
        // testBoard02 is the mirror image of testBoard01
        int[][] testBoard02 = {{0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 2, 2, 1},
                             {0, 0, 0, 0, 0, 2, 2, 1},
                             {0, 0, 0, 0, 0, 1, 1, 1}};

        int[][] testBoard03 = {{0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {2, 0, 0, 0, 0, 0, 0, 0},
                             {2, 2, 0, 0, 0, 0, 0, 0},
                             {1, 1, 1, 0, 2, 0, 0, 0},
                             {1, 1, 1, 0, 2, 0, 0, 0}};
        //textBoard04 is the mirror image of testBoard03
        int[][] testBoard04 = {{0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 2},
                             {0, 0, 0, 0, 0, 0, 2, 2},
                             {0, 0, 0, 2, 0, 1, 1, 1},
                             {0, 0, 0, 2, 0, 1, 1, 1}};

        int[][] testBoard05 = {{0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 1, 1, 2},
                             {0, 0, 0, 2, 0, 1, 2, 1},
                             {2, 0, 0, 2, 1, 1, 1, 2}};
        // testBoard06 is the mirror image of testBoard05
        int[][] testBoard06 = {{0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {2, 1, 1, 0, 0, 0, 0, 0},
                             {1, 2, 1, 0, 2, 0, 0, 0},
                             {2, 1, 1, 1, 2, 0, 0, 2}};

        //negative tests

        int[][] testBoard07 = {{0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 1},
                             {0, 0, 0, 0, 0, 1, 1, 2},
                             {2, 0, 0, 0, 2, 1, 2, 1},
                             {2, 0, 0, 2, 2, 1, 1, 1}};
        // testBoard08 is the mirror image of testBoard07
        int[][] testBoard08 = {{0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {1, 0, 0, 0, 0, 0, 0, 0},
                             {2, 1, 1, 0, 0, 0, 0, 0},
                             {1, 2, 1, 2, 0, 0, 0, 2},
                             {1, 1, 1, 2, 2, 0, 0, 2}};
        int[][] testBoard09 = {{0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {1, 0, 0, 0, 0, 1, 1, 1},
                             {2, 2, 0, 2, 0, 1, 2, 1},
                             {2, 2, 0, 2, 1, 1, 1, 2}};
        // testBoard10 is the mirror image of testBoard09
        int[][] testBoard10 = {{0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {1, 1, 1, 0, 0, 0, 0, 1},
                             {1, 2, 1, 0, 2, 0, 2, 2},
                             {2, 1, 1, 1, 2, 0, 2, 2}};
        // testBoard11 is a blank board
        int[][] testBoard11 = {{0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0, 0, 0, 0}};
        
        int score = 0;

        int[][][] positiveTests = {testBoard01, testBoard02, testBoard03, testBoard04, testBoard05, testBoard06};
        int[][][] negativeTests = {testBoard07, testBoard08, testBoard09, testBoard10, testBoard11};

        System.out.println("Test case for checkmate:");
        // Test positive tests
        for (int i = 0; i < positiveTests.length; i++) {
            try {
                if (game.checkMate(1, positiveTests[i])) {
                    score++;
                } else {
                    System.out.println("Positive test case " + i + " failed.");
                    printBoard(positiveTests[i], new char[] { '1', '2' });
                }
            } catch (Exception e) {
                System.out.println("Positive test case " + i + " failed.");
                System.out.println("Exception: " + e);
                printBoard(positiveTests[i], new char[] { '1', '2' });
            }

        }

        // Test negative tests
        for (int i = 0; i < negativeTests.length; i++) {
            try {
                if (!game.checkMate(1, negativeTests[i])) {
                    score++;
                } else {
                    System.out.println("Negative test case " + i + " failed.");
                    printBoard(negativeTests[i], new char[] { '1', '2' });
                }
            } catch (Exception e) {
                System.out.println("Negative test case " + i + " failed.");
                System.out.println("Exception: " + e);
                printBoard(negativeTests[i], new char[] { '1', '2' });
            }

        }

        System.out.println("Test case for checkmate: ");
        System.out.println("Score: " + score + "/" + (positiveTests.length + negativeTests.length));

        return score;
    }

	static int testValidate(Connect4 game){
		int score = 8;
		System.out.println("Test case for validate:");
		for(char i = '0'; i <= '7'; i++){
            try {
                if(!game.validate(i, new int[Connect4.HEIGHT][Connect4.WIDTH])){
                    score--;
                    System.out.println("Positive test case " + i + " failed.");
                }
            } catch (Exception e) {
                score--;
                System.out.println("Positive test case " + i + " failed.");
                System.out.println("Exception: " + e);
            }
			
		}
		char[] negativeTests = {'8', 'a', 'A', '!', ' ', '@', 'c', '\u0004'};

        int[][] testBoard01 = new int[Connect4.HEIGHT][Connect4.WIDTH];
        for (int i = 0; i < Connect4.HEIGHT; i++) {
            for (int j = 0; j < Connect4.WIDTH; j++) {
                testBoard01[i][j] = 1;
            }
        }

		for(int i = 0; i < negativeTests.length; i++){
            try {
                if(!game.validate(negativeTests[i], new int[Connect4.HEIGHT][Connect4.WIDTH])){
                    score++;
                }
                else{
                    System.out.println("Negative test case " + i + " failed.");
                }
            } catch (Exception e) {
                System.out.println("Negative test case " + i + " failed.");
                System.out.println("Exception: " + e);
            }

		}

        try {
            if (!game.validate('0', testBoard01)) {
                score++;
            } else {
                System.out.println("Test failed for full board");
            }
        } catch (Exception e) {
            System.out.println("Test failed for full board");
            System.out.println("Exception: " + e);
        }

        System.out.println("Test case for validate: ");
		System.out.println("Score: " + score + "/17");
		
		return score;
	}

	static int testRestart(Connect4 game){

		System.out.println("Test case for restart:");
		int[][] board = new int[Connect4.HEIGHT][Connect4.WIDTH];
		for(int i = 0; i < Connect4.HEIGHT; i++){
			for(int j = 0; j < Connect4.WIDTH; j++){
				board[i][j] = 1;
			}
		}

        boolean pass = true;
        try {
            game.restart(board);
            for(int i = 0; i < Connect4.HEIGHT; i++){
                for(int j = 0; j < Connect4.WIDTH; j++){
                    if(board[i][j] != 0){
                        pass = false;
                        break;
                    }
                }
                if(!pass){
                    break;
                }
            }
        } catch (Exception e) {
            pass = false;
            System.out.println("Exception: " + e);
        }

        System.out.println("Test case for restart:");
		System.out.println("Score: " + (pass ? 1 : 0) + "/1");
		return pass ? 1 : 0;
	}

	static void testPrintBoard(Connect4 game){
		System.out.println("Test case for printBoard:");
		int[][] board = new int[Connect4.HEIGHT][Connect4.WIDTH];
		// fill board with 1 and 2 alternatingly
		for(int i = 0; i < Connect4.HEIGHT; i++){
			for(int j = 0; j < Connect4.WIDTH; j++){
				board[i][j] = (i + j) % 2 + 1;
			}
		}

		int[][] board2 = new int[Connect4.HEIGHT][Connect4.WIDTH];
		// fill board with 'a' and 'b' alternatingly
		for(int i = 0; i < Connect4.HEIGHT; i++){
			for(int j = 0; j < Connect4.WIDTH; j++){
				board2[i][j] = (i + j) % 2 + 'a';
			}
		}

        try {
            game.printBoard(board, new char[]{'a', 'b'});
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
		

		String correct = " 01234567\n"
						+" --------\n"
						+"|abababab|\n"
						+"|babababa|\n"
						+"|abababab|\n"
						+"|babababa|\n"
						+"|abababab|\n"
						+"|babababa|\n"
						+" --------";
		System.out.println("correct: \n" + correct);
	}

	static void testChangeSymbol(Connect4 game){
		System.out.println("Test case for changeSymbol:");
		// new board filled with 1 and 2 alternatingly
		int[][] board = new int[Connect4.HEIGHT][Connect4.WIDTH];
		for(int i = 0; i < Connect4.HEIGHT; i++){
			for(int j = 0; j < Connect4.WIDTH; j++){
				board[i][j] = (i + j) % 2 + 1;
			}
		}

        try {
            char[] symbols = new char[]{'1', '2'};
            game.changeSymbol(1, symbols);
            game.changeSymbol(2, symbols);
            printBoard(board, symbols);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

	}

	static int testFillBoard(Connect4 game){
		System.out.println("Test case for fillBoard:");
		int[][] testBoard01 = {{0, 0, 0, 0, 0, 0, 0, 0},
							   {0, 0, 0, 0, 0, 0, 0, 0},
							   {0, 0, 0, 0, 0, 0, 0, 0},
							   {0, 0, 0, 0, 0, 0, 0, 0},
							   {0, 0, 0, 0, 0, 0, 0, 0},
							   {0, 0, 0, 0, 0, 0, 0, 0}};
		
		int[][] testBoard02 = {{0, 0, 0, 0, 0, 0, 0, 0},
							   {1, 0, 0, 0, 0, 0, 0, 0},
							   {2, 0, 0, 0, 0, 0, 0, 0},
							   {1, 0, 0, 0, 0, 0, 0, 0},
							   {2, 0, 0, 0, 0, 0, 0, 0},
							   {1, 2, 0, 0, 0, 0, 0, 0}};

		// 8 ans boards that has 1 on each column
		int[][][] ansBoards = new int[8][Connect4.HEIGHT][Connect4.WIDTH]; 

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < Connect4.HEIGHT; j++){
                for(int k = 0; k < Connect4.WIDTH; k++){
                    ansBoards[i][j][k] = j == 5 && k == i ? 1 : 0;
                }
            }
		}

        int score = 0;
        
		for(int i = 0; i < 8; i++){
            int[][] board = new int[Connect4.HEIGHT][Connect4.WIDTH];
            for(int j = 0; j < Connect4.HEIGHT; j++){
                for(int k = 0; k < Connect4.WIDTH; k++){
                    board[j][k] = testBoard01[j][k];
                }
            }
            try {
                game.fillBoard(board, i, 1);
                if(Arrays.deepEquals(board, ansBoards[i])){
                    score++;
                }else{
                    System.out.println("Test case " + i + " failed.");
                    printBoard(board, new char[]{'1', '2'});
                }
            } catch (Exception e) {
                System.out.println("Test case " + i + " failed.");
                System.out.println("Exception: " + e);
                printBoard(board, new char[]{'1', '2'});
            }   
        }

		int[][] ansBoard02 = {{1, 0, 0, 0, 0, 0, 0, 0},
							   {1, 0, 0, 0, 0, 0, 0, 0},
							   {2, 0, 0, 0, 0, 0, 0, 0},
							   {1, 0, 0, 0, 0, 0, 0, 0},
							   {2, 0, 0, 0, 0, 0, 0, 0},
							   {1, 2, 0, 0, 0, 0, 0, 0}};

        try {
            game.fillBoard(testBoard02, 0, 1);
            if(Arrays.deepEquals(testBoard02, ansBoard02)){
                score++;
            }else{
                System.out.println("Test case height failed.");
                printBoard(testBoard02, new char[]{'1', '2'});
            }
        } catch (Exception e) {
            System.out.println("Test case height failed.");
            System.out.println("Exception: " + e);
            printBoard(testBoard02, new char[]{'1', '2'});
        }

        System.out.println("Test case for fillBoard: ");
        System.out.println("Score: " + score + "/9");
        return score;
	}

    static int testIsGameOver(Connect4 game){
        // testBoard01 is a board with 1 and 2 alternatingly
        int[][] testBoard01 = {{1, 2, 1, 2, 1, 2, 1, 2},
                               {2, 1, 2, 1, 2, 1, 2, 1},
                               {1, 2, 1, 2, 1, 2, 1, 2},
                               {2, 1, 2, 1, 2, 1, 2, 1},
                               {1, 2, 1, 2, 1, 2, 1, 2},
                               {2, 1, 2, 1, 2, 1, 2, 1}};
        
        
        int[][] testBoard02 = {{0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0},
                               {1, 0, 0, 0, 0, 0, 0, 0},
                               {1, 2, 0, 0, 0, 0, 0, 0},
                               {1, 2, 0, 0, 0, 0, 0, 0},
                               {1, 2, 2, 0, 0, 0, 0, 0}};

        int[][] testBoard03 = {{1, 0, 0, 0, 0, 0, 0, 0},
                               {1, 0, 0, 0, 0, 0, 0, 0},
                               {1, 0, 0, 0, 0, 0, 0, 0},
                               {1, 0, 0, 0, 0, 0, 0, 0},
                               {2, 0, 0, 0, 0, 0, 0, 0},
                               {2, 2, 0, 0, 0, 0, 0, 0}};
        
        int[][] testBoard04 = {{0, 0, 0, 0, 0, 0, 0, 1},
                               {0, 0, 0, 0, 0, 0, 0, 1},
                               {0, 0, 0, 0, 0, 0, 0, 1},
                               {0, 0, 0, 0, 0, 0, 0, 1},
                               {0, 0, 0, 0, 0, 0, 0, 2},
                               {0, 0, 0, 0, 0, 0, 2, 2}};

        int[][] testBoard05 = {{1, 1, 1, 1, 0, 0, 0, 0},
                               {2, 2, 2, 1, 0, 0, 0, 0},
                               {1, 1, 1, 2, 0, 0, 0, 0},
                               {2, 2, 2, 1, 0, 0, 0, 0},
                               {1, 1, 1, 2, 0, 0, 0, 0},
                               {2, 2, 2, 1, 2, 0, 0, 0}};  
                               
        int[][] testBoard06 = {{0, 0, 0, 0, 1, 1, 1, 1},
                               {0, 0, 0, 0, 2, 2, 2, 1},
                               {0, 0, 0, 0, 1, 1, 1, 2},
                               {0, 0, 0, 0, 2, 2, 2, 1},
                               {0, 0, 0, 0, 1, 1, 1, 2},
                               {2, 0, 0, 0, 2, 2, 2, 1}}; 
                            
        int[][] testBoard07 = {{1, 1, 1, 2, 1, 1, 1, 2},
                               {2, 2, 2, 1, 2, 2, 2, 1},
                               {1, 1, 1, 2, 1, 1, 1, 2},
                               {2, 2, 2, 1, 2, 2, 2, 1},
                               {1, 1, 1, 2, 1, 1, 1, 2},
                               {2, 2, 2, 1, 2, 2, 2, 1}};

        int[][] testBoard07_1 ={{1, 0, 0, 0, 0, 0, 0, 0},
                                {1, 1, 0, 0, 0, 0, 0, 0},
                                {2, 2, 1, 0, 0, 0, 0, 0},
                                {1, 2, 2, 1, 0, 0, 0, 0},
                                {2, 1, 1, 2, 0, 0, 0, 0},
                                {2, 2, 2, 1, 0, 0, 0, 0}};
        

        int[][] testBoard07_2 ={{0, 0, 0, 0, 0, 0, 0, 1},
                                {0, 0, 0, 0, 0, 0, 1, 1},
                                {0, 0, 0, 0, 0, 1, 2, 2},
                                {0, 0, 0, 0, 1, 2, 2, 1},
                                {0, 0, 0, 0, 2, 1, 1, 2},
                                {0, 0, 0, 0, 1, 2, 2, 2}};

        int[][] testBoard07_3 ={{1, 0, 0, 0, 0, 0, 0, 0},
                                {1, 1, 0, 0, 0, 0, 0, 0},
                                {2, 2, 2, 2, 0, 0, 0, 0},
                                {1, 2, 2, 1, 0, 0, 0, 0},
                                {2, 1, 1, 2, 0, 0, 0, 0},
                                {2, 2, 2, 1, 0, 0, 0, 0}};
        

        int[][] testBoard07_4 ={{0, 0, 0, 0, 0, 0, 0, 1},
                                {0, 0, 0, 0, 0, 0, 1, 1},
                                {0, 0, 0, 0, 2, 2, 2, 2},
                                {0, 0, 0, 0, 1, 2, 2, 1},
                                {0, 0, 0, 0, 2, 1, 1, 2},
                                {0, 0, 0, 0, 1, 2, 2, 2}};
        // negative test cases

        int[][] testBoard08 = {{1, 1, 1, 2, 1, 1, 1, 0},
                               {2, 2, 2, 1, 2, 2, 2, 1},
                               {1, 1, 1, 2, 1, 1, 1, 2},
                               {2, 2, 2, 1, 2, 2, 2, 1},
                               {1, 1, 1, 2, 1, 1, 1, 2},
                               {2, 2, 2, 1, 2, 2, 2, 1}};
        
        int[][] testBoard09 = {{0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0},
                               {1, 2, 0, 0, 0, 0, 0, 0},
                               {1, 2, 0, 0, 0, 0, 0, 0},
                               {1, 2, 2, 0, 0, 0, 0, 0}};

        int[][] testBoard10 = {{0, 0, 0, 0, 0, 0, 0, 0},
                               {1, 0, 0, 0, 0, 0, 0, 0},
                               {1, 0, 0, 0, 0, 0, 0, 0},
                               {1, 0, 0, 0, 0, 0, 0, 0},
                               {2, 0, 0, 0, 0, 0, 0, 0},
                               {2, 2, 0, 0, 0, 0, 0, 0}};
        
        int[][] testBoard11 = {{0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 1},
                               {0, 0, 0, 0, 0, 0, 0, 1},
                               {0, 0, 0, 0, 0, 0, 0, 1},
                               {0, 0, 0, 0, 0, 0, 0, 2},
                               {0, 0, 0, 0, 0, 0, 2, 2}};

        int[][] testBoard12 = {{1, 1, 0, 1, 0, 0, 0, 0},
                               {2, 2, 2, 1, 0, 0, 0, 0},
                               {1, 1, 1, 2, 0, 0, 0, 0},
                               {2, 2, 2, 1, 0, 0, 0, 0},
                               {1, 1, 1, 2, 0, 0, 0, 0},
                               {2, 2, 2, 1, 2, 0, 0, 0}};  
                               
        int[][] testBoard13 = {{0, 0, 0, 0, 1, 0, 1, 1},
                               {0, 0, 0, 0, 2, 2, 2, 1},
                               {0, 0, 0, 0, 1, 1, 1, 2},
                               {0, 0, 0, 0, 2, 2, 2, 1},
                               {0, 0, 0, 0, 1, 1, 1, 2},
                               {2, 0, 0, 0, 2, 2, 2, 1}};
                               
        int[][][] positiveTestCases = {testBoard01, testBoard02, testBoard03, testBoard04, testBoard05, testBoard06, testBoard07, testBoard07_1, testBoard07_2, testBoard07_3, testBoard07_4};
        int[][][] negativeTestCases = {testBoard08, testBoard09, testBoard10, testBoard11, testBoard12, testBoard13};

        int score = 0;
        System.out.println("Test cases for the isGameOver method:");
        
        for (int i = 0; i < positiveTestCases.length; i++) {
            try {
                if (game.isGameOver(positiveTestCases[i])) {
                    score++;
                } else {
                    System.out.println("Test case " + (i + 1) + " failed.");
                    printBoard(positiveTestCases[i], new char[]{'1', '2'});
                }
            } catch (Exception e) {
                System.out.println("Test case " + (i + 1) + " failed.");
                System.out.println("Exception: " + e);
                printBoard(positiveTestCases[i], new char[]{'1', '2'});
            }
            
        }
        for (int i = 0; i < negativeTestCases.length; i++) {
            try {
                if (!game.isGameOver(negativeTestCases[i])) {
                    score++;
                } else {
                    System.out.println("Test case " + (i + 1) + " failed.");
                    printBoard(negativeTestCases[i], new char[]{'1', '2'});
                }
            } catch (Exception e) {
                System.out.println("Test case " + (i + 1) + " failed.");
                System.out.println("Exception: " + e);
                printBoard(negativeTestCases[i], new char[]{'1', '2'});
            }

        }

        System.out.println("Test for the isGameOver: ");
        System.out.println("Score: " + score + "/" + (positiveTestCases.length + negativeTestCases.length));
        return score;
    }

	static void printBoard(int[][] board, char[] symbols) {
        System.out.println(" 01234567");
        System.out.println(" --------");
        for (int i = 0; i < Connect4.HEIGHT; i++) {
            System.out.print("|");
            for (int j = 0; j < Connect4.WIDTH; j++) {
                System.out.print(board[i][j] == 0 ? " " : board[i][j] == 1 ? symbols[0] : symbols[1]);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println(" --------");
    }
}
