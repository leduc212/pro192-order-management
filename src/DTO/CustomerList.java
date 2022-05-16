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
public class CustomerList {

    public ArrayList<Customer> customerList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    //Tim customer theo ID
    public int searchCustomerByID(int ID) {
        if (customerList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId() == ID) {
                return i;
            }
        }
        return -1;
    }

    //Tim customer theo ID tra ve object
    public Customer searchCustomerObjectByID(int ID) {
        if (customerList.isEmpty()) {
            return null;
        }
        for (Customer o : customerList) {
            if (o.getId() == ID) {
                return o;
            }
        }
        return null;
    }

    //Input Customer
    public void addNewCustomer() {
        int id;
        String name, address;
        int pos;

        do {
            id = MyToys.getAnInteger("Input Campus' ID: (integer only)", "Invalid ID!", 0);
            pos = searchCustomerByID(id);
            if (pos >= 0) {
                System.out.println("The Customer ID is already existed! "
                        + "Input another one!");
            }
        } while (pos != -1);

        name = MyToys.getString("Input Customer' name: ", "Invalid name!");
        address = MyToys.getString("Input Customer' address: ", "Invalid address!");
        customerList.add(new Customer(id, name, address));
        System.out.println("A Customer profile is added successfully.");
    }

    //Remove a Customer
    public void removeCustomer() {
        int id;
        id = MyToys.getAnInteger("Input Customer' ID: (integer only)", "Invalid ID!", 0);
        if (searchCustomerObjectByID(id) != null) {
            customerList.remove(searchCustomerObjectByID(id));
            System.out.println("Customer removed successfully!");
        } else {
            System.out.println("Customer not found");
        }
    }

    //Xuat list
    public void printAscendingByID() {
        if (customerList.isEmpty()) {
            System.out.println("The Customer list is empty. Nothing to print");
            return;
        }

        Comparator ascID = new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.getId() - o2.getId();
            }
        };
        Collections.sort(customerList, ascID);

        String show = String.format("|%-8s|%-5s|%-20s|%-40s|",
                "Customer", "ID", "Name", "Address");
        System.out.println(show);
        System.out.println("------------------------------------------------------------------------------");
        for (Customer o : customerList) {
            o.outputCustomer();
        }
    }
}
