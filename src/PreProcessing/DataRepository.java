package PreProcessing;

import Knn.KNNData;
import Knn.KNearestNeighbors;
import data.Game;

import java.util.*;

public class DataRepository {
    private static ArrayList<Game> gamesData;

    public DataRepository(ArrayList<Game> games){
        gamesData = games;

    }
    public void printNumberOfEntries() {
        System.out.println("Loaded " + gamesData.size() + " survey answers.");
    }
    private static  ArrayList<Game> getGamesData() {
        return gamesData;
    }

    // Min-Max Normalization
    private float findHigh(List<Float> totalScores) {
        float dataHigh = -1;

        for (Float score : totalScores) {
            if (score > dataHigh) dataHigh = (float) score;
        }
        return dataHigh;
    }
    private float findLow(List<Float> totalScores) {
        float dataLow = 10000;

        for (Float score : totalScores) {
            if (score < dataLow) dataLow = (float) score;
        }
        return dataLow;
    }
    private float MinMaxNormalize(Float score, float dataHigh, float dataLow, float normalizedHigh, float normalizedLow) {
        if (dataHigh == dataLow) return (float) 1 / 2;
        else return ((score - dataLow) / (dataHigh - dataLow)) * (normalizedHigh - normalizedLow) + normalizedLow;
    }

    public static List<KNNData> getKNNData() {
        List<KNNData> knnDataPoints = new ArrayList<>();

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> platforms = new ArrayList<>();
        ArrayList<String> publishers = new ArrayList<>();
        ArrayList<String> genres = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<Double> NAsales = new ArrayList<>();
        ArrayList<Double> EUsales = new ArrayList<>();
        ArrayList<Double> JPsales = new ArrayList<>();
        ArrayList<Double> OTsales = new ArrayList<>();
        ArrayList<Double> GBsales = new ArrayList<>();

        int counter = 0;
        for (Game game : getGamesData()) {
            if (!game.getYear().equals("N/A") && !game.getGlobal_Sales().equals("")) {
                names.add(game.getName());
                platforms.add(game.getPlatform());
                publishers.add(game.getPublisher());
                years.add(Integer.parseInt(game.getYear()));
                NAsales.add(Double.parseDouble(game.getNa_Sales()));
                EUsales.add(Double.parseDouble(game.getEu_Sales()));
                JPsales.add(Double.parseDouble(game.getJp_Sales()));
                OTsales.add(Double.parseDouble(game.getOther_Sales()));
                GBsales.add(Double.parseDouble(game.getGlobal_Sales()));
                genres.add(game.getGenre());
            } else {
                counter += 1;
            }
        }

        years = normaliseData( );

            KNNData kNNDataPoint = new KNNData(name, platform, Integer.parseInt(year), genre, publisher,
                Double.parseDouble(na_Sales), Double.parseDouble(eu_Sales), Double.parseDouble(jp_Sales),
                Double.parseDouble(other_Sales), Double.parseDouble(global_Sales));
        knnDataPoints.add(kNNDataPoint);




        return knnDataPoints;
    }

    public static ArrayList<KNNData> getTestData(ArrayList<KNNData> knnData, int testSize) {
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

    public static ArrayList<KNNData> getTrainingData(ArrayList<KNNData> kNNData, ArrayList<KNNData> toTestData) {
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

    public static void printPredictions(HashMap<KNNData, String> predictions) {
        System.out.println("Printing the known test set with corresponding predicted label: ");
        for (KNNData knnData : predictions.keySet()) {
            String output = knnData.toString() + " Prediction: " + predictions.get(knnData);
            System.out.println(output);
        }
    }

    public static void printAverageAccuracy(ArrayList<KNNData> knnData, int testSize, int kNeighbors, int iterations) {
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



}
