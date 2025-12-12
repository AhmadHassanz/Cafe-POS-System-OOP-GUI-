package com.example.zorocafe;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Management{
    User[] users = new User[5];
    Inventory inventory = new Inventory();
    Cart cart = new Cart(inventory);
    Path path = Path.of("D:\\Data\\users.csv");
    int count = 0;

    Management() {
        defaultAccount();
        loadRecords();
    }
    public User login(String username,String password)
    {
        for(int i = 0; i < count; i++)
        {
            if(username.equals(users[i].getUsername()) && password.equals(users[i].getPassword()))
            {
                return users[i];
            }
        }
        return null;
    }
    public boolean addUser(String username,String password,UserType type) {
        if(count >= users.length)
        {
            capacityIncrease();
        }

        for (int i = 0; i < count; i++) {
            if (username.equals(users[i].getUsername())) {
                return false;
            }
        }

        //Storing Object Based on type in user array
        if(type == UserType.ADMIN)
        {
            users[count] = new Admin(username,password,type);
        } else {
            users[count] = new Staff(username,password,type);
        }
        count++;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile(), true));
            bw.write(username + "," + password + "," + type);
            bw.newLine();
            bw.close();
            System.out.println("Account created successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


public void defaultAccount() {
    Path p = Path.of("D:\\Data");

    //Checking if Data Folder exists or not
    try {
        if (!Files.exists(p)) {
            Files.createDirectories(p);
        }
    } catch (IOException e) {
        System.out.println("Error");
    }
    //Default account creation and storage in file
    if (!Files.exists(path)) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile()));
            users[0] = new Admin("admin","12345",UserType.ADMIN);
            bw.write(users[0].getUsername() + "," + users[0].getPassword() + "," + users[0].getRole());
            bw.newLine();
            bw.close();
            count++;
            System.out.println("Default file created successfully with username 'admin' and password '12345' ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
public void loadRecords() {
    try {
        count = 0;
        BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
        String text;
        while ((text = br.readLine())  != null)
        {
            if(count>= users.length)
            {
                capacityIncrease();
            }
            String[] parts = text.split(",");
            UserType type = UserType.valueOf(parts[2]);

            if(type == UserType.ADMIN)
            {
                users[count] = new Admin(parts[0],parts[1],type);
            }else
            {
                users[count] = new Staff(parts[0],parts[1],type);
            }
            count++;
        }
        br.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }catch (IOException e)
    {
        e.printStackTrace();
    }
}
public boolean deleteRecords(String username)
{
        for (int i = 0; i < count; i++) {
            if (username.equals(users[i].getUsername())) {
                for (int j = i; j < count - 1; j++)
                {
                    users[j] = users[j + 1];
                }
                users[count - 1] = null;
                count--;
                rewriteFile();
                return true;
            }
        }
        return false;
    }

public void rewriteFile()
{
    try
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile()));
        for(int i = 0;i < count;i++)
        {
            bw.write(users[i].getUsername() + "," + users[i].getPassword() + "," + users[i].getRole());
            bw.newLine();
        }
        bw.close();
    }catch (FileNotFoundException e)
    {
        e.printStackTrace();
    }catch (IOException e)
    {
        e.printStackTrace();
    }
}
public void updateRecords(String username,String newUsername,String newPassword) {
        for (int i = 0; i < count; i++) {
            if (username.equals(users[i].getUsername())) {
                users[i].setUsername(newUsername);
                users[i].setPassword(newPassword);
                rewriteFile();
            }
        }

}
public User[] printAllUser()
{
    User[] result = new User[count];
    for(int i = 0; i < count;i++)
    {
        result[i] = users[i];
    }
    return result;
}

public void capacityIncrease()
{
    User[] newArray = new User[users.length * 2];
    for(int i = 0;i < count;i++)
    {
        newArray[i] = users[i];
    }
    users = newArray;
}

//public void userSettings()
//{
//    System.out.println("======User Settings======");
//    System.out.println("1.Add user");
//    System.out.println("2.Delete user");
//    System.out.println("3.Update user");
//    System.out.println("4.Display ALL users");
//
//    int option;
//    Scanner sc = new Scanner(System.in);
//
//    boolean check = false;
//    while (!check) {
//        try {
//            System.out.println("Press number from 1-4 and 0 to exit");
//            option = sc.nextInt();
//            if (option == 1) {
//                addUser();
//            } else if (option == 2) {
//                deleteRecords();
//            } else if (option == 3){
//                updateRecords();
//            }else if(option == 4){
//                printAllUser();
//            }else if (option == 0) {
//                check = true;
//                return;
//            }
//
//        } catch (InputMismatchException e) {
//            System.out.println("Invalid Input:Please choose number from 1-4 and 0 to exit");
//            sc.nextLine();
//        }
//    }
//}
//
//public void InventorySettings()
//{
//    int option;
//    Scanner sc = new Scanner(System.in);
//
//    boolean check = false;
//    while (!check) {
//        try {
//            System.out.println("======Inventory Settings======");
//            System.out.println("1. Add Item");
//            System.out.println("2. Delete Item");
//            System.out.println("3. Search Item by name");
//            System.out.println("4. Update Item");
//            System.out.println("Press number from 1-4 and 0 to exit");
//            option = sc.nextInt();
//            if (option == 1) {
//                inventory.addItem();
//            } else if (option == 2) {
//                inventory.removeItem();
//            } else if (option == 3){
//                inventory.findItemByName();
//            }else if (option == 4){
//                inventory.updateItem();
//            }else if (option == 0) {
//                check = true;
//                return;
//            }
//
//        } catch (InputMismatchException e) {
//            System.out.println("Invalid Input:Please choose number from 1-3 and 0 to exit");
//            sc.nextLine();
//        }
//    }
//}


}
