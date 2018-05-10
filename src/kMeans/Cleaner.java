package kMeans;

import data.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Transforms the data from Strings to the preffered type. Loader + cleaning happens here as well.
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
    public static double getUsSale(String string){
        string = string.split(" ")[0];
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static double getEuSale(String string){
        string = string.split(" ")[0];
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static List<KMeansData> getKmeansData(List<Game> data){
        List<KMeansData> results = new ArrayList<>();
        for(Game elemnet: data) {
            KMeansData kData = new KMeansData();
            int yearKmeans = getYear(elemnet.getYear());
            //for the N/A values
            if (yearKmeans != 0) {
                kData.setYear(yearKmeans);
            } else continue;
            kData.setAmericaSale(getUsSale(elemnet.getNa_Sales()));
            kData.setEuSale(getEuSale(elemnet.getEu_Sales()));
            kData.setGlobalSale(getTotalSale(elemnet.getGlobal_Sales()));
            if(kData.getGlobalSale() == 0.0) continue;

            results.add(kData);
        }
        return results;
    }
}
