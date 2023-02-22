package com.example.geektrust;

import java.util.List;
import java.util.Map;

public class Station {
    private Map<String, Integer> stationTypeCollection;
    private Map<String, Integer> stationTypeDiscount;
    //private int collection;


    public Map<String, Integer> getStationTypeDiscount() {
        return stationTypeDiscount;
    }

    public void setStationTypeDiscount(Map<String, Integer> stationTypeDiscount) {
        this.stationTypeDiscount = stationTypeDiscount;
    }

    public Map<String, Integer> getStationTypeCollection() {
        return stationTypeCollection;
    }

    public void setStationTypeCollection(Map<String, Integer> stationTypeCollection) {
        this.stationTypeCollection = stationTypeCollection;
    }
}
