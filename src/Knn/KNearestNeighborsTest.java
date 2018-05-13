package Knn;

import PreProcessing.DataRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class KNearestNeighborsTest {

    private int k = 5;
    private ArrayList<KNNData> knnDataPoints = new ArrayList<>();
    private ArrayList<KNNData> toTestData = new ArrayList<>();
    private ArrayList<KNNData> trainingData = new ArrayList<>();
    private HashMap<KNNData, String> predictions = new HashMap<>();

    @Before
    public void setUp() {
        ArrayList<List<Float>> allAttributes = new ArrayList<>();

        allAttributes.add(Arrays.asList((float) 0.222, (float) 0.625, (float) 0.068, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.167, (float) 0.417, (float) 0.068, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.111, (float) 0.500, (float) 0.051, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.083, (float) 0.458, (float) 0.085, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.194, (float) 0.667, (float) 0.068, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.306, (float) 0.792, (float) 0.119, (float) 0.125));
        allAttributes.add(Arrays.asList((float) 0.083, (float) 0.583, (float) 0.068, (float) 0.083));
        allAttributes.add(Arrays.asList((float) 0.194, (float) 0.583, (float) 0.085, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.028, (float) 0.375, (float) 0.068, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.167, (float) 0.458, (float) 0.085, (float) 0.000));
        allAttributes.add(Arrays.asList((float) 0.306, (float) 0.708, (float) 0.085, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.139, (float) 0.583, (float) 0.102, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.139, (float) 0.417, (float) 0.068, (float) 0.000));
        allAttributes.add(Arrays.asList((float) 0.000, (float) 0.417, (float) 0.017, (float) 0.000));
        allAttributes.add(Arrays.asList((float) 0.417, (float) 0.833, (float) 0.034, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.389, (float) 1.000, (float) 0.085, (float) 0.125));
        allAttributes.add(Arrays.asList((float) 0.306, (float) 0.792, (float) 0.051, (float) 0.125));
        allAttributes.add(Arrays.asList((float) 0.222, (float) 0.625, (float) 0.068, (float) 0.083));
        allAttributes.add(Arrays.asList((float) 0.389, (float) 0.750, (float) 0.119, (float) 0.083));
        allAttributes.add(Arrays.asList((float) 0.222, (float) 0.750, (float) 0.085, (float) 0.083));
        allAttributes.add(Arrays.asList((float) 0.306, (float) 0.583, (float) 0.119, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.222, (float) 0.708, (float) 0.085, (float) 0.125));
        allAttributes.add(Arrays.asList((float) 0.083, (float) 0.667, (float) 0.000, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.222, (float) 0.542, (float) 0.119, (float) 0.167));
        allAttributes.add(Arrays.asList((float) 0.139, (float) 0.583, (float) 0.153, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.194, (float) 0.417, (float) 0.102, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.194, (float) 0.583, (float) 0.102, (float) 0.125));
        allAttributes.add(Arrays.asList((float) 0.250, (float) 0.625, (float) 0.085, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.250, (float) 0.583, (float) 0.068, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.111, (float) 0.500, (float) 0.102, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.139, (float) 0.458, (float) 0.102, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.306, (float) 0.583, (float) 0.085, (float) 0.125));
        allAttributes.add(Arrays.asList((float) 0.250, (float) 0.875, (float) 0.085, (float) 0.000));
        allAttributes.add(Arrays.asList((float) 0.333, (float) 0.917, (float) 0.068, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.167, (float) 0.458, (float) 0.085, (float) 0.000));
        allAttributes.add(Arrays.asList((float) 0.194, (float) 0.500, (float) 0.034, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.333, (float) 0.625, (float) 0.051, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.167, (float) 0.458, (float) 0.085, (float) 0.000));
        allAttributes.add(Arrays.asList((float) 0.028, (float) 0.417, (float) 0.051, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.222, (float) 0.583, (float) 0.085, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.194, (float) 0.625, (float) 0.051, (float) 0.083));
        allAttributes.add(Arrays.asList((float) 0.056, (float) 0.125, (float) 0.051, (float) 0.083));
        allAttributes.add(Arrays.asList((float) 0.028, (float) 0.500, (float) 0.051, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.194, (float) 0.625, (float) 0.102, (float) 0.208));
        allAttributes.add(Arrays.asList((float) 0.222, (float) 0.750, (float) 0.153, (float) 0.125));
        allAttributes.add(Arrays.asList((float) 0.139, (float) 0.417, (float) 0.068, (float) 0.083));
        allAttributes.add(Arrays.asList((float) 0.222, (float) 0.750, (float) 0.102, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.083, (float) 0.500, (float) 0.068, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.278, (float) 0.708, (float) 0.085, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.194, (float) 0.542, (float) 0.068, (float) 0.042));
        allAttributes.add(Arrays.asList((float) 0.750, (float) 0.500, (float) 0.627, (float) 0.542));
        allAttributes.add(Arrays.asList((float) 0.583, (float) 0.500, (float) 0.593, (float) 0.583));
        allAttributes.add(Arrays.asList((float) 0.722, (float) 0.458, (float) 0.661, (float) 0.583));
        allAttributes.add(Arrays.asList((float) 0.333, (float) 0.125, (float) 0.508, (float) 0.500));
        allAttributes.add(Arrays.asList((float) 0.611, (float) 0.333, (float) 0.610, (float) 0.583));
        allAttributes.add(Arrays.asList((float) 0.389, (float) 0.333, (float) 0.593, (float) 0.500));
        allAttributes.add(Arrays.asList((float) 0.556, (float) 0.542, (float) 0.627, (float) 0.625));
        allAttributes.add(Arrays.asList((float) 0.167, (float) 0.167, (float) 0.390, (float) 0.375));
        allAttributes.add(Arrays.asList((float) 0.639, (float) 0.375, (float) 0.610, (float) 0.500));
        allAttributes.add(Arrays.asList((float) 0.250, (float) 0.292, (float) 0.492, (float) 0.542));
        allAttributes.add(Arrays.asList((float) 0.194, (float) 0.000, (float) 0.424, (float) 0.375));
        allAttributes.add(Arrays.asList((float) 0.444, (float) 0.417, (float) 0.542, (float) 0.583));
        allAttributes.add(Arrays.asList((float) 0.472, (float) 0.083, (float) 0.508, (float) 0.375));
        allAttributes.add(Arrays.asList((float) 0.500, (float) 0.375, (float) 0.627, (float) 0.542));
        allAttributes.add(Arrays.asList((float) 0.361, (float) 0.375, (float) 0.441, (float) 0.500));
        allAttributes.add(Arrays.asList((float) 0.667, (float) 0.458, (float) 0.576, (float) 0.542));
        allAttributes.add(Arrays.asList((float) 0.361, (float) 0.417, (float) 0.593, (float) 0.583));
        allAttributes.add(Arrays.asList((float) 0.417, (float) 0.292, (float) 0.525, (float) 0.375));
        allAttributes.add(Arrays.asList((float) 0.528, (float) 0.083, (float) 0.593, (float) 0.583));
        allAttributes.add(Arrays.asList((float) 0.361, (float) 0.208, (float) 0.492, (float) 0.417));
        allAttributes.add(Arrays.asList((float) 0.444, (float) 0.500, (float) 0.644, (float) 0.708));
        allAttributes.add(Arrays.asList((float) 0.500, (float) 0.333, (float) 0.508, (float) 0.500));
        allAttributes.add(Arrays.asList((float) 0.556, (float) 0.208, (float) 0.661, (float) 0.583));
        allAttributes.add(Arrays.asList((float) 0.500, (float) 0.333, (float) 0.627, (float) 0.458));
        allAttributes.add(Arrays.asList((float) 0.583, (float) 0.375, (float) 0.559, (float) 0.500));
        allAttributes.add(Arrays.asList((float) 0.639, (float) 0.417, (float) 0.576, (float) 0.542));
        allAttributes.add(Arrays.asList((float) 0.694, (float) 0.333, (float) 0.644, (float) 0.542));
        allAttributes.add(Arrays.asList((float) 0.667, (float) 0.417, (float) 0.678, (float) 0.667));
        allAttributes.add(Arrays.asList((float) 0.472, (float) 0.375, (float) 0.593, (float) 0.583));
        allAttributes.add(Arrays.asList((float) 0.389, (float) 0.250, (float) 0.424, (float) 0.375));
        allAttributes.add(Arrays.asList((float) 0.333, (float) 0.167, (float) 0.475, (float) 0.417));
        allAttributes.add(Arrays.asList((float) 0.333, (float) 0.167, (float) 0.458, (float) 0.375));
        allAttributes.add(Arrays.asList((float) 0.417, (float) 0.292, (float) 0.492, (float) 0.458));
        allAttributes.add(Arrays.asList((float) 0.472, (float) 0.292, (float) 0.695, (float) 0.625));
        allAttributes.add(Arrays.asList((float) 0.306, (float) 0.417, (float) 0.593, (float) 0.583));
        allAttributes.add(Arrays.asList((float) 0.472, (float) 0.583, (float) 0.593, (float) 0.625));
        allAttributes.add(Arrays.asList((float) 0.667, (float) 0.458, (float) 0.627, (float) 0.583));
        allAttributes.add(Arrays.asList((float) 0.556, (float) 0.125, (float) 0.576, (float) 0.500));
        allAttributes.add(Arrays.asList((float) 0.361, (float) 0.417, (float) 0.525, (float) 0.500));
        allAttributes.add(Arrays.asList((float) 0.333, (float) 0.208, (float) 0.508, (float) 0.500));
        allAttributes.add(Arrays.asList((float) 0.333, (float) 0.250, (float) 0.576, (float) 0.458));
        allAttributes.add(Arrays.asList((float) 0.500, (float) 0.417, (float) 0.610, (float) 0.542));
        allAttributes.add(Arrays.asList((float) 0.417, (float) 0.250, (float) 0.508, (float) 0.458));
        allAttributes.add(Arrays.asList((float) 0.194, (float) 0.125, (float) 0.390, (float) 0.375));
        allAttributes.add(Arrays.asList((float) 0.361, (float) 0.292, (float) 0.542, (float) 0.500));
        allAttributes.add(Arrays.asList((float) 0.389, (float) 0.417, (float) 0.542, (float) 0.458));
        allAttributes.add(Arrays.asList((float) 0.389, (float) 0.375, (float) 0.542, (float) 0.500));
        allAttributes.add(Arrays.asList((float) 0.528, (float) 0.375, (float) 0.559, (float) 0.500));
        allAttributes.add(Arrays.asList((float) 0.222, (float) 0.208, (float) 0.339, (float) 0.417));
        allAttributes.add(Arrays.asList((float) 0.389, (float) 0.333, (float) 0.525, (float) 0.500));
        allAttributes.add(Arrays.asList((float) 0.556, (float) 0.542, (float) 0.847, (float) 1.000));
        allAttributes.add(Arrays.asList((float) 0.417, (float) 0.292, (float) 0.695, (float) 0.750));
        allAttributes.add(Arrays.asList((float) 0.778, (float) 0.417, (float) 0.831, (float) 0.833));
        allAttributes.add(Arrays.asList((float) 0.556, (float) 0.375, (float) 0.780, (float) 0.708));
        allAttributes.add(Arrays.asList((float) 0.611, (float) 0.417, (float) 0.814, (float) 0.875));
        allAttributes.add(Arrays.asList((float) 0.917, (float) 0.417, (float) 0.949, (float) 0.833));
        allAttributes.add(Arrays.asList((float) 0.167, (float) 0.208, (float) 0.593, (float) 0.667));
        allAttributes.add(Arrays.asList((float) 0.833, (float) 0.375, (float) 0.898, (float) 0.708));
        allAttributes.add(Arrays.asList((float) 0.667, (float) 0.208, (float) 0.814, (float) 0.708));
        allAttributes.add(Arrays.asList((float) 0.806, (float) 0.667, (float) 0.864, (float) 1.000));
        allAttributes.add(Arrays.asList((float) 0.611, (float) 0.500, (float) 0.695, (float) 0.792));
        allAttributes.add(Arrays.asList((float) 0.583, (float) 0.292, (float) 0.729, (float) 0.750));
        allAttributes.add(Arrays.asList((float) 0.694, (float) 0.417, (float) 0.763, (float) 0.833));
        allAttributes.add(Arrays.asList((float) 0.389, (float) 0.208, (float) 0.678, (float) 0.792));
        allAttributes.add(Arrays.asList((float) 0.417, (float) 0.333, (float) 0.695, (float) 0.958));
        allAttributes.add(Arrays.asList((float) 0.583, (float) 0.500, (float) 0.729, (float) 0.917));
        allAttributes.add(Arrays.asList((float) 0.611, (float) 0.417, (float) 0.763, (float) 0.708));
        allAttributes.add(Arrays.asList((float) 0.944, (float) 0.750, (float) 0.966, (float) 0.875));
        allAttributes.add(Arrays.asList((float) 0.944, (float) 0.250, (float) 1.000, (float) 0.917));
        allAttributes.add(Arrays.asList((float) 0.472, (float) 0.083, (float) 0.678, (float) 0.583));
        allAttributes.add(Arrays.asList((float) 0.722, (float) 0.500, (float) 0.797, (float) 0.917));
        allAttributes.add(Arrays.asList((float) 0.361, (float) 0.333, (float) 0.661, (float) 0.792));
        allAttributes.add(Arrays.asList((float) 0.944, (float) 0.333, (float) 0.966, (float) 0.792));
        allAttributes.add(Arrays.asList((float) 0.556, (float) 0.292, (float) 0.661, (float) 0.708));
        allAttributes.add(Arrays.asList((float) 0.667, (float) 0.542, (float) 0.797, (float) 0.833));
        allAttributes.add(Arrays.asList((float) 0.806, (float) 0.500, (float) 0.847, (float) 0.708));
        allAttributes.add(Arrays.asList((float) 0.528, (float) 0.333, (float) 0.644, (float) 0.708));
        allAttributes.add(Arrays.asList((float) 0.500, (float) 0.417, (float) 0.661, (float) 0.708));
        allAttributes.add(Arrays.asList((float) 0.583, (float) 0.333, (float) 0.780, (float) 0.833));
        allAttributes.add(Arrays.asList((float) 0.806, (float) 0.417, (float) 0.814, (float) 0.625));
        allAttributes.add(Arrays.asList((float) 0.861, (float) 0.333, (float) 0.864, (float) 0.750));
        allAttributes.add(Arrays.asList((float) 1.000, (float) 0.750, (float) 0.915, (float) 0.792));
        allAttributes.add(Arrays.asList((float) 0.583, (float) 0.333, (float) 0.780, (float) 0.875));
        allAttributes.add(Arrays.asList((float) 0.556, (float) 0.333, (float) 0.695, (float) 0.583));
        allAttributes.add(Arrays.asList((float) 0.500, (float) 0.250, (float) 0.780, (float) 0.542));
        allAttributes.add(Arrays.asList((float) 0.944, (float) 0.417, (float) 0.864, (float) 0.917));
        allAttributes.add(Arrays.asList((float) 0.556, (float) 0.583, (float) 0.780, (float) 0.958));
        allAttributes.add(Arrays.asList((float) 0.583, (float) 0.458, (float) 0.763, (float) 0.708));
        allAttributes.add(Arrays.asList((float) 0.472, (float) 0.417, (float) 0.644, (float) 0.708));
        allAttributes.add(Arrays.asList((float) 0.722, (float) 0.458, (float) 0.746, (float) 0.833));
        allAttributes.add(Arrays.asList((float) 0.667, (float) 0.458, (float) 0.780, (float) 0.958));
        allAttributes.add(Arrays.asList((float) 0.722, (float) 0.458, (float) 0.695, (float) 0.917));
        allAttributes.add(Arrays.asList((float) 0.417, (float) 0.292, (float) 0.695, (float) 0.750));
        allAttributes.add(Arrays.asList((float) 0.694, (float) 0.500, (float) 0.831, (float) 0.917));
        allAttributes.add(Arrays.asList((float) 0.667, (float) 0.542, (float) 0.797, (float) 1.000));
        allAttributes.add(Arrays.asList((float) 0.667, (float) 0.417, (float) 0.712, (float) 0.917));
        allAttributes.add(Arrays.asList((float) 0.556, (float) 0.208, (float) 0.678, (float) 0.750));
        allAttributes.add(Arrays.asList((float) 0.611, (float) 0.417, (float) 0.712, (float) 0.792));
        allAttributes.add(Arrays.asList((float) 0.528, (float) 0.583, (float) 0.746, (float) 0.917));
        allAttributes.add(Arrays.asList((float) 0.444, (float) 0.417, (float) 0.695, (float) 0.708));

        ArrayList<String> importedLabels = new ArrayList<>(Arrays.asList(
                "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa",
                "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa",
                "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa",
                "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa",
                "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa", "Iris_setosa",
                "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor",
                "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor",
                "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor",
                "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor",
                "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor",
                "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor", "Iris_versicolor",
                "Iris_versicolor", "Iris_versicolor", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica",
                "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica",
                "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica",
                "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica",
                "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica",
                "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica", "Iris_virginica"));

        ArrayList<String> labels = new ArrayList<>(importedLabels);

        for (int i = 0; i < labels.size(); i++) {
            String name = "P" + (i + 1);
            String platform = labels.get(i);
            Double year = 1.0;
            String genre = "Unknown";
            String publisher  = "Unknown";
            double na_Sales = allAttributes.get(i).get(0);
            double eu_Sales = allAttributes.get(i).get(1);
            double jp_Sales = allAttributes.get(i).get(2);
            double other_Sales = allAttributes.get(i).get(3);
            double global_Sales = 0.0;

            KNNData kNNDataPoint = new KNNData(name, platform, year, genre, publisher,na_Sales, eu_Sales, jp_Sales, other_Sales, global_Sales);
            this.knnDataPoints.add(kNNDataPoint);
        }

        this.toTestData = new ArrayList<>(getTestData(knnDataPoints, 20));
        this.trainingData = new ArrayList<>(getTrainingData(knnDataPoints, toTestData));
        this.predictions = KNearestNeighbors.predict(toTestData, trainingData, 9);
    }

    @Test
    public void predict() {

//        System.out.println(knnDataPoints);
//        System.out.println(predictions);

        DataRepository.printPredictions(predictions);
//
//        System.out.printf("Accuracy: %.3f \n", KNearestNeighbors.getAccuracy(knnDataPoints, predictions));

        double accuracyTotal = 0;
        for (int i = 0; i < 100; i++) {
            toTestData = new ArrayList<>(getTestData(knnDataPoints, 10));
            trainingData = new ArrayList<>(getTrainingData(knnDataPoints, toTestData));
            predictions = KNearestNeighbors.predict(toTestData, trainingData, k);
            double accuracy = KNearestNeighbors.getAccuracy(predictions);
            accuracyTotal = accuracyTotal + accuracy;
        }

        accuracyTotal = accuracyTotal / (double) 100;

        assertTrue(accuracyTotal > 90);
        System.out.printf("Accuracy average over 100 randomly generated test sets is: %.3f is higher than the threshold 90 ", accuracyTotal);

    }

    private ArrayList<KNNData> getTestData(ArrayList<KNNData> kNNData, int testSize) {
        ArrayList<KNNData> toTestData = new ArrayList<>();


        for (int i = 0; i < testSize; i++) {
            ArrayList<KNNData> randomizedDataPoints = new ArrayList<>(kNNData);

            for (int j = 0; j < 5; j++) {
                Collections.shuffle(randomizedDataPoints);
            }

            int len = randomizedDataPoints.size();
            int idx = new Random().nextInt(len);

            KNNData dataPoint = kNNData.get(idx);

            if (toTestData.contains(dataPoint)) return getTestData(kNNData, testSize);
            else {
                toTestData.add(dataPoint);
            }
        }
        return toTestData;
    }

    private ArrayList<KNNData> getTrainingData(ArrayList<KNNData> kNNData, ArrayList<KNNData> toTestData) {
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

}

