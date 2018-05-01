package Knn;

import java.util.ArrayList;

public class KNNData {

    String name;
    String platform;
    String year;
    String genre;
    String publisher;
    String na_Sales;
    String eu_Sales;
    String jp_Sales;
    String other_Sales;
    String global_Sales;

    public KNNData(String name, String platform, String year, String genre, String publisher, String na_Sales, String eu_Sales, String jp_Sales, String other_Sales, String global_Sales){
        this.name = name;
        this.platform = platform;
        this.year = year;
        this.publisher = publisher;
        this.na_Sales = na_Sales;
        this.eu_Sales = eu_Sales;
        this.jp_Sales = jp_Sales;
        this.other_Sales = other_Sales;
        this.global_Sales = global_Sales;

        this.genre = genre;
    }

    @Override
    public String toString(){
        String output = String.format("Name: %s | Platform %s | Year %s | Publisher %s | NA: %s | EU: %s | JP: %s | Other: %s | Global: %s | Known Genre: "
                , this.name, this.platform, this.year, this.publisher,
                this.na_Sales, this.eu_Sales, this.jp_Sales, this.other_Sales, this.global_Sales, this.genre);

        output += " -> Genre: " + this.genre;

        return output;
    }

    public String getName()         { return name; }
    public String getPlatform()     { return platform; }
    public String getYear()         { return year; }
    public String getGenre()        { return genre; }
    public String getPublisher()    { return publisher; }
    public String getNa_Sales()     { return na_Sales; }
    public String getEu_Sales()     { return eu_Sales; }
    public String getJp_Sales()     { return jp_Sales; }
    public String getOther_Sales()  { return other_Sales; }
    public String getGlobal_Sales() { return global_Sales; }
}
