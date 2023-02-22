package com.example.geektrust;


public class MetroCard {
    private String metroCardNumber;
    private int balance;

    private Category categoryType;

    private Station stationType;


    public String getMetroCardNumber() {
        return metroCardNumber;
    }

    public void setMetroCardNumber(String metroCardNumber) {
        this.metroCardNumber = metroCardNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Category getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Category categoryType) {
        this.categoryType = categoryType;
    }

    public Station getStationType() {
        return stationType;
    }

    public void setStationType(Station stationType) {
        this.stationType = stationType;
    }
}
