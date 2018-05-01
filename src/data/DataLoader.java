package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static ArrayList<Game> LoadData() {
		ArrayList<Game> data = new ArrayList<>();
		int j=0;
		try {
			List<List<String>> dataOrig = CSVFileReader.readDataFile("src\\data\\GameSales.csv","," ,"",true);

			for (int i=1; i<dataOrig.size(); i+=1) {

                String rank = dataOrig.get(i).get(0);
                String name = dataOrig.get(i).get(1);
                String platform = dataOrig.get(i).get(2);
                String year = dataOrig.get(i).get(3);
                String genre = dataOrig.get(i).get(4);
                String publisher = dataOrig.get(i).get(5);
                String na_Sales = dataOrig.get(i).get(6) ;
                String eu_Sales = dataOrig.get(i).get(7);
                String jp_Sales = dataOrig.get(i).get(8);
                String other_Sales = dataOrig.get(i).get(9);
                String global_Sales = dataOrig.get(i).get(10).substring(0,dataOrig.get(i).get(10).length()-1);

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

}
