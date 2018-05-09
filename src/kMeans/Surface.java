package kMeans;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

        for(int age = 17; age <= 50; age ++) {
            g2d.setPaint(Color.black);
            g2d.drawLine(0, age * 20, 1000, age * 20);
        }

        for(int interest = 15; interest <= 50; interest++) {
            g2d.setPaint(Color.BLACK);
            g2d.drawLine(interest * 20, 0, interest * 20, 1000);
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
                System.out.println("point: " + i + "  x: " + x + "  y: " + y);
                g2d.fillOval(x, (int)y, 5, 5);
            }
        }
    }

    private Color getColor(int i) {
        switch(i){
            case 1: return Color.red;
            case 2: return Color.blue;
            case 3: return Color.green;
            case 4: return Color.yellow;
            case 5: return Color.orange;
            case 6: return Color.black;
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
