package com.example.zorocafe;
public class Bill {
    private double subTotal;
    private double Total;
    private double tax;
    private CartItem[] item;

    //Constructor
    public Bill(double subTotal,double Total,double tax,CartItem[] item)
    {
        this.subTotal = subTotal;
        this.Total = Total;
        this.item = item;
        this.tax = tax;
    }

    //Getters
    public double getSubTotal()
    {
        return subTotal;
    }

    public double getTotal()
    {
        return Total;
    }

    public CartItem[] getItem()
    {
        return item;
    }
    public double getTax()
    {
        return tax;
    }
    //Bill
    @Override
    public String toString()
    {
        return String.format("SubTotal: %s \nTax: %s \nTotal: %s",subTotal,tax,Total);
    }
}
