package com.example.zorocafe;

import java.io.*;
import java.nio.file.Path;

public class Inventory implements InventoryOperations{
    private Item[] items = new Item[10];
    int count = 0;
    private int uniqueId = 1;

    Inventory()
    {
        loadFile();
    }

    @Override
    public void addItem(String name,double price,Category category)
    {
        if(count >= items.length)
        {
            capIncrease();
        }

        items[count] = new Item(uniqueId,name,price,category);
        count++;
        uniqueId++;
        saveFile();

        System.out.println("Item added successfully");

    }
    @Override
    public boolean removeItem(String name)
    {
            for (int i = 0; i < count; i++) {
                if (name.equalsIgnoreCase(items[i].getName())) {
                    for (int j = i; j < count - 1; j++) {
                        items[j] = items[j + 1];
                    }
                    items[count - 1] = null;
                    count--;
                    saveFile();
                    return true;
                }
            }
            return false;
    }

    @Override
    public Item[] viewItems()
    {
        Item[] result = new Item[count];
        for(int i = 0; i < count;i++)
        {
            result[i] = items[i];
        }
        return result;
    }

    @Override
    public Item findItemByName(String name) {

            for (int i = 0; i < count; i++) {
                if (name.equalsIgnoreCase(items[i].getName()))
                {
                    return items[i];
                }
            }
            return null;
    }

    public void saveFile()
    {
        Path path = Path.of("D:\\Data\\items.csv");
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile()));
            for(int i = 0; i < count;i++)
            {
                bw.write(items[i].getId() + "," + items[i].getName() + "," + items[i].getPrice() + "," + items[i].getCategory());
                bw.newLine();
            }
            bw.close();

        }catch (IOException e)
        {
            System.out.println("File cant be written");
        }

    }

    public void loadFile()
    {
        Path path = Path.of("D:\\Data\\items.csv");
        count = 0;
        int maxId = 0;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
            String text;
            while ((text = br.readLine()) != null)
            {
                String[] part = text.split(",");
                int id = Integer.parseInt(part[0]);
                String name = part[1];
                double price = Double.parseDouble(part[2]);
                Category category = Category.valueOf(part[3]);

                items[count] = new Item(id,name,price,category);
                count++;
                if(id >= maxId)
                {
                    maxId = id;
                }
            }
            uniqueId = maxId + 1;
            br.close();

        }catch (FileNotFoundException e)
        {
            System.out.print("");
        }catch (IOException e)
        {
            System.out.println("File cant be read");
        }
    }

    //To increase capacity of Item array
    public void capIncrease()
    {
        Item[] newArray = new Item[items.length * 2];
        for(int i = 0;i < items.length;i++)
        {
            newArray[i] = items[i];
        }
        items = newArray;
    }

   public boolean updateItem(int id,String newName,double newPrice)
   {
       for(int i = 0; i < count;i++)
       {
           if(id == items[i].getId())
           {
               items[i].setName(newName);
               items[i].setPrice(newPrice);
               saveFile();
               return true;
           }
       }
       return false;
   }
    //find item by name for cart purpose
    public Item findItemByNameSimple(String name)
    {
        for(int i = 0; i < count;i++)
        {
            if(name.equalsIgnoreCase(items[i].getName()))
            {
                return items[i];
            }
        }
        System.out.println("Please Enter Valid item");
        return null;
    }
}
