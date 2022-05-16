/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author HP
 */
public class Invoice {

    int id;
    String date;
    Order order;

    //constructors
    public Invoice(int id, String date, Order order) {
        this.id = id;
        this.date = date;
        this.order = order;
    }

    //getters&setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    //methods
    public void invoiceOutput() {
        String show = String.format("|%-8s|%-10d|%-15s|%-10d|",
                "Invoice", getId(), getDate(), getOrder().getId());
        System.out.println(show);
    }
}
