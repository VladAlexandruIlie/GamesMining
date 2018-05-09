import Knn.KNNData;
import Knn.KNearestNeighbors;
import PreProcessing.DataRepository;
import data.DataLoader;
import java.util.*;

public class Main {
    //private static List<Game> data = new ArrayList<>();

    public static void main(String args[]) {
        DataRepository data = new DataRepository(DataLoader.LoadData());
        data.printNumberOfEntries();

        //K-NN
        int testSize = 10; int kNeighbors = 15;
        ArrayList<KNNData> knnData = new ArrayList<>(DataRepository.getKNNData());
        ArrayList<KNNData> toTestData = new ArrayList<>(DataRepository.getTestData(knnData, testSize));
        ArrayList<KNNData> trainingData = new ArrayList<>(DataRepository.getTrainingData(knnData, toTestData));
        HashMap<KNNData, String> predictions = KNearestNeighbors.predict(toTestData, trainingData, kNeighbors);

        // k-NN results
        DataRepository.printPredictions(predictions);
        System.out.printf("Accuracy: %.3f \n", KNearestNeighbors.getAccuracy(predictions));
        //DataRepository.printAverageAccuracy(knnData, testSize, kNeighbors,  100);
    }
}
