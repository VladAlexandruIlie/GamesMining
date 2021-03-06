import Knn.KNNData;
import Knn.KNearestNeighbors;
import PreProcessing.DataRepository;
import data.DataLoader;
import data.Game;
import kMeans.Cleaner;
import kMeans.Cluster;
import kMeans.KMeans;
import kMeans.KMeansData;

import java.util.*;

public class Main {
    private static List<Game> data = new ArrayList<>();
    private static List<KMeansData> dataK = new ArrayList<>(Cleaner.getKmeansData(data));

    public static void main(String args[]) {
        DataRepository gamesData = new DataRepository(DataLoader.LoadData());
        gamesData.printNumberOfEntries();
        data = DataRepository.getGamesData();
        System.out.println(data );
        dataK = Cleaner.getKmeansData(data);
        System.out.println("Kmeans data entries: " + dataK.size() + " values.");

        //K-NN
        int testSize = 10; int kNeighbors = 27;
        ArrayList<KNNData> knnData = new ArrayList<>(DataRepository.getKNNData());
        ArrayList<KNNData> toTestData = new ArrayList<>(DataRepository.getTestData(knnData, testSize));
        ArrayList<KNNData> trainingData = new ArrayList<>(DataRepository.getTrainingData(knnData, toTestData));
        HashMap<KNNData, String> predictions = KNearestNeighbors.predict(toTestData, trainingData, kNeighbors);

        // k-NN results
//        DataRepository.printPredictions(predictions);
        System.out.printf("Accuracy: %.3f \n", KNearestNeighbors.getAccuracy(predictions));
        //DataRepository.printAverageAccuracy(knnData, testSize, kNeighbors, 100);
        System.out.println();

        //kMeans results
        //clustering by K-means - create clusters to determine games performance based on global sale and the year they were launched
        int numberOfClusters = 4; // we can set how many clusters we want
        System.out.println("K-Means");
        List<Cluster> meansClusters = KMeans.getClusters(dataK, numberOfClusters);
        printClusters(meansClusters);
        System.out.println("\n");
        System.out.println("------------------------------------------");
    }

    private static void printClusters(List<Cluster> clusters) {
        System.out.println("Number of clusters: " + clusters.size());
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println("Cluster : " + (i + 1) + "  Number of elements : " + clusters.get(i).getElements().size() + " Centroid data: " + clusters.get(i).getCentroid().toString());
        }
    }
}