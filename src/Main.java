import data.DataLoader;
import data.Game;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main (String args[]){
        List<Game> data = DataLoader.LoadData();

        System.out.println(data);

    }
}
