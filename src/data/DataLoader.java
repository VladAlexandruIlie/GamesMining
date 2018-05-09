package data;


import KMeans.KMeansData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static ArrayList<Game> LoadData() {
        ArrayList<Game> data = new ArrayList<>();

        ArrayList<Integer> ranks = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> platforms = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<String> genres = new ArrayList<>();
        ArrayList<String> publishers = new ArrayList<>();
        ArrayList<Double> NAsales = new ArrayList<>();
        ArrayList<Double> EUsales = new ArrayList<>();
        ArrayList<Double> JPsales = new ArrayList<>();
        ArrayList<Double> OTsales = new ArrayList<>();
        ArrayList<Double> Gsales = new ArrayList<>();

        try {
            List<List<String>> dataOrig = CSVFileReader.readDataFile("src/data/GameSales.csv", "(,)", "", true);
            KMeansData kData = new KMeansData();

            for (int i = 1; i < dataOrig.size(); i += 1) {
                if (!dataOrig.get(i).get(3).equals("N/A") && !dataOrig.get(i).get(10).substring(0, dataOrig.get(i).get(10).length() - 1).equals("")) {
                    ranks.add(Integer.parseInt(dataOrig.get(i).get(0)));
                    names.add(dataOrig.get(i).get(1));
                    platforms.add(dataOrig.get(i).get(2));
                    years.add(Integer.parseInt(dataOrig.get(i).get(3)));
                    genres.add(dataOrig.get(i).get(4));
                    publishers.add(dataOrig.get(i).get(5));
                    NAsales.add(Double.parseDouble(dataOrig.get(i).get(6)));
                    EUsales.add(Double.parseDouble(dataOrig.get(i).get(7)));
                    JPsales.add(Double.parseDouble(dataOrig.get(i).get(8)));
                    OTsales.add(Double.parseDouble(dataOrig.get(i).get(9)));
                    Gsales.add(Double.parseDouble(dataOrig.get(i).get(10).substring(0, dataOrig.get(i).get(10).length() - 1)));
                }
            }
            for (int i = 0; i < ranks.size(); i++) {
                int rank = ranks.get(i);
                String name = names.get(i);
                String platform = platforms.get(i);
                int year = years.get(i);
                String genre = genres.get(i);
                String publisher = publishers.get(i);
                double na_Sales = NAsales.get(i);
                double eu_Sales = EUsales.get(i);
                double jp_Sales = JPsales.get(i);
                double other_Sales = OTsales.get(i);
                double global_Sales = Gsales.get(i);

                Game newGame = new Game(rank, name, platform, year, genre, publisher, na_Sales, eu_Sales, jp_Sales, other_Sales, global_Sales);
                data.add(newGame);
            }
                /*
				int yearKmeans = getYear(year);
                //for the N/A values
                if (yearKmeans!=0) {
                    kData.setYear(yearKmeans);
                } else continue;
                kData.setAmericaSale(getUsSale(na_Sales));
                kData.setEuSale(getEuSale(eu_Sales));
                kData.setGlobalSale(getTotalSale(global_Sales));
                */
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    private static int getYear(String string) {
        string = string.split(" ")[0];
        int year = 0;
        try {
            year = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            year = 0;
        }
        return year;
    }

    private static double getTotalSale(String string) {
        string = string.split(" ")[0];
        double sales = 0.0;
        sales = Double.parseDouble(string);
        return sales;
    }

    private static double getUsSale(String string) {
        string = string.split(" ")[0];
        double sales = 0.0;
        sales = Double.parseDouble(string);
        return sales;
    }

    private static double getEuSale(String string) {
        string = string.split(" ")[0];
        double sales = 0.0;
        sales = Double.parseDouble(string);
        return sales;
    }
}
