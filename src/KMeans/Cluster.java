package KMeans;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    private List<KMeans.KMeansData> elements;
    private KMeans.KMeansData centroid;

    public Cluster() {
        elements = new ArrayList<>();
        centroid = null;
    }

    public List<KMeans.KMeansData> getElements() {
        return elements;
    }

    public void setElements(List<KMeans.KMeansData> elements) {
        this.elements = elements;
    }

    public KMeans.KMeansData getCentroid() {
        return centroid;
    }

    public void setCentroid(KMeans.KMeansData centroid) {
        this.centroid = centroid;
    }

    public void addElement(KMeans.KMeansData ns) {
        elements.add(ns);
    }
}
