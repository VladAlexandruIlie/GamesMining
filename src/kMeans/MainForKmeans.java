package kMeans;

import data.DataLoader;
import data.Game;
import java.util.*;

@SuppressWarnings("Duplicates")

/**
 * This class is used as a main for the graphicPrinter. The duplicate is because I have the exam same thing in the original Main.
 */
public class MainForKmeans {
    private static List<Game> data = new ArrayList<>();
    private static List<KMeansData> dataK = new ArrayList<>();

//    public static void main(String args[]) {
    public static List<Cluster> getClusters() {
        data = DataLoader.LoadData();
        dataK = Cleaner.getKmeansData(data);
        System.out.println("Loaded " + data.size() + " data entries.");

        //kMeans results
        //clustering by K-means - create clusters to determine games performance based on global sale and the year they were launched
        int numberOfClusters = 3;
        System.out.println("K-Means");
        List<Cluster> meansClusters = KMeans.getClusters(dataK, numberOfClusters);
        printClusters(meansClusters);
        System.out.println("\n");
        System.out.println("------------------------------------------");
        System.out.println("\n");
        return meansClusters;
    }

    private static void printClusters(List<Cluster> clusters) {
        System.out.println("clusters: " + clusters.size());
        for(int i = 0; i < clusters.size(); i++) {
            System.out.println("Index: " + i + " size: " + clusters.get(i).getElements().size());
            }
        }
    }


