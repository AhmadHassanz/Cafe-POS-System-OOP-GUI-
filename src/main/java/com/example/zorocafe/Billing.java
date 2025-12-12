package com.example.zorocafe;

import java.io.IOException;
import java.io.FileWriter;

public class Billing {
    private Cart cart;

    Billing(Cart cart)
    {
        this.cart = cart;
    }

    public Bill makeBill()
    {
        CartItem[] items = cart.displayCart();
        double subTotal = 0;
        if(cart.getCount() == 0)
        {
            return null;
        }
        for(int i = 0; i < cart.getCount();i++)
        {
            subTotal += items[i].getTotalPrice();
        }

        final double tax = 0.15;
        double total = (tax * subTotal) + subTotal;
      Bill bill = new Bill(subTotal,total,tax,items);
      saveBill(bill);
      return bill;
    }

    public void saveBill(Bill bill)
    {
        String path = "D:\\Data\\bills.csv";
        if(bill == null)
        {
            return;
        }
        CartItem[] items = bill.getItem();
        try(FileWriter fw = new FileWriter(path,true))
        {
            for(int i = 0; i < items.length;i++)
            {
                CartItem c = items[i];
                if(c == null)
                {
                    break;
                }
                fw.write(c.getItem().getName() + ",");
                fw.write(c.getItem().getPrice() + ",");
                fw.write(c.getQuantity() + ",");
                fw.write(c.getTotalPrice() + "");
            }
            fw.write("\n SubTotal, " + bill.getSubTotal());
            fw.write("\n Tax, " + bill.getTax() * 100 + "%");
            fw.write("\n Total, " + bill.getTotal());
            fw.write("\n\n");
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
