package com.company;
import javax.swing.*;
import java.awt.*;

public class MainView {

    private int screenHeight, screenWidth, gameType, movesLeft;

    private JFrame frame;
    private JPanel mainPanel, gameFieldPanel;
    MainView currentMainView;
    HumanMove humanMove, oHumanMove;

    private GameField gameField;
    private String whosMove;
    private DrawX drawX;
    private DrawO drawO;

    private String[][] gameBoard = {{"","",""},{"","",""},{"","",""}};

    public MainView(int gameType) {

        this.currentMainView = this;

//        Gets info if game should be with human or AI
        this.gameType = gameType;
        this.movesLeft = 9;

        // Determining screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        screenHeight = (int) screenSize.getHeight();
        screenWidth = (int) screenSize.getWidth();

//      Creating frame
        frame = new JFrame();
        addComponentsToPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tic Tac Toe Game");
        frame.setLocationRelativeTo(null);
        frame.setLocation(((screenWidth / 2) - (screenWidth / 4)), ((screenHeight / 2) - (screenHeight / 4)));


        //      Display frame
        frame.pack();
        frame.setVisible(true);


//        Creating game field
        gameField.createGameField(gameFieldPanel);

            if (gameType == 1) {

                humanMove = new HumanMove(this, gameField.getPointsList(), gameFieldPanel, drawO, drawX, "X", gameBoard);
                gameFieldPanel.addMouseListener(humanMove);
                
                whosMove = "X";

         } else if (gameType == 2) {

                humanMove = new HumanMove(this, gameField.getPointsList(), gameFieldPanel, drawO, drawX, "X", gameBoard);

                whosMove = "X";

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        gameFieldPanel.addMouseListener(humanMove);
                        while (movesLeft != 0) {
                            if (whosMove == "X") {
                                int[] moves = makeComputerBestMove(gameBoard);
                                drawX.addMarkX(gameField.getPointsList()[moves[0]][moves[1]][0], gameField.getPointsList()[moves[0]][moves[1]][1], gameField.getPointsList()[moves[0] + 1][moves[1] + 1][0], gameField.getPointsList()[moves[0] + 1][moves[1] + 1][1]);
                                gameBoard[moves[0]][moves[1]] = "X";
                                if (checkIfWin(gameBoard) == "X") {
                                    movesLeft = 0;
                                    new WinPopUpView("Congratulations", "X have won", currentMainView);
                                } else if (checkIfWin(gameBoard) == "TIE") {
                                    movesLeft = 0;
                                    new WinPopUpView("Congratulations", "There have been tie", currentMainView);
                                }
                            } else if (whosMove == "O") {
                                if (checkIfWin(gameBoard) == "O") {
                                    movesLeft = 0;
                                    new WinPopUpView("Congratulations", "O have won", currentMainView);
                                } else if (checkIfWin(gameBoard) == "TIE") {
                                    movesLeft = 0;
                                    new WinPopUpView("Congratulations", "There have been tie", currentMainView);
                                }
                            }
                        }
                    }
                });
                        t.start();
            }

        }

    protected void addComponentsToPane() {
        // Setting up elements and layout
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        gameField = new GameField(this);
        gameField.setPreferredSize(new Dimension(screenWidth / 3, screenHeight / 2));
        gameField.setBorder(BorderFactory.createLineBorder(Color.black));


        gameFieldPanel = new JPanel();
        LayoutManager overlay = new OverlayLayout(gameFieldPanel);
        gameFieldPanel.setLayout(overlay);
        gameFieldPanel.setBackground(Color.WHITE);
        gameFieldPanel.add(gameField);

        drawX = new DrawX();
        gameFieldPanel.add(drawX);

        drawO = new DrawO();
        gameFieldPanel.add(drawO);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(gameFieldPanel, c);

        frame.add(mainPanel);
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
            }
            return "";
        }
            return "TIE";
    }

    public void winningPopUp(){
        if (checkIfWin(gameBoard) == "X") {
            new WinPopUpView("Congratulations", "X have won", currentMainView);
        } else if (checkIfWin(gameBoard) == "O") {
            new WinPopUpView("Congratulations", "O have won", currentMainView);
        } else if (checkIfWin(gameBoard) == "TIE") {
            new WinPopUpView("Congratulations", "There have been tie", currentMainView);
        }
    }

    public synchronized int[] makeComputerBestMove(String[][] board) {

        String[][] gameBoardInternal = board;
        int bestScoreFromMove = -1;
        int[] move = new int[2];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameBoardInternal[i][j] == "") {
                        gameBoardInternal[i][j] = "X";
                        int score = minimax(gameBoardInternal, 0, false);
                        System.out.println("rest score " + score);
                        gameBoardInternal[i][j] = "";
                        if (score > bestScoreFromMove) {
                            bestScoreFromMove = score;
                            move[0] = i;
                            move[1] = j;
                        }
                    }
                }
            }
            this.whosMove = "O";
            return move;
        }
//    }

    public synchronized int minimax(String[][] gameBoard, int depth, boolean isMaximizing) {

        String result = checkIfWin(gameBoard);

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
            int bestScore = -1;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameBoard[i][j] == "") {
                        gameBoard[i][j] = "X";
                        int score = minimax(gameBoard, depth + 1, false);
                        gameBoard[i][j] = "";
                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;

        } else {
            int bestScore = 1;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameBoard[i][j] == "") {
                        gameBoard[i][j] = "O";
                        int score = minimax(gameBoard, depth + 1, true);
                        gameBoard[i][j] = "";
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    public String[][] getGameBoard() {
        return gameBoard;
    }

    public String getWhosMove() {
        return whosMove;
    }

    public synchronized void setWhosMove(String whosMove) {
        this.whosMove = whosMove;
    }

    public void setGameBoard(String[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int getGameType() {
        return gameType;
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }
}

