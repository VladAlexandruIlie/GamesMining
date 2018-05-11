package kMeans;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Color;
/**
 * Class used to create the visualization for Kmeans. Not sure if we want to use it for the submission.
 * Doesn't accept double and it's annoying. (see line 51, y variable) Global sales are dispplayed on the image Graph as ints..
 * so it's not that useful. (see result.txt x and y coordinates on messenger)
 */
class Surface extends JPanel implements ActionListener {

    private List<Cluster> clusters;

    public Surface(List<Cluster> clusters) {
        this.clusters = clusters;
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        int j=0;

        for(int year = 0; year <= 50; year ++) {
            g2d.setPaint(Color.black);
            g2d.drawLine(0, year * 20, 1000, year * 20);
        }

        for(int globalSale = 0; globalSale <= 100; globalSale++) {
            g2d.setPaint(Color.BLACK);
            g2d.drawLine(globalSale * 20, 0, globalSale * 20, 1000);
        }

        int min = 100000; // check the minimum
        for(Cluster cl: clusters) {
            for (int i = 0; i < cl.getElements().size(); i++) {
                if (cl.getElements().get(i).getYear() < min) {
                    min = cl.getElements().get(i).getYear();
                }
            }
        }

        for(Cluster cluster: clusters) {
            g2d.setPaint(getColor(++j));
            for (int i = 0; i < cluster.getElements().size(); i++) {
                int x = (cluster.getElements().get(i).getYear() - min + 1 )* 25;
                double y = cluster.getElements().get(i).getGlobalSale() * 30;
                //coordinates printout
//                System.out.println("point: " + i + "  x: " + x + "  y: " + y);
                g2d.fillOval((int)y, x, 8, 8);
            }
        }
    }

    // coloured clusters (max 12)
    private Color getColor(int i) {
        switch(i){
            case 1: return Color.red;
            case 2: return Color.blue;
            case 3: return Color.orange;
            case 4: return Color.yellow;
            case 5: return Color.green;
            case 6: return Color.black;
            case 7: return Color.cyan;
            case 8: return new Color(255,102,102);
            case 9: return new Color(255,174, 51);
            case 10: return new Color(102, 51, 0);
            case 11: return new Color(102, 0, 153);
            default: return Color.magenta;
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
