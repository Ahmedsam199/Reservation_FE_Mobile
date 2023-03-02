package com.example.loginddesign;

public class ResrvationModel {
    int FlightID;
    int Cost;
    String FlightName;
    String From;
    String To;

    public ResrvationModel(int FlightID, String FlightName, int Cost, String From, String To) {
        this.FlightID = FlightID;
        this.FlightName = FlightName;
        this.Cost = Cost;
        this.From=From;
        this.To=To;

    }
    public int getFlightID() {
        return FlightID;
    }
    public void setFlightID(int FlightID) {
        this.FlightID = FlightID;
    }
    public String getFlightName() {
        return FlightName;
    }
    public void setFlightName(String FlightName) {
        FlightName = FlightName;
    }
    public int getCost() {
        return Cost;
    }
    public void setCost(int Cost) {
        Cost = Cost;
    }
    public String getFrom() {
        return From;
    }
    public void setFrom(String From) {
        From = From;
    }
    public String getTo() {
        return To;
    }
    public void setTo(String To) {
        To = To;
    }
}