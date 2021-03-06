package kMeans;

import java.util.ArrayList;
import java.util.List;

/**
 * Has the data of the cluster.
 */
public class Cluster {

    private List<KMeansData> elements;
    private KMeansData centroid;

    public Cluster() {
        elements = new ArrayList<>();
        centroid = null;
    }

    public List<KMeansData> getElements() {
        return elements;
    }

    public KMeansData getCentroid() {
        return centroid;
    }

    public void setCentroid(KMeansData centroid) {
        this.centroid = centroid;
    }

    public void addElement(KMeansData ns) {
        elements.add(ns);
    }
}
