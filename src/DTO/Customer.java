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
public class Customer {
    int id;
    String name;
    String address;
    
    //constructors

    public Customer(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    //methods
    public void inputCustomer(){
        id = MyToys.getAnInteger("Input Customer's ID: (integer only)", "Invalid ID!", 0);
        name = MyToys.getString("Input Customer's name: ", "Invalid name!");
        address = MyToys.getString("Input Customer's address: ", "Invalid address!");
    }
    
    public void outputCustomer(){
        String show = String.format("|%-8s|%-5d|%-20s|%-40s|",
                "Customer", getId(), getName(), getAddress());
        System.out.println(show);
    }
}
