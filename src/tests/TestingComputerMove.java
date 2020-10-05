package tests;

public class TestingComputerMove {

    public int movesLeft;

    public TestingComputerMove() {
        this.movesLeft = 9;
    }

    public synchronized int[] makeComputerBestMove(String[][] board){


        String[][] gameBoard = board;
        int score;
//        String[][] gameBoarInternal = gameBoard;
        int bestScoreFromMove = 0;

        int[] move = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
//                System.out.println("Game board wewn "+gameBoard[i][j]);
                if (gameBoard[i][j] == "") {
                    gameBoard[i][j] = "X";
//                        System.out.println("funkcja minimax "+ minimax(gameBoarInternal, 0, false));
                    score = minimax(gameBoard, 0, false);
//                        System.out.println("rest score " + score);
                    gameBoard[i][j] = "";
//                    System.out.println("received best score " + score);
                    if (score > bestScoreFromMove) {
                        bestScoreFromMove = score;
                        move[0] = i;
                        move[1] = j;
//                        return move;
                    }
                }
            }
        }
//        scoreCounter.getBoard()[move[0]][move[1]] = 1;
//        this.whosMove = "O";
        return move;
    }

    public synchronized int minimax(String[][] gameBoard, int depth, boolean isMaximizing) {
//        return 1;
//    }
        int score;

        String[][] gameBoarInternal = gameBoard;

        String result = checkIfWin(gameBoarInternal);

        if (result != "") {
            if (result == "X") {
                return 1;
            } else if (result == "O") {
                return -1;
            } else if (result == "TIE"){
                return 0;
            }
        }

        if (isMaximizing) {
            int bestScore = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameBoarInternal[i][j] == "") {
                        gameBoarInternal[i][j] = "X";
                        score = minimax(gameBoarInternal, depth + 1, false);
                        gameBoarInternal[i][j] = "";
                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;

        } else {
            int bestScore = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameBoarInternal[i][j] == "") {
                        gameBoarInternal[i][j] = "O";
                        score = minimax(gameBoarInternal, depth + 1, true);
                        gameBoarInternal[i][j] = "";
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    public String checkIfWin(String[][] gameBoard){
        while (movesLeft != 0) {
            for (int i = 0; i < 3; i++) {
                String verticalSum = "";
                String sum = "";
                for (int j = 0; j < 3; j++) {
//                Counting if vertical sum is 3 in any row
                    verticalSum += gameBoard[j][i];
                    if (verticalSum.equals("XXX")) {
                        return "X";
                    } else if (verticalSum.equals("OOO")) {
                        return "O";
                    }
//                Counting if horizontal sum is 3 in any row
                    sum += gameBoard[i][j];
                    if (sum.equals("XXX")) {
                        return "X";
                    } else if (sum.equals("OOO")) {
                        return "O";
                    }
                }
                if ((gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2]).equals("XXX")) {
                    return "X";
                } else if ((gameBoard[0][2] + gameBoard[1][1] + gameBoard[2][0]).equals("XXX")) {
                    return "X";
                } else if ((gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2]).equals("OOO")) {
                    return "O";
                } else if ((gameBoard[0][2] + gameBoard[1][1] + gameBoard[2][0]).equals("OOO")) {
                    return "O";
                }
//        Dopisajeżeli skończą się ruchy to tie
            }
            return "";
        }
        return "TIE";
    }

}
