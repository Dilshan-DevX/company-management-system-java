package com.nex.cms.model;

public class InvoiceItem {

    private String strockId;
    private String brand;
    private String name;
    private String qty;
    private String sellingPrice;
    private String mfd;
    private String exp;


    public String getStrockId() {
        return strockId;
    }

    public void setStrockId(String strockId) {
        this.strockId = strockId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getMfd() {
        return mfd;
    }

    public void setMfd(String mfd) {
        this.mfd = mfd;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

}
