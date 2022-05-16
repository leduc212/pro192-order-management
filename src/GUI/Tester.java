/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.Customer;
import DTO.CustomerList;
import DTO.Invoice;
import DTO.Item;
import DTO.ItemList;
import DTO.Order;
import DTO.OrderList;
import DTO.Quantity;
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
public class Tester {

    public static void main(String[] args) {

        CustomerList c = new CustomerList();
        ItemList i = new ItemList();
        OrderList o = new OrderList();
        ArrayList<Quantity> quantityLog = new ArrayList<>();

        c.customerList = initCustomer();
        i.itemList = initItem();
        o.orderList = initOrder();
        quantityLog = initQuantity();

        int choice;
        Scanner sc = new Scanner(System.in);

        do {
            menu();
            choice = MyToys.getAnInteger("", "Input a valid option, please!", 1, 5);

            switch (choice) {
                case 1:
                    boolean flag = true;
                    do {
                        menu1();
                        int choice1 = MyToys.getAnInteger("", "Input a valid option, please!", 1, 5);
                        switch (choice1) {
                            case 1:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Add a new Customer!");
                                c.addNewCustomer();
                                break;
                            case 2:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Remove a Customer");
                                c.removeCustomer();
                                break;
                            case 3:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Show the list of Customer");
                                c.printAscendingByID();
                                break;
                            case 4:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Search a Customer with ID and show their Orders");
                                int id;
                                id = MyToys.getAnInteger("Input Customer's ID: (integer only)", "Invalid ID!", 0);
                                if (c.searchCustomerObjectByID(id) != null) {
                                    String show = String.format("|%-8s|%-5s|%-20s|%-40s|",
                                            "Customer", "ID", "Name", "Address");
                                    System.out.println(show);
                                    System.out.println("------------------------------------------------------------------------------");
                                    c.searchCustomerObjectByID(id).outputCustomer();
                                    System.out.println("");
                                } else {
                                    System.out.println("Customer not found");
                                    return;
                                }
                                for (Order order : o.orderList) {
                                    if (order.getCustomer().getId() == id) {
                                        String show = String.format("|%-8s|%-5s|%-15s|%-15s|%-10s|%-10s|%-12s|",
                                                "Order", "ID", "Order Date", "Ship Date", "CustomerID", "Invoice ID", "Invoice Date");
                                        System.out.println(show);
                                        System.out.println("------------------------------------------------------------------------------------");
                                        break;
                                    }
                                }
                                for (Order order : o.orderList) {
                                    if (order.getCustomer().getId() == id) {
                                        if (order.getInvoice() == null) {
                                            order.outputOrder();
                                        } else {
                                            order.outputOrderInvoice();
                                        }
                                    }
                                }
                                break;
                            case 5:
                                System.out.println("Return to main menu!");
                                flag = false;
                                break;
                        }

                    } while (flag);
                    break;
                case 2:
                    flag = true;
                    do {
                        menu2();
                        int choice2 = MyToys.getAnInteger("", "Input a valid option, please!", 1, 6);
                        switch (choice2) {
                            case 1:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Add a new Item!");
                                i.addNewItem();
                                break;
                            case 2:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Change the price of an Item!");
                                i.updatePrice();
                                break;
                            case 3:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Remove an Item");
                                i.removeItem();
                                break;
                            case 4:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Show the list of Items");
                                i.printAscendingByID();
                                break;
                            case 5:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Search an Item with ID and show the Orders that contain this Item");
                                int id;
                                id = MyToys.getAnInteger("Input Item's ID: (integer only)", "Invalid ID!", 0);
                                if (i.searchItemObjectByID(id) != null) {
                                    String show = String.format("|%-8s|%-5s|%-20s|%-10s|%-12s|",
                                            "Item", "ID", "Name", "Price", "Status");
                                    System.out.println(show);
                                    System.out.println("-------------------------------------------------------------");
                                    i.searchItemObjectByID(id).outputItem();
                                    System.out.println("");
                                    for (Quantity log : quantityLog) {
                                        int itemID = log.getItem().getId();
                                        if (itemID == id) {
                                            String show1 = String.format("|%-8s|%-5s|%-15s|%-15s|%-10s|%-10s|%-12s|",
                                                    "Order", "ID", "Order Date", "Ship Date", "CustomerID", "Invoice ID", "Invoice Date");
                                            System.out.println(show1);
                                            System.out.println("------------------------------------------------------------------------------------");
                                            break;
                                        }
                                    }
                                    for (Quantity log : quantityLog) {
                                        int itemID = log.getItem().getId();
                                        Order order = log.getOrder();

                                        if (itemID == id) {
                                            if (order.getInvoice() == null) {
                                                order.outputOrder();
                                            } else {
                                                order.outputOrderInvoice();
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("Item not found");
                                }
                                break;
                            case 6:
                                System.out.println("Return to main menu!");
                                flag = false;
                                break;
                        }

                    } while (flag);
                    break;
                case 3:
                    flag = true;
                    do {
                        menu3();
                        int choice3 = MyToys.getAnInteger("", "Input a valid option, please!", 1, 6);
                        switch (choice3) {
                            case 1:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Add a new Order!");
                                o.addNewOrder(c);
                                break;
                            case 2:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Remove an Order");
                                o.removeOrder();
                                break;
                            case 3:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Show the list of Orders");
                                o.printAscendingByID();
                                break;
                            case 4:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Search an Order with ID and show which Item is in this Order");
                                int id;
                                id = MyToys.getAnInteger("Input Order's ID: (integer only)", "Invalid ID!", 0);
                                if (o.searchOrderObjectByID(id) != null) {
                                    String show = String.format("|%-8s|%-5s|%-15s|%-15s|%-10s|%-10s|%-12s|",
                                            "Order", "ID", "Order Date", "Ship Date", "CustomerID", "Invoice ID", "Invoice Date");
                                    System.out.println(show);
                                    System.out.println("------------------------------------------------------------------------------------");

                                    if (o.searchOrderObjectByID(id).getInvoice() != null) {
                                        o.searchOrderObjectByID(id).outputOrderInvoice();
                                    } else {
                                        o.searchOrderObjectByID(id).outputOrder();
                                    }
                                    System.out.println("");
                                    for (Quantity log : quantityLog) {
                                        int orderID = log.getOrder().getId();
                                        if (orderID == id) {
                                            String show1 = String.format("|%-8s|%-5s|%-20s|%-10s|%-12s|",
                                                    "Item", "ID", "Name", "Price", "Status");
                                            System.out.println(show1);
                                            System.out.println("-------------------------------------------------------------");
                                            break;
                                        }
                                    }
                                    for (Quantity log : quantityLog) {
                                        int orderID = log.getOrder().getId();
                                        Item item = log.getItem();

                                        if (orderID == id) {
                                            item.outputItem();
                                        }
                                    }
                                } else {
                                    System.out.println("Order not found");
                                }
                                break;
                            case 5:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Create an invoice for an order!");
                                id = MyToys.getAnInteger("Input Order's ID: (integer only)", "Invalid ID!", 0);
                                if (o.searchOrderObjectByID(id) != null) {

                                    if (o.searchOrderObjectByID(id).getInvoice() != null) {
                                        System.out.println("This order already have an invoice!");
                                        break;
                                    } else {
                                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
                                        LocalDateTime now = LocalDateTime.now();
                                        Invoice invoice = new Invoice(id, dtf.format(now), o.searchOrderObjectByID(id));
                                        o.searchOrderObjectByID(id).setInvoice(invoice);
                                        o.invoiceList.add(invoice);
                                        String show = String.format("|%-8s|%-5s|%-15s|%-15s|%-10s|%-10s|%-12s|",
                                                "Order", "ID", "Order Date", "Ship Date", "CustomerID", "Invoice ID", "Invoice Date");
                                        System.out.println(show);
                                        System.out.println("------------------------------------------------------------------------------------");

                                        if (o.searchOrderObjectByID(id).getInvoice() != null) {
                                            o.searchOrderObjectByID(id).outputOrderInvoice();
                                        } else {
                                            o.searchOrderObjectByID(id).outputOrder();
                                        }
                                        System.out.println("Added an invoice successfully!");
                                    }
                                } else {
                                    System.out.println("Order not found");
                                }
                                break;
                            case 6:
                                System.out.println("Return to main menu!");
                                flag = false;
                                break;
                        }

                    } while (flag);
                    break;
                case 4:
                    flag = true;
                    do {
                        menu4();
                        int choice3 = MyToys.getAnInteger("", "Input a valid option, please!", 1, 4);
                        switch (choice3) {
                            case 1:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Add Item to an existing Order!");
                                System.out.println("Input an order and an item!");
                                //Tim Order
                                int orderID,
                                 itemID;
                                orderID = MyToys.getAnInteger("Input Order's ID: (integer only)", "Invalid ID!", 0);
                                if (o.searchOrderObjectByID(orderID) != null) {
                                    String show = String.format("|%-8s|%-5s|%-15s|%-15s|%-10s|%-10s|%-12s|",
                                            "Order", "ID", "Order Date", "Ship Date", "CustomerID", "Invoice ID", "Invoice Date");
                                    System.out.println(show);
                                    System.out.println("------------------------------------------------------------------------------------");
                                    if (o.searchOrderObjectByID(orderID).getInvoice() != null) {
                                        o.searchOrderObjectByID(orderID).outputOrderInvoice();
                                    } else {
                                        o.searchOrderObjectByID(orderID).outputOrder();
                                    }
                                } else {
                                    System.out.println("Order not found");
                                    break;
                                }
                                //Tim Item
                                itemID = MyToys.getAnInteger("Input Item's ID: (integer only)", "Invalid ID!", 0);
                                if (i.searchItemObjectByID(itemID) != null) {
                                    if (i.searchItemObjectByID(itemID).getStatus().equalsIgnoreCase("UNAVAILABLE")) {
                                        System.out.println("This item is unavailable!");
                                        break;
                                    }
                                    String show = String.format("|%-8s|%-5s|%-20s|%-10s|%-12s|",
                                            "Item", "ID", "Name", "Price", "Status");
                                    System.out.println(show);
                                    System.out.println("-------------------------------------------------------------");
                                    i.searchItemObjectByID(itemID).outputItem();
                                } else {
                                    System.out.println("Item not found");
                                    break;
                                }
                                //Add so luong item
                                int quantity = MyToys.getAnInteger("Input the quantity of item that you want to add to this order!", "Invalid number of items", 0);
                                //Add vao 1 list
                                quantityLog.add(new Quantity(o.searchOrderObjectByID(orderID), i.searchItemObjectByID(itemID), quantity));
                                System.out.println("Added a new log successfully!");
                                break;
                            case 2:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Show all of the Order - Item quantity log!");
                                int n = 0;
                                if (quantityLog.isEmpty()) {
                                    System.out.println("The log is empty. Nothing to print");
                                    break;
                                }
                                String show = String.format("|%-8s|%-10s|%-10s|%-20s|%-8s|",
                                        "Log ID", "Order ID", "Item ID", "Item Name", "Quantity");
                                System.out.println(show);
                                System.out.println("--------------------------------------------------------------");
                                for (Quantity log : quantityLog) {
                                    n += 1;
                                    int tmpOrderID = log.getOrder().getId();
                                    int tmpItemID = log.getItem().getId();
                                    String tmpItemName = log.getItem().getName();
                                    int tmpQuantity = log.getQuantity();

                                    show = String.format("|%-8d|%-10d|%-10d|%-20s|%-8d|",
                                            n, tmpOrderID, tmpItemID, tmpItemName, tmpQuantity);
                                    System.out.println(show);
                                }
                                break;
                            case 3:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Remove item from an Order!");
                                if (quantityLog.isEmpty()) {
                                    System.out.println("The quantity log list is empty. Nothing to remove!");
                                    break;
                                }
                                System.out.println("Input an Order's ID and choose which item you want to remove!");
                                int oID;
                                Order orderObj = null;
                                oID = MyToys.getAnInteger("Input Order's ID: (integer only)", "Invalid ID!", 0);

                                for (int j = 0; j < quantityLog.size(); j++) {
                                    if (quantityLog.get(j).getOrder().getId() == oID) {
                                        orderObj = quantityLog.get(j).getOrder();
                                        break;
                                    }
                                }
                                if (orderObj != null) {
                                    String show1 = String.format("|%-8s|%-5s|%-15s|%-15s|%-10s|%-10s|%-12s|",
                                            "Order", "ID", "Order Date", "Ship Date", "CustomerID", "Invoice ID", "Invoice Date");
                                    System.out.println(show1);
                                    System.out.println("------------------------------------------------------------------------------------");

                                    if (o.searchOrderObjectByID(oID).getInvoice() != null) {
                                        o.searchOrderObjectByID(oID).outputOrderInvoice();
                                    } else {
                                        o.searchOrderObjectByID(oID).outputOrder();
                                    }
                                    System.out.println("This order is containing these items:");
                                    for (Quantity log : quantityLog) {
                                        orderID = log.getOrder().getId();
                                        if (orderID == oID) {
                                            String show2 = String.format("|%-8s|%-5s|%-20s|%-10s|%-12s|",
                                                    "Item", "ID", "Name", "Price", "Status");
                                            System.out.println(show2);
                                            System.out.println("-------------------------------------------------------------");
                                            break;
                                        }
                                    }
                                    for (Quantity log : quantityLog) {
                                        orderID = log.getOrder().getId();
                                        Item item = log.getItem();

                                        if (orderID == oID) {
                                            item.outputItem();
                                        }
                                    }

                                    int iID;
                                    Quantity ql = null;
                                    iID = MyToys.getAnInteger("Input ID of the item that you want to remove from this order: (integer only)", "Invalid ID!", 0);
                                    for (Quantity quantityLog1 : quantityLog) {
                                        if ((quantityLog1.getOrder().getId() == oID) && (quantityLog1.getItem().getId() == iID)) {
                                            ql = quantityLog1;
                                        }
                                    }
                                    if (ql != null) {
                                        quantityLog.remove(ql);
                                        System.out.println("Remove successfully!");
                                    } else {
                                        System.out.println("There is no item with this ID that is in this order!");
                                    }

                                } else {
                                    System.out.println("There is no order with this ID in the quantity log!");
                                }

                                break;
                            case 4:
                                System.out.println("Return to main menu!");
                                flag = false;
                                break;
                        }
                    } while (flag);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
            }

        } while (true);
    }

    static void menu() {
        System.out.println("===========================MainMenu============================");
        System.out.println("**      1. Customer management                               **");
        System.out.println("**      2. Item management                                   **");
        System.out.println("**      3. Order management                                  **");
        System.out.println("**      4. Order and Item quantity management                **");
        System.out.println("**      5. Exit                                              **");
        System.out.println("===============================================================");
        System.out.println(">Input your choice: ");
    }

    static void menu1() {
        System.out.println("======================CustomerManagement=======================");
        System.out.println("**      1. Add a new Customer                                **");
        System.out.println("**      2. Remove a Customer                                 **");
        System.out.println("**      3. Show the list of Customers                        **");
        System.out.println("**      4. Search a Customer with ID and show their Orders   **");
        System.out.println("**      5. Return to main menu                               **");
        System.out.println("===============================================================");
        System.out.println(">Input your choice: ");

    }

    static void menu2() {
        System.out.println("=============================ItemManagement==============================");
        System.out.println("**1. Add a new Item                                                    **");
        System.out.println("**2. Update the price of an Item                                       **");
        System.out.println("**3. Remove an Item                                                    **");
        System.out.println("**4. Show the list of Item                                             **");
        System.out.println("**5. Search an Item with ID and show the Orders that contain this Item **");
        System.out.println("**6. Return to main menu                                               **");
        System.out.println("=========================================================================");
        System.out.println(">Input your choice: ");
    }

    static void menu3() {
        System.out.println("=============================OrderManagement==============================");
        System.out.println("**  1. Add a new Order                                                  **");
        System.out.println("**  2. Remove an Order                                                  **");
        System.out.println("**  3. Show the list of Orders                                          **");
        System.out.println("**  4. Search an Order with ID and show which Item is in this Order     **");
        System.out.println("**  5. Create invoice for an order                                      **");
        System.out.println("**  6. Return to main menu                                              **");
        System.out.println("==========================================================================");
        System.out.println(">Input your choice: ");
    }

    static void menu4() {
        System.out.println("======================QuantityManagement=======================");
        System.out.println("**      1. Add items to an Order(Add new quantity log)       **");
        System.out.println("**      2. Show the quantity log                             **");
        System.out.println("**      3. Remove item from an Order                         **");
        System.out.println("**      4. Return to main menu                               **");
        System.out.println("===============================================================");
        System.out.println(">Input your choice: ");
    }

    static ArrayList<Customer> initCustomer() {
        ArrayList<Customer> ds = new ArrayList<>();
        ds.add(new Customer(1, "Le Duc", "2,D4,kp6,PLB,Q9,TPHCM"));
        ds.add(new Customer(2, "Pham Nhat Vuong", "Landmark 81"));
        ds.add(new Customer(3, "Jack Ma", "China"));
        ds.add(new Customer(4, "Elon Musk", "Somewhere in the USA"));
        ds.add(new Customer(5, "David Beckham", "Somewhere in the UK"));
        ds.add(new Customer(10, "Cristiano Ronaldo", "Somewhere in Portugal"));
        ds.add(new Customer(15, "Lionel Messi", "Somewhere in Argentina"));
        return ds;
    }

    static ArrayList<Order> initOrder() {
        ArrayList<Order> ds = new ArrayList<>();

        CustomerList c = new CustomerList();
        c.customerList = initCustomer();

        ds.add(new Order(1, "25/12/2020", "31/12/2020", c.searchCustomerObjectByID(1)));
        ds.add(new Order(2, "01/03/2021", "02/03/2021", c.searchCustomerObjectByID(2)));
        ds.add(new Order(3, "12/05/2020", "25/05/2020", c.searchCustomerObjectByID(1)));
        ds.add(new Order(4, "19/03/2021", "22/03/2021", c.searchCustomerObjectByID(3)));
        ds.add(new Order(5, "16/04/2021", "20/04/2021", c.searchCustomerObjectByID(2)));
        return ds;
    }

    static ArrayList<Item> initItem() {
        ArrayList<Item> ds = new ArrayList<>();
        ds.add(new Item(1, "Axe Shampoo", 100000, "Available"));
        ds.add(new Item(2, "Victoria Secret", 1000000, "Unavailable"));
        ds.add(new Item(3, "Soap", 50000, "Available"));
        ds.add(new Item(4, "Face mask", 70000, "Available"));
        ds.add(new Item(5, "Play Doh", 30000, "Unavailable"));
        ds.add(new Item(9, "Pencil", 5000, "Available"));
        ds.add(new Item(10, "Eraser", 6000, "Available"));
        return ds;
    }

    static ArrayList<Quantity> initQuantity() {
        ArrayList<Quantity> ds = new ArrayList<>();

        ItemList i = new ItemList();
        i.itemList = initItem();

        OrderList o = new OrderList();
        o.orderList = initOrder();

        ds.add(new Quantity(o.searchOrderObjectByID(1), i.searchItemObjectByID(1), 1));
        ds.add(new Quantity(o.searchOrderObjectByID(1), i.searchItemObjectByID(3), 2));
        ds.add(new Quantity(o.searchOrderObjectByID(2), i.searchItemObjectByID(4), 5));
        ds.add(new Quantity(o.searchOrderObjectByID(2), i.searchItemObjectByID(1), 2));
        ds.add(new Quantity(o.searchOrderObjectByID(3), i.searchItemObjectByID(9), 10));
        ds.add(new Quantity(o.searchOrderObjectByID(4), i.searchItemObjectByID(10), 5));
        return ds;
    }
}
