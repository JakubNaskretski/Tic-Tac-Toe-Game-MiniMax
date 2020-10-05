package com.company;

import javax.swing.*;
import java.awt.*;

public class InitialView {

    private int screenHeight, screenWidth;
    private JFrame frame;
    private JPanel mainPanel;
    private JButton otherPlayerGameButton, computerGameButton;

    private JLabel welcomeLabel1, welcomeLabel2;

    private MainView mainView;


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public InitialView() {

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

        otherPlayerGameButton.addActionListener(e -> {
            this.frame.dispose();
            mainView = new MainView(1);
        });

        computerGameButton.addActionListener(e -> {
            this.frame.dispose();
            mainView = new MainView(2);
        });
    }

    protected void addComponentsToPane() {
        // Setting up elements and layout
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        welcomeLabel1 = new JLabel(" Welcome in Tic Tac Toe Game ");
        welcomeLabel1.setFont(new Font("serif", Font.BOLD, 25));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(welcomeLabel1, c);

        welcomeLabel2 = new JLabel(" Who would you like to play with ? ");
        welcomeLabel2.setFont(new Font("serif", Font.BOLD, 25));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(welcomeLabel2, c);


        otherPlayerGameButton = new JButton("Player vs Player");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        mainPanel.add(otherPlayerGameButton, c);

        computerGameButton = new JButton("Player vs Computer");
        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 2;
        mainPanel.add(computerGameButton, c);
//
        frame.add(mainPanel);
    }


}
