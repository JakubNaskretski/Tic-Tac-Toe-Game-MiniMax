package com.company;

import javax.swing.*;
import java.awt.*;

public class WinPopUpView {

    private int screenHeight, screenWidth;
    private JFrame frame;
    private MainView mainView;
    private JPanel mainPanel;
    private JButton startNewGame;

    private String textToDisplay1, textToDisplay2;

    private JLabel congratsLabel1, congratsLabel2;


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public WinPopUpView(String textToDisplay1, String textToDisplay2, MainView mainView) {
        this.textToDisplay1 = textToDisplay1;
        this.textToDisplay2 = textToDisplay2;
        this.mainView = mainView;

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

        startNewGame.addActionListener(e -> {
            mainView.getFrame().dispose();
            this.frame.dispose();
            new InitialView();
        });
    }

    protected void addComponentsToPane() {
        // Setting up elements and layout
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        congratsLabel1 = new JLabel(textToDisplay1);
        congratsLabel1.setFont(new Font("serif", Font.BOLD, 25));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(congratsLabel1, c);

        congratsLabel2 = new JLabel(textToDisplay2);
        congratsLabel2.setFont(new Font("serif", Font.BOLD, 25));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(congratsLabel2, c);


        startNewGame = new JButton("Start new game");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        mainPanel.add(startNewGame, c);
//
        frame.add(mainPanel);
    }

}
