package kMeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("Duplicates")

public class KMeans {

    /**
     * Finds the clusters.
     * @param data - The normalised data.
     * @param k - number of clusters.
     * @return The clusters.
     */
    public static List<Cluster> getClusters(List<KMeansData> data, int k) {
        List<Cluster> clusters = new ArrayList<>();

        List<KMeansData> temp = new ArrayList<>(data);

        //initial clusters - randomly generated
        for(int i = 1; i <= k; i++) {
            int rng = new Random().nextInt(temp.size());
            Cluster cluster = new Cluster();
            cluster.setCentroid(temp.get(rng));
            clusters.add(cluster);
            temp.remove(rng);
        }

        //add each entry to the corresponding cluster(closest centroid)
        for(KMeansData dataK: temp) {
            addElementToCluster(dataK, clusters);
        }

        //verify if we changed anything
        boolean centroidChanges = true;
        while(centroidChanges) { //at each iteration, try to centralize the clusters
            List<Cluster> newClusters = new ArrayList<>();
            for(int i = 0; i < clusters.size(); i++) { ////for each old cluster, getting a new one with the most central centroid
                newClusters.add(getNewCluster(clusters.get(i)));
            }

            for(KMeansData ns : data) { ////add each entry each of the new closest centroids
                addElementToCluster(ns, newClusters);
            }


            centroidChanges = false;
            for(int i = 0; i < clusters.size(); i++) {
                int x1 = clusters.get(i).getCentroid().getYear();
                double y1 = clusters.get(i).getCentroid().getGlobalSale();
                int x2 = newClusters.get(i).getCentroid().getYear();
                double y2 = newClusters.get(i).getCentroid().getGlobalSale();

                if(x1 != x2 || y1 != y2 || clusters.get(i).getElements().size() != newClusters.get(i).getElements().size()) {
                    centroidChanges = true;
                    break;
                }
            }

            clusters = new ArrayList<>(); //update the clusters
            clusters.addAll(newClusters);
        }

        return clusters;
    }

    /**
     * This method calculates the average of each coordinate, then checks which is the closest value to it.
     * @param cluster
     * @return The new cluster with only containing the new centroid.
     */
    private static Cluster getNewCluster(Cluster cluster) {
        int a = 0;
        int b = 0;
        if(cluster.getElements() == null){ return null; }
        for(KMeansData normalisedData:cluster.getElements()) {
            a += normalisedData.getYear();
            b += normalisedData.getGlobalSale();
        }
        try {
            a /= cluster.getElements().size();
            b /= cluster.getElements().size();
        } catch (ArithmeticException e){
            a = 1;
            b = 1;
        }

//        System.out.println("cluster elem :" + cluster.getElements().size());
        int c = -1;
        double min = 10000000;
        for(KMeansData ns:cluster.getElements()) {
            double x = 1.0 * Math.abs(a - ns.getYear());
            double y = 1.0 * Math.abs(b - ns.getGlobalSale());
            double distance = Math.sqrt(x * x + y * y);
            if(distance < min) {
                min = distance;
                c = cluster.getElements().indexOf(ns);
            }
        }

        Cluster newCluster = new Cluster();
        if (c == -1){ return null; }
        newCluster.setCentroid(cluster.getElements().get(c));
        return newCluster;
    }

    /**
     * Chooses the closest centroid using the distance formula.
     * @param data - the data element that needs to be added
     * @param clusters - the clusters where should be added
     */
    private static void addElementToCluster(KMeansData data, List<Cluster> clusters) {
        int c = -1; //initialise with a value that cannot exist
        double min = 1000000;
        for(int i = 0; i < clusters.size(); i++) {
            if(clusters.get(i) == null) continue;
            double x = 1.0 * Math.abs(clusters.get(i).getCentroid().getYear() - data.getYear());
            double y = 1.0 * Math.abs(clusters.get(i).getCentroid().getGlobalSale() - data.getGlobalSale());
            double distance = Math.sqrt(x * x + y * y);
            if(distance < min) {
                min = distance;
                c = i;
            }
        }
        clusters.get(c).addElement(data);
    }
}