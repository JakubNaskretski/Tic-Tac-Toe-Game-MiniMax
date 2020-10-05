package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class DrawO extends JComponent {

    private static class MarkO {
        final int x1;
        final int y1;
        final int x2;
        final int y2;
        final Color color;

        public MarkO(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
    }

    private final LinkedList<MarkO> markOList = new LinkedList<MarkO>();

    public void addMarkO(int x1, int y1, int x2, int y2) {
        addMarkO(x1, y1, x2, y2, Color.CYAN);
    }

    public void addMarkO(int x1, int y1, int x2, int y2, Color color) {
        markOList.add(new MarkO(x1, y1, x2, y2, color));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (MarkO markO : markOList) {
//            TODO: Make changable color
            g2.setColor(markO.color);
//            g2.setStroke(new BasicStroke(10));
            g2.setStroke((new BasicStroke(15.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
            g2.drawOval((markO.x1+(markO.x2/6)), (markO.y1+(markO.y2/6)), markO.x2-(markO.x2/3), markO.y2-(markO.y2/3));
        }
    }

}
