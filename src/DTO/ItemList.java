/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import util.MyToys;

/**
 *
 * @author HP
 */
public class ItemList {
    public ArrayList<Item> itemList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    
    //Tim item theo ID
    public int searchItemByID(int ID) {
        if (itemList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId() == ID) {
                return i;
            }
        }
        return -1;
    }
    
    //Tim Item theo ID tra ve object
    public Item searchItemObjectByID(int ID) {
        if (itemList.isEmpty()) {
            return null;
        }
        for (Item o : itemList) {
            if (o.getId() == ID) {
                return o;
            }
        }
        return null;
    }
    
    //Input Item
    public void addNewItem() {
        int id,price;
        String name, status;
        int pos;

        do {
            id = MyToys.getAnInteger("Input Item' ID: (integer only)", "Invalid ID!", 0);
            pos = searchItemByID(id);
            if (pos >= 0) {
                System.out.println("The Item ID is already existed! "
                        + "Input another one!");
            }
        } while (pos != -1);

        name = MyToys.getString("Input Item' name: ", "Invalid name!");
        price = MyToys.getAnInteger("Input Item' price: (integer only)", "Invalid price!", 0);
        String stt = MyToys.getId("Input Item's status (available or unavailable):  (A/U)", "Invalid status!. Insert A/U", "^[A|U|a|u]");
        if (stt.equals("a") || stt.equals("A") ) status = "Available";
        else status = "Unavailable";
        itemList.add(new Item(id, name, price, status));
        System.out.println("An Item is added successfully.");
    }
    
    //Remove a Item
    public void removeItem() {
        int id;
        id = MyToys.getAnInteger("Input Item' ID: (integer only)", "Invalid ID!", 0);
        if (searchItemObjectByID(id) != null) {
            itemList.remove(searchItemObjectByID(id));
            System.out.println("Item removed successfully!");
        } else {
            System.out.println("Item not found");
        }
    }
    
    //Xuat list
    public void printAscendingByID() {
        if (itemList.isEmpty()) {
            System.out.println("The Item list is empty. Nothing to print");
            return;
        }
        Comparator ascID = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getId() - o2.getId();
            }
        };
        Collections.sort(itemList, ascID);

        String show = String.format("|%-8s|%-5s|%-20s|%-10s|%-12s|",
                "Item", "ID", "Name", "Price","Status");
        System.out.println(show);
        System.out.println("-------------------------------------------------------------");
        for (Item o : itemList) {
            o.outputItem();
        }
    }
    
    //Update Price
    public void updatePrice() {
        int id;
        int tmpPrice;
        Item x;
        id = MyToys.getAnInteger("Input Item's ID: (integer only)", "Invalid ID!", 0);
        x = searchItemObjectByID(id);
        System.out.println("===========================================");
        if (x == null) {
            System.out.println("Not Found!!!!");
        } else {
            System.out.println("Here is the Item before updating");
            x.outputItem();
            System.out.println("You are required to input new price for this Item: ");
            tmpPrice = MyToys.getAnInteger("Input Item's  price: ", "Invalid price!", 0);
            x.setPrice(tmpPrice);
            System.out.println("The price info is updated successfully!");
        }
    }
}
