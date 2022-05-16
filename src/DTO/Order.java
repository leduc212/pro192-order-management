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
public class Order {
    int id;
    String orderDate;
    String shipDate;
    Customer customer;
    Invoice invoice;
    
    //constructors

    public Order(int id, String orderDate, String shipDate, Customer customer) {
        this.id = id;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.customer = customer;
    }

    public Order(int id, String orderDate, String shipDate, Customer customer, Invoice invoice) {
        this.id = id;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.customer = customer;
        this.invoice = invoice;
    }

    
    //getters&setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    
    
    
    
    //methods
    public void inputOrder(){
        id = MyToys.getAnInteger("Input Order's ID: (integer only)", "Invalid ID!", 0);
        orderDate = MyToys.getDate("Input order date: (dd/mm/yyyy)", "Invalid date!");
        do {
            shipDate = MyToys.getDate("Input end date: (dd/mm/yyyy)", "Invalid date!");
            if (!validEndDate(orderDate, shipDate)) {
                System.out.println("Invalid ship date!");
            }
        } while (!validEndDate(orderDate, shipDate));
    }
    
    public void outputOrder(){
        String show = String.format("|%-8s|%-5d|%-15s|%-15s|%-10d|%-10s|%-12s|",
                "Order", getId(), getShipDate(), getOrderDate(),getCustomer().getId(),"<none>","<none>");
        System.out.println(show);
    }
    
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
    
    public void outputOrderInvoice(){
        String show = String.format("|%-8s|%-5d|%-15s|%-15s|%-10d|%-10d|%-12s|",
                "Order", getId(), getShipDate(), getOrderDate(),getCustomer().getId(),getInvoice().getId(),getInvoice().getDate());
        System.out.println(show);
    }
}
