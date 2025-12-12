package com.example.zorocafe;
public class Item {
    private int id;
    private String name;
    private double price;
    private Category category;

    public Item(int id,String name,double price,Category category)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    //Setters
    public  void setName(String name)
    {
        this.name = name;
    }
    public  void setID(int id)
    {
        this.id = id;
    }
    public  void setPrice(double price)
    {
        this.price = price;
    }
    public void setCategory(Category category)
    {
        this.category = category;
    }

    //Getters
    public  int getId()
    {
        return id;
    }
    public  String getName()
    {
        return name;
    }
    public  double getPrice()
    {
        return price;
    }
    public Category getCategory()
    {
        return category;
    }

    //Item Representation
    public String toString()
    {
        return String.format("%-5d %-10s PKR %.2f %-10s",id,name,price,category);
    }
}
