package kMeans;

import data.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Transforms the data from Strings to the desired type. Loader + cleaning happens here as well.
 */
public class Cleaner {

    public static int getYear(String string){
        string = string.split(" ")[0];
        int year = 0;
        try {
            year = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            year = 0;
        }
        return year;
    }
    public static double getTotalSale(String string){
        string = string.split(" ")[0].replaceAll(",", ".");
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static List<KMeansData> getKmeansData(List<Game> data){
        List<KMeansData> results = new ArrayList<>();
        int i = 0;
        for(Game element: data) {
            KMeansData kData = new KMeansData();
            int yearKmeans = getYear(element.getYear());
            //for the N/A values
            if (yearKmeans != 0) {
                kData.setYear(yearKmeans);
            } else continue;
            kData.setGlobalSale(getTotalSale(element.getGlobal_Sales()));
            results.add(kData);
        }
        return results;
    }
}
