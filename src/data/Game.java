package data;
import java.util.ArrayList;

public class Game {

    private String rank;
    private String name;
    private String platform;
    private String year;
    private String genre;
    private String publisher;
    private String na_Sales;
    private String eu_Sales;
    private String jp_Sales;
    private String other_Sales;
    private String global_Sales;

    public Game(String rank, String name, String platform, String year, String genre, String publisher,
                String na_Sales, String eu_Sales, String jp_Sales, String other_Sales, String global_Sales) {
        this.rank = rank;
        this.name = name;
        this.platform = platform;
        this.year = year;
        this.genre = genre;
        this.publisher = publisher;
        this.na_Sales = na_Sales;
        this.eu_Sales = eu_Sales;
        this.jp_Sales = jp_Sales;
        this.other_Sales = other_Sales;
        this.global_Sales = global_Sales;
    }

    @Override
    public String toString() {

        return String.format("Rank: %6s | Name: %30s | Platform %7s | Y: %5s | G: %10s | Pub: %10s | NA: %4s | EU: %4s | JP: %4s | Other: %4s | Global: %4s "
               , this.rank, this.name, this.platform, this.year, this.genre, this.publisher,
                this.na_Sales, this.eu_Sales, this.jp_Sales, this.other_Sales, this.global_Sales);
    }

    public String getRank() {
        return rank;
    }

    public String getPlatform() {
        return platform;
    }

    public String getYear() { return year; }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getNa_Sales() {
        return na_Sales;
    }

    public String getEu_Sales() {
        return eu_Sales;
    }

    public String getJp_Sales() {
        return jp_Sales;
    }

    public String getOther_Sales() {
        return other_Sales;
    }

    public String getGlobal_Sales() {
        return global_Sales;
    }

    public String getName() {
        return name;
    }
}
