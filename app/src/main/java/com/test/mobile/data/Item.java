package com.test.mobile.data;

public class Item {
    private String ID;
    private String status;
    private String name;
    private double quantity = 0;
    private String site;
    private String WH;//warehouse
    private String location;
    private String batch;
    private String serial;

    public Item(String id, String status, String name, double quantity,
                String site, String WH, String location,
                String batch, String serial) {
        this.ID = id;
        this.status = status;
        this.name = name;
        this.quantity = quantity;
        this.site = site;
        this.WH = WH;
        this.location = location;
        this.batch = batch;
        this.serial = serial;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getWH() {
        return WH;
    }

    public void setWH(String WH) {
        this.WH = WH;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return String.format("\n%d: Status:%s,  Item:%s, qty:%f, site:%s, WH:%s, Loc:%s, batch:%s, serial:%s",
                ID,status,name,quantity,site,WH,location,batch,serial);
    }
}
