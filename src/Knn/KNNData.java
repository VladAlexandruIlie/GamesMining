package Knn;
import java.util.ArrayList;

public class KNNData {
    private String name;
    private String platform;
    private String genre;
    private String publisher;
    private Double year;
    private Double na_Sales;
    private Double eu_Sales;
    private Double jp_Sales;
    private Double other_Sales;
    private Double global_Sales;

    public KNNData(String name, String platform, Double year, String genre, String publisher, double na_Sales, double eu_Sales, double jp_Sales, double other_Sales, double global_Sales){
        this.name = name;
        this.platform = platform;
        this.publisher = publisher;
        this.year = year;
        this.na_Sales = na_Sales;
        this.eu_Sales = eu_Sales;
        this.jp_Sales = jp_Sales;
        this.other_Sales = other_Sales;
        this.global_Sales = global_Sales;
        this.genre = genre;
    }

    public String getName()         { return name; }
    public String getPlatform()     { return platform; }
    public String getGenre()        { return genre; }
    public String getPublisher()    { return publisher; }
    public Double getYear()         { return year; }
//    public Double getNa_Sales()     { return na_Sales; }
//    public Double getEu_Sales()     { return eu_Sales; }
//    public Double getJp_Sales()     { return jp_Sales; }
//    public Double getOther_Sales()  { return other_Sales; }
//    public Double getGlobal_Sales() { return global_Sales; }

    public ArrayList<Object> getAttributes() {
        ArrayList<Object> attributes = new ArrayList<>();
        attributes.add(this.genre);
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
        return String.format("Name: %30.30s | G: %5.5s | Pb: %10.10s | Y: %5.3f | NA: %3.2f | EU: %3.2f | JP: %3.2f | Other: %3.2f | G: %3.2f | Platform: %5s >"
                , this.name, this.genre, this.publisher, this.year,
                this.na_Sales, this.eu_Sales, this.jp_Sales, this.other_Sales, this.global_Sales, this.platform);
    }
}
