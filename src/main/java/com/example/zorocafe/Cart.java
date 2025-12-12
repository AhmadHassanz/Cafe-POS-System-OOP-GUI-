package com.example.zorocafe;

public class Cart implements CartOperations{
    private CartItem[] cartItems = new CartItem[5];
    private int count = 0;
    Inventory inventory;

    //Getters
    public int getCount()
    {
        return count;
    }
    public CartItem[] getCartItems()
    {
        return cartItems;
    }
    //Constructor
    public Cart(Inventory inventory)
    {
        this.inventory = inventory;
    }
    //To add item in cart
    @Override
    public boolean addCartItem(String name,int qty)
    {
        if(count >= cartItems.length)
        {
            capacityIncrease();
        }
        Item item = inventory.findItemByNameSimple(name);
        if(item == null) {
            return false;
        }
        //To avoid duplication in cart
        for(int i = 0; i < count;i++)
        {
            if(name.equalsIgnoreCase(cartItems[i].getItem().getName()))
            {
                int quantity = qty + cartItems[i].getQuantity();
                cartItems[i].setQuantity(quantity);
                return true;
            }
        }
        cartItems[count] = new CartItem(item,qty);
        count++;
        return true;
    }
    @Override
    public boolean removeCartItem(String name)
    {
        for(int i = 0; i < count;i++)
        {
            if(name.equalsIgnoreCase(cartItems[i].getItem().getName()))
            {
                for(int j = i; j < count - 1;j++)
                {
                    cartItems[j] = cartItems[j+1];
                }
                cartItems[count - 1] = null;
                count--;
                return true;
            }
        }

     return false;
    }

    @Override
    public CartItem[] displayCart()
    {
        CartItem[] result = new CartItem[count];
        for(int i = 0;i < count;i++)
        {
            result[i] = cartItems[i];
        }
        return result;
    }

    @Override
    public boolean updateCartItem(int id,int qty)
    {
        for (int i = 0; i < count; i++) {
            if (id == cartItems[i].getItem().getId()) {
                cartItems[i].setQuantity(qty);
                return true;
            }
        }
        return false;
    }

    public void capacityIncrease()
    {
        CartItem[] newArray = new CartItem[cartItems.length * 2];
        for(int i = 0;i < cartItems.length;i++)
        {
            newArray[i] = cartItems[i];
        }
        cartItems = newArray;
    }
}
