package data;


import KMeans.KMeansData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

	public static ArrayList<Game> LoadData() {
		ArrayList<Game> data = new ArrayList<>();
		int j=0;
		try {
			List<List<String>> dataOrig = CSVFileReader.readDataFile("src/data/GameSales.csv","," ,"",true);
			KMeansData kData = new KMeansData();

			for (int i=1; i<dataOrig.size(); i+=1) {

				String rank = dataOrig.get(i).get(0);
				String name = dataOrig.get(i).get(1);
				String platform = dataOrig.get(i).get(2);
				String year = dataOrig.get(i).get(3);
				int yearKmeans = getYear(year);
				//for the N/A values
				if (yearKmeans!=0) {
					kData.setYear(yearKmeans);
				} else continue;
				String genre = dataOrig.get(i).get(4);
				String publisher = dataOrig.get(i).get(5);
				String na_Sales = dataOrig.get(i).get(6) ;
				kData.setAmericaSale(getUsSale(na_Sales));
				String eu_Sales = dataOrig.get(i).get(7);
				kData.setEuSale(getEuSale(eu_Sales));
				String jp_Sales = dataOrig.get(i).get(8);
				String other_Sales = dataOrig.get(i).get(9);
				String global_Sales = dataOrig.get(i).get(10).substring(0,dataOrig.get(i).get(10).length()-1);
				kData.setGlobalSale(getTotalSale(global_Sales));


				Game newGame = new Game(rank, name, platform, year, genre, publisher, na_Sales, eu_Sales, jp_Sales,other_Sales,global_Sales);
				j++;
				data.add(newGame);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	private static int getYear(String string){
		string = string.split(" ")[0];
		int year = 0;
		try {
			year = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			year = 0;
		}
		return year;
	}
	private static double getTotalSale(String string){
		string = string.split(" ")[0];
		double sales = 0.0;
		sales = Double.parseDouble(string);
		return sales;
	}
	private static double getUsSale(String string){
		string = string.split(" ")[0];
		double sales = 0.0;
		sales = Double.parseDouble(string);
		return sales;
	}

	private static double getEuSale(String string){
		string = string.split(" ")[0];
		double sales = 0.0;
		sales = Double.parseDouble(string);
		return sales;
	}
}
