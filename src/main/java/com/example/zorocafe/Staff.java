package com.example.zorocafe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Staff extends User{

    public Staff(String username,String password,UserType type)
    {
        super(username,password,UserType.STAFF);
    }

//    public void starterForStaff(Inventory inventory,Cart cart)
//    {
//        Billing b = new Billing(cart);
//        Scanner sc = new Scanner(System.in);
//        System.out.println("========WELCOME TO RORONOA ZORO CAFE=========");
//        System.out.println("===================MENU======================");
//        inventory.viewItems();
//        while (true) {
//            try {
//                System.out.println("1.Add Item to Cart");
//                System.out.println("2.Remove Item from Cart");
//                System.out.println("3.Display Cart");
//                System.out.println("4.Update Quantity of CartItem");
//                System.out.println("5.Generate Bill");
//                System.out.print("Enter Option to proceed (0 to exit):");
//                int option = sc.nextInt();
//                if(option == 1)
//                {
//                    cart.addCartItem();
//                }else if(option == 2)
//                {
//                    cart.removeCartItem();
//                } else if (option == 3) {
//                    cart.displayCart();
//                } else if (option == 4) {
//                    cart.updateCartItem();
//                } else if(option == 5) {
//                    Bill a=  b.makeBill();
//                    System.out.println(a);
//                }else if(option == 0)
//                {
//                    return;
//                }else
//                {
//                    System.out.println("Please Enter valid number");
//                }
//
//            } catch (InputMismatchException e) {
//                System.out.println("Please Enter valid number");
//                sc.nextLine();
//            }
//        }
//    }
}
