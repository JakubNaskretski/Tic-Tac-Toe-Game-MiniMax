package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class DrawX extends JComponent {

    private static class MarkX {
        final int x1;
        final int y1;
        final int x2;
        final int y2;
        final Color color;

        public MarkX(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
    }

    private final LinkedList<MarkX> markXList = new LinkedList<MarkX>();

    public void addMarkX(int x1, int y1, int x2, int y2) {
        addMarkX(x1, y1, x2, y2, Color.RED);
    }

    public void addMarkX(int x1, int y1, int x2, int y2, Color color) {
        markXList.add(new MarkX(x1, y1, x2, y2, color));
        repaint();
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {

        try {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (MarkX markX : markXList) {
//            TODO: Make changable color
            g2.setColor(markX.color);
//            g2.setStroke(new BasicStroke(10));
            g2.setStroke((new BasicStroke(15.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
//            g2.drawLine(markX.x1, markX.y1, markX.x2, markX.y2);
            g2.drawLine((int)(markX.x1+25), (int)(markX.y1+25), markX.x2-25, markX.y2-25);

            g2.drawLine((int)(markX.x1+25), (int)(markX.y2-25), markX.x2-25, markX.y1+25);
        }
    } catch (ConcurrentModificationException e){
            System.out.println("ConcurrentModificationException");
        }
    }

}
