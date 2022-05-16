/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import util.MyToys;

/**
 *
 * @author HP
 */
public class Item {
    int id;
    String name;
    int price;
    String status;
    
    //constructors

    public Item(int id, String name, int price, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    
    
    //getters&setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    //methods
    public void inputItem(){
        id = MyToys.getAnInteger("Input Item's ID: (integer only)", "Invalid ID!", 0);
        name = MyToys.getString("Input Item's name: ", "Invalid name!");
        price = MyToys.getAnInteger("Input Item's price: (integer only)", "Invalid price!", 0);
        String stt = MyToys.getId("Input Item's status (available or unavailable):  (A/U)", "Invalid status!. Insert A/U", "^[A|U|a|u]");
        if (stt.equals("a") || stt.equals("A") ) setStatus("Available");
        else setStatus("Unavailable");
    }
    
    public void outputItem(){
        String show = String.format("|%-8s|%-5d|%-20s|%-10d|%-12s|",
                "Item", getId(), getName(), getPrice(),getStatus());
        System.out.println(show);
    }
}
