package com.test.mobile.data;

public class Item {
    private int id = 0;
    private String name;
    private int quantity = 0;
    private String site;
    private String WH;//warehouse
    private String location;
    private String batch;
    private String serial;

    public Item(int autoNum, String name, int quantity,
                String site, String WH, String location,
                String batch, String serial) {
        this.id = autoNum;
        this.name = name;
        this.quantity = quantity;
        this.site = site;
        this.WH = WH;
        this.location = location;
        this.batch = batch;
        this.serial = serial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
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
        return String.format("\n%d: Item:%s, qty:%d, site:%s, WH:%s, Loc:%s, batch:%s, serial:%s",
                id,name,quantity,site,WH,location,batch,serial);
    }
}
