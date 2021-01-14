package com.example.andriodfinalproject;

public class history1 {
    String name;
    String Quantity;
    String orderNo;
    String Price;

    public history1(String nme,String cont,String ord,String prc) {
        this.name = nme;
        this.Quantity = cont;
        this.orderNo=ord;
        this.Price=prc;
    }
    public String getName() {
        return name;
    }
    public void setName(String nm) {
        this.name = nm;
    }
    public String getQuantity() {
        return Quantity;
    }
    public void setQuantity(String cont)
    {
        this.Quantity = cont;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String ord)
    {
        this.orderNo = ord;
    }
    public String getPrice() {
        return Price;
    }
    public void setPrice(String pr) {
        this.Price = pr;
    }
    @Override
    public String toString()
    { return "Name : "+name+" Contact : "+Quantity;}

}
