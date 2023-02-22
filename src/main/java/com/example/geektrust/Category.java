package com.example.geektrust;

//import java.util.HashMap;
import java.util.Map;

public class Category {
  private Map<String, Map<String, Integer>> count;

    public Map<String, Map<String, Integer>> getCount() {
        return count;
    }

    public void setCount(Map<String, Map<String, Integer>> count) {
        this.count = count;
    }
}
