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
    private static Double findHigh(List<Double> totalScores) {
        Double dataHigh = -1.0;

        for (Double score : totalScores) {
            if (score > dataHigh) dataHigh =  score;
        }
        return dataHigh;
    }
    private static Double findLow(List<Double> totalScores) {
        Double dataLow = 10000.0;

        for (Double score : totalScores) {
            if (score < dataLow) dataLow = score;
        }
        return dataLow;
    }
    private static Double MinMaxNormalize(Double data, Double dataHigh, Double dataLow, Double normalizedHigh, Double normalizedLow) {
        if (dataHigh == dataLow) return 1/2.0;
        else return ((data - dataLow) / (dataHigh - dataLow)) * (normalizedHigh - normalizedLow) + normalizedLow;
    }

    public static List<KNNData> getKNNData() {
        List<KNNData> knnDataPoints = new ArrayList<>();

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> platforms = new ArrayList<>();
        ArrayList<String> publishers = new ArrayList<>();
        ArrayList<String> genres = new ArrayList<>();
        ArrayList<Double> years = new ArrayList<>();
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
                years.add(Double.parseDouble(game.getYear()));
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

        years = normaliseData(years,1.0,0.0);
        //NAsales = normaliseData(NAsales,10.0,0.0);
        //EUsales = normaliseData(EUsales,10.0,0.0);
        //JPsales = normaliseData(JPsales,10.0,0.0);
        //OTsales = normaliseData(OTsales,10.0,0.0);
        //GBsales = normaliseData(GBsales,40.0,0.0);
        //System.out.println("Years : " + years);

        for(int i =0; i<names.size(); i++) {
            String name = names.get(i);
            String platform = platforms.get(i);
            Double year = years.get(i);
            String genre = genres.get(i);
            String publisher = publishers.get(i);
            Double na_Sales = NAsales.get(i);
            Double eu_Sales = EUsales.get(i);
            Double jp_Sales = JPsales.get(i);
            Double other_Sales = OTsales.get(i);
            Double global_Sales = GBsales.get(i);
            KNNData kNNDataPoint = new KNNData(name, platform, year, genre, publisher, na_Sales, eu_Sales, jp_Sales, other_Sales, global_Sales);
            knnDataPoints.add(kNNDataPoint);

        }


        return knnDataPoints;
    }

    private static ArrayList<Double> normaliseData(ArrayList<Double> data,Double upperBound, Double lowerBound ) {
        ArrayList<Double> normalizedData = new ArrayList<>();

        Double dataHigh = findHigh(data);
        Double dataLow = findLow(data);

        for (Double newYear : data) {
            Double normalized = MinMaxNormalize(newYear, dataHigh, dataLow, upperBound, lowerBound);
            normalizedData.add(normalized);
        }
        return normalizedData;
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
