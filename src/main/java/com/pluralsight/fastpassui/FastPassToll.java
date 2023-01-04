package com.pluralsight.fastpassui;

public class FastPassToll {

    String fastPassId;
    String stationId;
    Float amountPaid;

    public FastPassToll(){}

    public FastPassToll(String fastPassId, String stationId, Float amountPaid){
        this.fastPassId = fastPassId;
        this.stationId = stationId;
        this.amountPaid = amountPaid;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getFastPassId() {
        return fastPassId;
    }

    public void setFastPassId(String fastPassId) {
        this.fastPassId = fastPassId;
    }
}
