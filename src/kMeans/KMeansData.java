package kMeans;

/**
 * Data used for Kmeans. Normalised.
 */
public class KMeansData {
    private int year;
    private double globalSale;
    private double euSale;
    private double americaSale;
    private String publisher;


    public KMeansData(int year, double globalSale, double euSale, double americaSale, String publisher) {
        this.year = year;
        this.globalSale = globalSale;
        this.euSale = euSale;
        this.americaSale = americaSale;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "year --> " + year +
                ", globalSale --> " + globalSale;
    }

    public KMeansData() { }

    public int getYear() { return year; }

    public void setYear(int year) {
        this.year = year;
    }

    public double getGlobalSale() {
        return globalSale;
    }

    public void setGlobalSale(double globalSale) {
        this.globalSale = globalSale;
    }

    public double getEuSale() {
        return euSale;
    } //just in case we need to use the other sale data

    public void setEuSale(double euSale) {
        this.euSale = euSale;
    }

    public double getAmericaSale() {
        return americaSale;
    }

    public void setAmericaSale(double americaSale) {
        this.americaSale = americaSale;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
