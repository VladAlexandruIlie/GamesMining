import Knn.KNNData;
import Knn.KNearestNeighbors;
import data.DataLoader;
import data.Game;

import java.util.*;

public class Main {
    private static List<Game> data = new ArrayList<>();

    public static void main (String args[]){
        data = DataLoader.LoadData();
        for (Game g: data){
            System.out.println(g);
        }

        //K-NN
        int testSize = 5; int kNeighbors = 5;
        ArrayList<KNNData> knnData = new ArrayList<>(getKNNData());
        ArrayList<KNNData> toTestData = new ArrayList<>(getTestData(testSize));
        ArrayList<KNNData> trainingData = new ArrayList<>(getTrainingData(knnData, toTestData));
        //HashMap<KNNData, String> predictions = KNearestNeighbors.predict(knnData, toTestData, trainingData, kNeighbors);

        // k-NN results
       // printPredictions(predictions);
       // System.out.printf("Accuracy: %.3f \n", KNearestNeighbors.getAccuracy(knnData,predictions));
        printAverageAccuracy(knnData, testSize, kNeighbors,  100);
    }

    public static List<KNNData> getKNNData(){
        List<KNNData> knnDataPoints = new ArrayList<>();

        for (int i= 0; i<data.size(); i++){
            String name = data.get(i).getName();
            String platform = data.get(i).getPlatform();
            String year = data.get(i).getYear();
            String publisher = data.get(i).getPublisher();
            String na_Sales = data.get(i).getNa_Sales();
            String eu_Sales = data.get(i).getEu_Sales();
            String jp_Sales = data.get(i).getJp_Sales();
            String other_Sales = data.get(i).getOther_Sales();
            String global_Sales = data.get(i).getGlobal_Sales();

            String genre = data.get(i).getGenre();

            KNNData kNNDataPoint = new KNNData(name, platform, year, genre, publisher, na_Sales, eu_Sales, jp_Sales, other_Sales, global_Sales);
            knnDataPoints.add(kNNDataPoint);
        }
        return knnDataPoints;
    }
    public static ArrayList<KNNData> getTestData(int testSize) {
        ArrayList<KNNData> toTestData = new ArrayList<KNNData>();
        for (int i = 0; i < testSize; i++) {
            ArrayList<KNNData> randomizedDataPoints = new ArrayList<KNNData>(getKNNData());

            for (int j = 0; j < 5; j++) {
                Collections.shuffle(randomizedDataPoints);
            }
            int len = randomizedDataPoints.size();
            int idx = new Random().nextInt(len);

            KNNData dataPoint = getKNNData().get(idx);
            if (toTestData.contains(dataPoint)) return getTestData(testSize);
            else {
                //String label = "Unknown";
                toTestData.add(dataPoint);
            }
        }
        return toTestData;
    }
    public static ArrayList<KNNData> getTrainingData(ArrayList<KNNData> kNNData, ArrayList<KNNData> toTestData) {
        ArrayList<KNNData> knnData = new ArrayList<>(getKNNData());
        ArrayList<KNNData> trainingData = new ArrayList<>();
        for (KNNData knnDataPoint: knnData){
            boolean found = false;
            for (KNNData toTestPoint: toTestData){
                if (knnDataPoint.getName().equals(toTestPoint.getName())) found = true;
            }
            if(!found) trainingData.add(knnDataPoint);
        }
        return trainingData;
    }

    public static void printPredictions(HashMap<KNNData, String> predictions) {
        System.out.println("Printing the known test set with corresponding predicted label: ");
        for (KNNData knnData: predictions.keySet()){
            String output = knnData.toString() + " Prediction: " + predictions.get(knnData);
            System.out.println(output);
        }
    }

    public static void printAverageAccuracy(ArrayList<KNNData> knnData, int testSize, int kNeighbors, int iterations) {
        double accuracyTotal = 0;
        for (int i= 0; i< iterations; i++){
            ArrayList<KNNData> toTestData = new ArrayList<KNNData>(getTestData(testSize));
            ArrayList<KNNData> trainingData = new ArrayList<KNNData>(getTrainingData(knnData, toTestData));
            //HashMap<KNNData, String> predictions = KNearestNeighbors.predict(knnData, toTestData, trainingData, kNeighbors);
            //double accuracy =  KNearestNeighbors.getAccuracy(knnData,predictions);
            //accuracyTotal = accuracyTotal + accuracy;
        }
        System.out.printf("Accuracy average over %d randomly generated test sets is: %.3f \n", iterations, accuracyTotal/100.0);
    }


}
