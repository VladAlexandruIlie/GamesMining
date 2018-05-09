package data;
import java.util.ArrayList;

public class Game {

    private int rank;
    private String name;
    private String platform;
    private int year;
    private String genre;
    private String publisher;
    private double na_Sales;
    private double eu_Sales;
    private double jp_Sales;
    private double other_Sales;
    private double global_Sales;

    public Game(int rank, String name, String platform, int year, String genre, String publisher,
                double na_Sales, double eu_Sales, double jp_Sales, double other_Sales, double global_Sales) {
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
        String output = String.format("Rank: %6s | Name: %30s | Platform %7s | Y: %5s | G: %10s | Pub: %10s | NA: %4s | EU: %4s | JP: %4s | Other: %4s | Global: %4s "
               ,this.rank, this.name, this.platform, this.year, this.genre, this.publisher,
                this.na_Sales, this.eu_Sales, this.jp_Sales, this.other_Sales, this.global_Sales);

        return output;
    }

    public int getRank() {
        return rank;
    }

    public String getPlatform() {
        return platform;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public Double getNa_Sales() {
        return na_Sales;
    }

    public Double getEu_Sales() {
        return eu_Sales;
    }

    public Double getJp_Sales() {
        return jp_Sales;
    }

    public Double getOther_Sales() {
        return other_Sales;
    }

    public Double getGlobal_Sales() {
        return global_Sales;
    }

    public String getName() {
        return name;
    }
}
