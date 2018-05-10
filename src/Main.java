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
        dataK = Cleaner.getKmeansData(data);

        //K-NN
        int testSize = 10; int kNeighbors = 21;
        ArrayList<KNNData> knnData = new ArrayList<>(DataRepository.getKNNData());
        ArrayList<KNNData> toTestData = new ArrayList<>(DataRepository.getTestData(knnData, testSize));
        ArrayList<KNNData> trainingData = new ArrayList<>(DataRepository.getTrainingData(knnData, toTestData));
        HashMap<KNNData, String> predictions = KNearestNeighbors.predict(toTestData, trainingData, kNeighbors);

        // k-NN results
        DataRepository.printPredictions(predictions);
        System.out.printf("Accuracy: %.3f \n", KNearestNeighbors.getAccuracy(predictions));
        DataRepository.printAverageAccuracy(knnData, testSize, kNeighbors, 100);
        System.out.println();

        //kMeans results
        //clustering by K-means - create clusters to determine games performance based on global sale and the year they were launched
        int numberOfClusters = 3; // we can set how many clusters we want
        System.out.println("K-Means");
        List<Cluster> meansClusters = KMeans.getClusters(dataK, numberOfClusters);
        printClusters(meansClusters);
        System.out.println("\n");
        System.out.println("------------------------------------------");
        System.out.println("\n");
    }

    private static void printClusters(List<Cluster> clusters) {
        System.out.println("clusters: " + clusters.size());
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println("Index: " + i + " size: " + clusters.get(i).getElements().size());
        }
    }
}