package com.example.zorocafe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin  extends User{

    Scanner sc = new Scanner(System.in);

    public Admin(String username,String password,UserType type)
    {
        super(username,password,UserType.ADMIN);
    }

//    public void starterForAdmin(Management management,Inventory inventory)
//    {
//        int option;
//        boolean check = false;
//        while (!check) {
//            try {
//                System.out.println("======WELCOME TO ADMIN PORTAL ======");
//                System.out.println("1. Display Menu");
//                System.out.println("2. User Settings");
//                System.out.println("3. Inventory Settings");
//                System.out.println("Press number from 1-3 and 0 to exit");
//                option = sc.nextInt();
//                if (option == 1) {
//                    inventory.viewItems();
//                } else if (option == 2) {
//                    management.userSettings();
//                } else if (option == 3){
//                    management.InventorySettings();
//                }else if (option == 0) {
//                    check = true;
//                    return;
//                }
//
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid Input:Please choose number from 1-4 and 0 to exit");
//                sc.nextLine();
//            }
//        }
//    }
}
