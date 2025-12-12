package com.example.zorocafe;
public interface CartOperations {
    boolean addCartItem(String name,int qty);
    boolean removeCartItem(String name);
    boolean updateCartItem(int id,int qty);
    CartItem[] displayCart();
}
