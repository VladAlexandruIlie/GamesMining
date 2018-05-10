package Knn;
import java.util.*;

public class KNearestNeighbors {
    public static HashMap<KNNData,String> predict(ArrayList<KNNData> toTestData, ArrayList<KNNData> trainingData, int k) {
        HashMap<KNNData,String> predictions =  new HashMap<>();

        for (KNNData unknownPoint : toTestData){
            HashMap<KNNData, Double> neighbors = new HashMap<>(getNeighbors(trainingData, unknownPoint, k));

            //printNeighbors(trainingData,unknownPoint,k);

            String label = getResponses(neighbors);
            predictions.put(unknownPoint, label);
        }
        return predictions;
    }

    private static Map<KNNData, Double> getNeighbors(ArrayList<KNNData> trainingData, KNNData unknownPoint, int k) {
        HashMap<KNNData, Double> neighbors = new HashMap<>();
        HashMap<KNNData, Double> distances =  new HashMap<>();

        for (KNNData knnData: trainingData){
            double dist = distance(unknownPoint, knnData);
            distances.put(knnData, dist);
        }

        List<Map.Entry<KNNData, Double>> list = new ArrayList<>(distances.entrySet());
        list.sort(Comparator.comparing(o -> (o.getValue())));
        Map<KNNData, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<KNNData, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        for (int i=0 ; i<k;i++) {
            ArrayList<KNNData> keys = new ArrayList<>(sortedMap.keySet());
            neighbors.put(keys.get(i), sortedMap.get(keys.get(i)));
        }
        return neighbors;
    }

    private static double distance(KNNData knnData, KNNData unknownPoint) {
        float distance = 0;
        ArrayList<Object> o1 = knnData.getAttributes();
        ArrayList<Object> o2 = unknownPoint.getAttributes();

        if (!o1.get(0).equals(o2.get(0))) distance +=0.15;
        if (!o1.get(1).equals(o2.get(1))) distance +=0.15;
        for (int i=2; i<o1.size();i++){
            distance += Math.pow((Double.parseDouble(o2.get(i).toString()) - Double.parseDouble(o1.get(i).toString())), 2);
        }
        return Math.sqrt(distance);
    }

    private static String getResponses(HashMap<KNNData, Double> neighbors) {
        HashMap<String, Integer> genreFrequency = new HashMap<>();

        for (KNNData datapoint: neighbors.keySet()){

            if (genreFrequency.keySet().contains(datapoint.getPlatform())){
                int occurances = genreFrequency.get(datapoint.getPlatform()) + 1;
                genreFrequency.remove(datapoint.getName());
                genreFrequency.put(datapoint.getPlatform(), occurances);
            }
            else {
                genreFrequency.putIfAbsent(datapoint.getPlatform(), 1);
            }
        }

        double max = -1;
        String final_label ="";
        for (String datapoint :  genreFrequency.keySet()){
            if (genreFrequency.get(datapoint) > max) {
                max = genreFrequency.get(datapoint);
                final_label =  datapoint;
            }
        }
        return final_label;
    }

    public static double getAccuracy(HashMap<KNNData, String> predictions) {
        float correct = 0;
        for (KNNData key: predictions.keySet()){
            if (key.getPlatform().equals(predictions.get(key))) correct +=1;
        }
        //System.out.println(correct);
        return correct/predictions.size() * 100.0 ;
    }

    private static void printNeighbors(ArrayList<KNNData> trainingData, KNNData unknownPoint, int k) {
        HashMap<KNNData, Double> neighbors = new HashMap<>(getNeighbors(trainingData, unknownPoint,k));

        System.out.println(unknownPoint + "is close to: ");
        for (KNNData data: neighbors.keySet()){
            System.out.println(data +" "+ String.format("%.4f", neighbors.get(data) ));
        }
        System.out.println();
    }



}

