package Knn;
import java.util.ArrayList;

public class KNNData {

    String name;
    String platform;
    String genre;
    String publisher;
    Double year;
    Double na_Sales;
    Double eu_Sales;
    Double jp_Sales;
    Double other_Sales;
    Double global_Sales;

    public KNNData(String name, String platform, String year, String genre, String publisher, String na_Sales, String eu_Sales, String jp_Sales, String other_Sales, String global_Sales){
        this.name = name;

        this.platform = platform;
        this.publisher = publisher;

        this.year = Double.parseDouble(year);

        this.na_Sales = Double.parseDouble(na_Sales);
        this.eu_Sales = Double.parseDouble(eu_Sales);
        this.jp_Sales = Double.parseDouble(jp_Sales);
        this.other_Sales = Double.parseDouble(other_Sales);
        this.global_Sales = Double.parseDouble(global_Sales);

        this.genre = genre;
    }

    public String getName()         { return name; }
    public String getPlatform()     { return platform; }
    public String getGenre()        { return genre; }
    public String getPublisher()    { return publisher; }
    public Double getYear()         { return year; }
    public Double getNa_Sales()     { return na_Sales; }
    public Double getEu_Sales()     { return eu_Sales; }
    public Double getJp_Sales()     { return jp_Sales; }
    public Double getOther_Sales()  { return other_Sales; }
    public Double getGlobal_Sales() { return global_Sales; }

    public ArrayList<Object> getAttributes() {
        ArrayList<Object> attributes =  new ArrayList<Object>();
        attributes.add(this.platform);
        attributes.add(this.publisher);
        attributes.add(this.year);
        attributes.add(this.na_Sales);
        attributes.add(this.eu_Sales);
        attributes.add(this.jp_Sales);
        attributes.add(this.other_Sales);
        attributes.add(this.global_Sales);

        return attributes;
    }

    @Override
    public String toString(){
        String output = String.format("Name: %30.30s | P: %7.7s | Y: %5s | Pb: %10.10s | NA: %6s | EU: %6s | JP: %6s | Other: %6s | G: %6s | Known Genre: %10s >"
                , this.name, this.platform, this.year, this.publisher,
                this.na_Sales, this.eu_Sales, this.jp_Sales, this.other_Sales, this.global_Sales, this.genre);

        return output;
    }
}
