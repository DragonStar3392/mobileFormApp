package com.test.mobile.data;

public class Item {
    private String name;
    private int quantity = 0;
    private String site;
    private String WH;//warehouse
    private int location = 0;
    private int batch = 0;
    private int serial = 0;

    public Item(String name, int quantity,
                String site, String WH, int location,
                int batch, int serial) {
        this.name = name;
        this.quantity = quantity;
        this.site = site;
        this.WH = WH;
        this.location = location;
        this.batch = batch;
        this.serial = serial;
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

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return String.format("\nItem:%s, qty:%d, site:%s, WH:%s, Loc:%d, batch:%d, serial:%d",
                name,quantity,site,WH,location,batch,serial);
    }
}
