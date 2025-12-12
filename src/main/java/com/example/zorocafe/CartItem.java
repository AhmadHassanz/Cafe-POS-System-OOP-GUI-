package com.example.zorocafe;
public class CartItem {
    private Item item;
    private int quantity;

    public CartItem(Item item,int quantity)
    {
        this.item = item;
        this.quantity = quantity;
    }
    //Setters
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    //Getters
    public Item getItem()
    {
        return item;
    }
    public int getQuantity()
    {
        return quantity;
    }

    public double getTotalPrice()
    {
        return (double) quantity * item.getPrice();
    }
    //toString to Represent Cart Item
    @Override
    public String toString()
    {
        double TotalPrice = getTotalPrice();
        return String.format("Qty: %d | Item: %s | Unit: %s | Total Price %s",quantity,item,item.getPrice(),TotalPrice);
    }
}
