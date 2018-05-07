
package Knn;

import java.util.*;



public class KNearestNeighbors {
    public static HashMap<KNNData,String> predict(ArrayList<KNNData> knnData, ArrayList<KNNData> toTestData, ArrayList<KNNData> trainingData, int k) {
        HashMap<KNNData,String> predictions =  new HashMap<>();

        for (KNNData unknownPoint : toTestData){
            HashMap<KNNData, Double> neighbors = new HashMap<KNNData, Double>(getNeighbors(trainingData, unknownPoint, k));

            String label = getResponses(neighbors);
            predictions.put(unknownPoint, label);
        }
        double accuracy = getAccuracy(knnData, predictions);
//        System.out.println("Accuracy: " + accuracy);
        return predictions;
    }

    private static Map<KNNData, Double> getNeighbors(ArrayList<KNNData> trainingData, KNNData unknownPoint, int k) {
        HashMap<KNNData, Double> neighbors = new HashMap<KNNData, Double>();
        HashMap<KNNData, Double> distances =  new HashMap<>();

        for (KNNData knnData: trainingData){
            double dist = distance(knnData, unknownPoint);
            distances.put(knnData, dist);
        }

//        for (KNNData knnData: distances.keySet()){
//            System.out.println(knnData +" -> " + distances.get(knnData));
//        }

        List<Map.Entry<KNNData, Double>> list = new ArrayList<>(distances.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

        // creating a new map to store the sorted list
        Map<KNNData, Double> sortedMap = new LinkedHashMap<KNNData, Double>();
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

        if (o1.get(0).equals(o2.get(0))) distance +=1;
        if (o1.get(1).equals(o2.get(1))) distance +=1;


        for (int i=2; i<o1.size();i++){
            distance += Math.pow((Double.parseDouble(o1.get(i).toString()) - Double.parseDouble(o2.get(i).toString())), 2);
        }
        return Math.sqrt(distance);
    }

    private static String getResponses(HashMap<KNNData, Double> neighbors) {
        HashMap<String, Integer> labelFrequency = new HashMap<>();
        ArrayList<String> genres =  new ArrayList<>();

        String label ="";
        for (KNNData datapoint: neighbors.keySet()){

            if (labelFrequency.keySet().contains(datapoint.getGenre())){
                int occurances = labelFrequency.get(datapoint.getGenre()) + 1;
                labelFrequency.remove(datapoint.name);
                labelFrequency.put(datapoint.getGenre(), occurances);
            }
            else {
                labelFrequency.putIfAbsent(datapoint.getGenre(), 1);
            }
            genres.add(datapoint.getGenre());
        }

//        System.out.println(labels);
//        System.out.println(labelFrequency);

        double max = -1;
        String final_label ="";
        for (KNNData datapoint :  neighbors.keySet()){
            if (neighbors.get(datapoint) > max) {
                max = neighbors.get(datapoint);
                final_label =  datapoint.getGenre();
            }
        }

        return final_label;
    }

    public static double getAccuracy(ArrayList<KNNData> knnData, HashMap<KNNData, String> predictions) {
        float correct = 0;
        for (KNNData key: predictions.keySet()){

            for (KNNData knownPoint: knnData){
                if (knownPoint.name.equals(key.getName()) && predictions.get(key).equals(knownPoint.getGenre())) correct++;
            }
        }
        return correct/predictions.size() * 100.0 ;
    }

    public static void printNeighbors(ArrayList<KNNData> trainingData, KNNData unknownPoint, int k) {
        HashMap<KNNData, Double> neighbors = new HashMap<KNNData, Double>(getNeighbors(trainingData, unknownPoint,k));

        String output = "Name: " + unknownPoint.getName() + " >{ Scors: ";
        for (Object i: unknownPoint.getAttributes()){
            output += String.format(" %.2f ,",i);
        }
        output += " -> Known Degree: " + unknownPoint.getGenre() +" }";

        System.out.print(output);

        System.out.println(" is closest to: ");
        for (KNNData knnData: neighbors.keySet()){
            System.out.println( knnData + " " + distance(knnData , unknownPoint));
        }
        System.out.println();
    }



}

