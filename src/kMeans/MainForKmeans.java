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

    public static List<Cluster>  getClusters() {
        data = DataLoader.LoadData();
        dataK = Cleaner.getKmeansData(data);
        int numberOfClusters = 3;
        List<Cluster> meansClusters = KMeans.getClusters(dataK, numberOfClusters);
        return meansClusters;
    }
}