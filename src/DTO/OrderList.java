/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import util.MyToys;

/**
 *
 * @author HP
 */
public class OrderList {

    public ArrayList<Order> orderList = new ArrayList<>();
    public ArrayList<Invoice> invoiceList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    //Tim Order theo ID
    public int searchOrderByID(int ID) {
        if (orderList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getId() == ID) {
                return i;
            }
        }
        return -1;
    }

    //Tim Order theo ID tra ve object
    public Order searchOrderObjectByID(int ID) {
        if (orderList.isEmpty()) {
            return null;
        }
        for (Order o : orderList) {
            if (o.getId() == ID) {
                return o;
            }
        }
        return null;
    }

    //Input Order
    public void addNewOrder(CustomerList c) {
        int id;
        String orderDate, shipDate;
        int customerID;
        int pos;

        do {
            id = MyToys.getAnInteger("Input Order's ID: (integer only)", "Invalid ID!", 0);
            pos = searchOrderByID(id);
            if (pos >= 0) {
                System.out.println("The Order ID is already existed! "
                        + "Input another one!");
            }
        } while (pos != -1);

        customerID = MyToys.getAnInteger("Input the ID of this order's Customer: (integer only)", "Invalid ID!", 0);
        pos = c.searchCustomerByID(customerID);
        if (pos >= 0) {
            System.out.println("Added the Customer of this order!");
        } else {
            System.out.println("Can't find the Customer with this ID! Please add a new Customer in the 'Customer Management' menu first");
            return;
        }

        orderDate = MyToys.getDate("Input order date: (dd/mm/yyyy)", "Invalid date!");
        do {
            shipDate = MyToys.getDate("Input end date: (dd/mm/yyyy)", "Invalid date!");
            if (!validEndDate(orderDate, shipDate)) {
                System.out.println("Invalid ship date!");
            }
        } while (!validEndDate(orderDate, shipDate));

        Customer customer = c.searchCustomerObjectByID(customerID);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        LocalDateTime now = LocalDateTime.now();
        String stt = MyToys.getId("Do you want to create an invoice for this order?:  (Y/N)", "Invalid input! Insert Y/N", "^[Y|N|y|n]");
        if (stt.equalsIgnoreCase("Y")) {
            Order order = new Order(id, orderDate, shipDate, customer);
            Invoice invoice = new Invoice(id, dtf.format(now), order);
            order.setInvoice(invoice);
            orderList.add(order);
            invoiceList.add(invoice);
            System.out.println("Added invoice for this order!");
        }else orderList.add(new Order(id, orderDate, shipDate, customer));       
        System.out.println("An order is added successfully.");
    }

    //Valid enddate
    public boolean validEndDate(String bDate, String eDate) {
        String begin[] = bDate.split("/");
        String end[] = eDate.split("/");
        if (Integer.parseInt(end[2]) < Integer.parseInt(begin[2])) {
            return false;
        } else if (Integer.parseInt(end[1]) < Integer.parseInt(begin[1])) {
            return false;
        } else if (Integer.parseInt(end[0]) < Integer.parseInt(begin[0])) {
            return false;
        } else {
            return true;
        }
    }

    //Remove an order
    public void removeOrder() {
        int id;
        id = MyToys.getAnInteger("Input order's ID: (integer only)", "Invalid ID!", 0);
        if (searchOrderObjectByID(id) != null) {
            orderList.remove(searchOrderObjectByID(id));
            System.out.println("Order removed successfully!");
        } else {
            System.out.println("Order not found");
        }
    }

    //Xuat list
    public void printAscendingByID() {
        if (orderList.isEmpty()) {
            System.out.println("The Order list is empty. Nothing to print");
            return;
        }

        Comparator ascID = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getId() - o2.getId();
            }
        };
        Collections.sort(orderList, ascID);

        String show = String.format("|%-8s|%-5s|%-15s|%-15s|%-10s|%-10s|%-12s|",
                "Order", "ID", "Order Date", "Ship Date", "CustomerID","Invoice ID","Invoice Date");
        System.out.println(show);
        System.out.println("------------------------------------------------------------------------------------");
        for (Order o : orderList) {
            if (o.getInvoice() == null) o.outputOrder();
            else o.outputOrderInvoice();
        }
    }

}
