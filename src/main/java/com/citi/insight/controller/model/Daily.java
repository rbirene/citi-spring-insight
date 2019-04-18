package com.citi.insight.controller.model;

import java.util.List;

public class Daily {
    private String ticker;
    private double openPrice;
    private double closePrice;
    private double highPrice;
    private double lowPrice;
    private int volume;
    private double absoluteDifference;
    private double percentageDifference;

    public Daily(){
    }

    public Daily(String ticker, double openPrice, double closePrice, double highPrice, double lowPrice, int volume, double absoluteDifference, double percentageDifference){
        this.ticker=ticker;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
        this.absoluteDifference = absoluteDifference;
        this.percentageDifference = percentageDifference;
    }

    //Accessor methods
    public String getTicker(){
        return ticker;
    }
    public double getOpenPrice(){
        return openPrice;
    }
    public double getClosePrice(){
        return closePrice;
    }
    public double getHighPrice(){
        return highPrice;
    }
    public double getLowPrice(){
        return lowPrice;
    }
    public double getVolume(){
        return volume;
    }
    public double getAbsoluteDifference(){
        return absoluteDifference;
    }
    public double getPercentageDifference(){
        return percentageDifference;
    }


    //Mutator methods:
    public void setTicker(String inTicker){
        this.ticker = inTicker;
    }
    public void setOpenPrice(double inOpenPrice){
        this.openPrice = inOpenPrice;
    }
    public void setClosePrice(double inClosePrice){
        this.closePrice = inClosePrice;
    }
    public void setHighPrice(double inHighPrice){
        this.highPrice = inHighPrice;
    }
    public void setLowPrice(double inLowPrice){
        this.lowPrice = inLowPrice;
    }
    public void setVolume(int inVolume){
        this.volume = inVolume;
    }
    public void setAbsoluteDifference(double inAbsoluteDifference){
        this.absoluteDifference = inAbsoluteDifference;
    }
    public void setPercentageDifference(double inPercentageDifference){
        this.percentageDifference = inPercentageDifference;
    }

}
