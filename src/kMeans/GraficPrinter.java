package kMeans;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Will display the clusters.
 * This cannot be run within the big main because it cannot call(use) the clusters from getCluster.
 */
public class GraficPrinter extends JFrame {

    public GraficPrinter(List<Cluster> clusters) {

        final Surface surface = new Surface(clusters);
        add(surface);

        setTitle("Points");
        setSize(4000, 4000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                List<Cluster> clusters = MainForKmeans.getClusters();

                GraficPrinter ex = new GraficPrinter(clusters);
                ex.setVisible(true);
            }
        });
    }
}
