package com.example.andriodfinalproject;

public class MilkMan {
    String name;
    String contact;
    String orderNo;

    public MilkMan(String name,String cont,String ord) {
        this.name = name;
        this.contact = cont;
        this.orderNo=ord;
    }
    public String getName() {
        return name;
    }
    public void setName(String nm) {
        this.name = nm;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String cont)
    {
        this.contact = cont;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String ord)
    {
        this.orderNo = ord;
    }
    @Override
    public String toString()
    { return "Name : "+name+" Contact : "+contact;}
}
