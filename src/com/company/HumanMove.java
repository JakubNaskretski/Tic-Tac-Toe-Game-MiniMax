package com.company;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HumanMove extends Thread implements MouseListener  {

    private MainView mainView;

    private int score;

    private final int gamePanelWidth;
    private final int gamePanelHeight;
    private final int dividedWidth;
    private final int dividedHeight;
    private final DrawO drawO;
    private final DrawX drawX;
    private int[][][] gameFieldPointsList;
    private String[][] gameBoard;
    private int[] moveToDraw = new int[2];

    private JPanel gameFieldPanel;
    private String whichPlayerDraw;

    public HumanMove(MainView mainView, int[][][] gameFieldPointsList, JPanel gameFieldPanel, DrawO drawO, DrawX drawX, String whichPlayerDraw, String[][] gameBoard) {

        int score = 0;

        this.mainView = mainView;
        this.gameFieldPointsList = gameFieldPointsList;
            this.gameFieldPanel =  gameFieldPanel;
            this.drawO = drawO;
            this.drawX = drawX;
            this.whichPlayerDraw =  whichPlayerDraw;
            this.gameBoard = gameBoard;

            java.awt.Rectangle r = gameFieldPanel.getBounds();
            this.gamePanelWidth = r.width;
            this.gamePanelHeight = r.height;
            this.dividedWidth = gamePanelWidth / 3;
            this.dividedHeight = gamePanelHeight / 3;
        }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (mainView.getMovesLeft() != 0) {

            this.gameBoard = mainView.getGameBoard();
            this.whichPlayerDraw = mainView.getWhosMove();
            int mouseX = gameFieldPanel.getMousePosition().x;
            int mouseY = gameFieldPanel.getMousePosition().y;

            if (mouseX >= 0 && mouseX <= dividedWidth) {
                if (mouseY >= 0 && mouseY <= dividedHeight) {
                    System.out.println(1);
                    moveToDraw[0] = 0;
                    moveToDraw[1] = 0;
                    drawPeopleMove(0, 0);
                } else if (mouseY >= dividedHeight && mouseY <= dividedHeight * 2) {
                    System.out.println(4);
                    moveToDraw[0] = 1;
                    moveToDraw[1] = 0;
                    drawPeopleMove(1, 0);
//                    drawMoveAIGame(1,0);
                } else if (mouseY >= dividedHeight * 2 && mouseY <= gamePanelHeight) {
                    System.out.println(7);
                    moveToDraw[0] = 2;
                    moveToDraw[1] = 0;
                    drawPeopleMove(2, 0);
//                    drawMoveAIGame(2,0);
                }
            } else if (mouseX >= dividedWidth && mouseX <= dividedWidth * 2) {
                if (mouseY >= 0 && mouseY <= dividedHeight) {
                    System.out.println(2);
                    moveToDraw[0] = 0;
                    moveToDraw[1] = 1;
                    drawPeopleMove(0, 1);
//                    drawMoveAIGame(0,1);
                } else if (mouseY >= dividedHeight && mouseY <= dividedHeight * 2) {
                    System.out.println(5);
                    moveToDraw[0] = 1;
                    moveToDraw[1] = 1;
                    drawPeopleMove(1, 1);
//                    drawMoveAIGame(1,1);
                } else if (mouseY >= dividedHeight * 2 && mouseY <= gamePanelHeight) {
                    System.out.println(8);
                    moveToDraw[0] = 2;
                    moveToDraw[1] = 1;
                    drawPeopleMove(2, 1);
//                    drawMoveAIGame(2,1);
                }
            } else if (mouseX >= dividedWidth * 2 && mouseX <= gamePanelWidth) {
                if (mouseY >= 0 && mouseY <= dividedHeight) {
                    System.out.println(3);
                    moveToDraw[0] = 1;
                    moveToDraw[1] = 2;
                    ;
                    drawPeopleMove(0, 2);
//                    drawMoveAIGame(0,2);
                } else if (mouseY >= dividedHeight && mouseY <= dividedHeight * 2) {
                    System.out.println(6);
                    moveToDraw[0] = 1;
                    moveToDraw[1] = 2;
                    drawPeopleMove(1, 2);
//                    drawMoveAIGame(1,2);
                } else if (mouseY >= dividedHeight * 2 && mouseY <= gamePanelHeight) {
                    System.out.println(9);
                    moveToDraw[0] = 2;
                    moveToDraw[1] = 2;
                    drawPeopleMove(2, 2);
//                    drawMoveAIGame(2,2);
                }
            }
        }
    }

    public synchronized void drawPeopleMove(int yPoint, int xPoint) {
//        Playing vs human

        System.out.println(gameBoard[yPoint][xPoint]);

        if (mainView.getGameType() == 1) {

//        Checks if can make move in this field
            if (gameBoard[yPoint][xPoint] == "") {
                System.out.println("Move is from inside " + whichPlayerDraw);
//            Checks if x move now
                if (whichPlayerDraw == "X") {
                    gameBoard[yPoint][xPoint] = "X";
                    drawX.addMarkX(gameFieldPointsList[yPoint][xPoint][0], gameFieldPointsList[yPoint][xPoint][1], gameFieldPointsList[yPoint + 1][xPoint + 1][0], gameFieldPointsList[yPoint + 1][xPoint + 1][1]);
                    mainView.setGameBoard(gameBoard);
                    mainView.setWhosMove("O");
                } else if (whichPlayerDraw == "O") {
                    gameBoard[yPoint][xPoint] = "O";
                    drawO.addMarkO(gameFieldPointsList[yPoint][xPoint][0], gameFieldPointsList[yPoint][xPoint][1], dividedWidth, dividedHeight);
                    mainView.setGameBoard(gameBoard);
                    mainView.setWhosMove("X");
                }
                if (mainView.checkIfWin(gameBoard) == "X") {
                    mainView.setMovesLeft(0);
                    new WinPopUpView("Congratulations", "X have won", mainView);
                } else if (mainView.checkIfWin(gameBoard) == "O") {
                    new WinPopUpView("Congratulations", "O have won", mainView);
                    mainView.setMovesLeft(0);
                } else if (mainView.checkIfWin(gameBoard) == "TIE") {
                    new WinPopUpView("Congratulations", "There have been tie", mainView);
                    mainView.setMovesLeft(0);
                }
            }
        } else if (mainView.getGameType() == 2) {
            if (whichPlayerDraw == "O") {
                if (gameBoard[yPoint][xPoint] == "") {
                    gameBoard[yPoint][xPoint] = "O";
                    drawO.addMarkO(gameFieldPointsList[yPoint][xPoint][0], gameFieldPointsList[yPoint][xPoint][1], dividedWidth, dividedHeight);
                    mainView.setGameBoard(gameBoard);
//                    if (mainView.checkIfWin(gameBoard) == "O") {
//                        mainView.setMovesLeft(0);
//                        new WinPopUpView("Congratulations", "O have won", mainView);
//                    }
                    mainView.setWhosMove("X");
                }
            }
        }
    }



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
