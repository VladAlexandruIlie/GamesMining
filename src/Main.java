import Knn.KNNData;
import Knn.KNearestNeighbors;
import kMeans.Cleaner;
import kMeans.Cluster;
import kMeans.KMeans;
import kMeans.KMeansData;
import data.DataLoader;
import data.Game;

import java.util.*;

public class Main {
    private static List<Game> data = new ArrayList<>();
    private static List<KMeansData> dataK = new ArrayList<>();

    public static void main(String[] args) {
        data = DataLoader.LoadData();
        dataK = Cleaner.getKmeansData(data);
        System.out.println("Loaded " + data.size() + " data entries.");

        //kMeans results
        //clustering by K-means - create clusters to determine games performance based on global sale and the year they were launched
        int numberOfClusters = 3; // we can set how many clusters we want
        System.out.println("K-Means");
        List<Cluster> meansClusters = KMeans.getClusters(dataK, numberOfClusters);
        printClusters(meansClusters);
        System.out.println("\n");
        System.out.println("------------------------------------------");
        System.out.println("\n");

        //K-NN
        int testSize = 10; int kNeighbors = 31;
        ArrayList<KNNData> knnData = new ArrayList<>(getKNNData());
        ArrayList<KNNData> toTestData = new ArrayList<>(getTestData(knnData, testSize));
        ArrayList<KNNData> trainingData = new ArrayList<>(getTrainingData(knnData, toTestData));
        HashMap<KNNData, String> predictions = KNearestNeighbors.predict(toTestData, trainingData, kNeighbors);

//        // k-NN results
        printPredictions(predictions);
        System.out.printf("Accuracy: %.3f \n", KNearestNeighbors.getAccuracy(predictions));
        printAverageAccuracy(knnData, testSize, kNeighbors,  100);
    }

    private static List<KNNData> getKNNData() {
        List<KNNData> knnDataPoints = new ArrayList<>();

        int counter=0;
        for (Game game : data) {
            String name         = game.getName();
            String platform     = game.getPlatform();
            String year         = game.getYear();
            String publisher    = game.getPublisher();
            String na_Sales     = game.getNa_Sales();
            String eu_Sales     = game.getEu_Sales();
            String jp_Sales     = game.getJp_Sales();
            String other_Sales  = game.getOther_Sales();
            String global_Sales = game.getGlobal_Sales();
            String genre        = game.getGenre();

            if (!year.equals("N/A") && !global_Sales.equals("")) {
                KNNData kNNDataPoint = new KNNData(name, platform, year, genre, publisher, na_Sales, eu_Sales, jp_Sales, other_Sales, global_Sales);
                knnDataPoints.add(kNNDataPoint);
            }
            else { counter +=1; }
        }
        System.out.println("Ignored " + counter + " data entries due to incomplete data");
        return knnDataPoints;
    }

    private static ArrayList<KNNData> getTestData(ArrayList<KNNData> knnData, int testSize) {
        ArrayList<KNNData> toTestData = new ArrayList<>();
        for (int i = 0; i < testSize; i++) {
            ArrayList<KNNData> randomizedDataPoints = new ArrayList<>(knnData);

            for (int j = 0; j < 5; j++) {
                Collections.shuffle(randomizedDataPoints);
            }
            int len = randomizedDataPoints.size();
            int idx = new Random().nextInt(len);

            KNNData dataPoint = knnData.get(idx);
            if (toTestData.contains(dataPoint)) return getTestData(knnData, testSize);
            else {
                //String label = "Unknown";
                toTestData.add(dataPoint);
            }
        }
        return toTestData;
    }

    private static ArrayList<KNNData> getTrainingData(ArrayList<KNNData> kNNData, ArrayList<KNNData> toTestData) {
        ArrayList<KNNData> knnData = new ArrayList<>(kNNData);
        ArrayList<KNNData> trainingData = new ArrayList<>();
        for (KNNData knnDataPoint : knnData) {
            boolean found = false;
            for (KNNData toTestPoint : toTestData) {
                if (knnDataPoint.getName().equals(toTestPoint.getName())) found = true;
            }
            if (!found) trainingData.add(knnDataPoint);
        }
        return trainingData;
    }

    private static void printPredictions(HashMap<KNNData, String> predictions) {
        System.out.println("Printing the known test set with corresponding predicted label: ");
        for (KNNData knnData : predictions.keySet()) {
            String output = knnData.toString() + " Prediction: " + predictions.get(knnData);
            System.out.println(output);
        }
    }

    private static void printAverageAccuracy(ArrayList<KNNData> knnData, int testSize, int kNeighbors, int iterations) {
        double accuracyTotal = 0;
        for (int i = 0; i < iterations; i++) {
            ArrayList<KNNData> toTestData = new ArrayList<>(getTestData(knnData, testSize));
            ArrayList<KNNData> trainingData = new ArrayList<>(getTrainingData(knnData, toTestData));
            HashMap<KNNData, String> predictions = KNearestNeighbors.predict(toTestData, trainingData, kNeighbors);
            double accuracy = KNearestNeighbors.getAccuracy(predictions);
            accuracyTotal = accuracyTotal + accuracy;
        }
        System.out.printf("Accuracy average over %d randomly generated test sets is: %.3f \n", iterations, accuracyTotal / (float) iterations);
    }

    private static void printClusters(List<Cluster> clusters) {
        System.out.println("clusters: " + clusters.size());
        for(int i = 0; i < clusters.size(); i++) {
            System.out.println("Index: " + i + " size: " + clusters.get(i).getElements().size());
        }
    }
}


