package com.hexaware.MLP156.util;

//import java.util.List;
import java.util.Scanner;

import com.hexaware.MLP156.factory.EmployeeFactory;
import com.hexaware.MLP156.factory.MenuFactory;
import com.hexaware.MLP156.factory.OrdersFactory;
import com.hexaware.MLP156.factory.VendorFactory;
import com.hexaware.MLP156.model.Employee;
import com.hexaware.MLP156.model.Menu;
import com.hexaware.MLP156.model.Orders;
import com.hexaware.MLP156.model.Vendor;
/**
 * CliMain used as Client interface for java coading.
 * @author hexware
 */
class CliMain {
  private Scanner option = new Scanner(System.in, "UTF-8");
/**
 * mainMenu method  used to display the option we had in the application.
 */
  private void mainMenu() {
    System.out.println("Canteen Management System");
    System.out.println("-----------------------");
    System.out.println("1. Show Menu");
    System.out.println("2. Employee Login");
    System.out.println("3. Vendor Login");
    System.out.println("4. Exit");
    mainMenuDetails();
  }
  private void subMenu1(final int vId) {
    System.out.println("Order Status- Accept or Reject order");
    System.out.println("-------------------------------------");
    System.out.println("1. Accept Order");
    System.out.println("2. Reject Order");
    System.out.println("3. Exit");
    subMenu1Details(vId);
  }
/**
 * mainMenuDetails method  process the option selected from main menu.
 */
  private void mainMenuDetails() {
    try {
      System.out.println("Enter your choice:");
      int menuOption = option.nextInt();
      switch (menuOption) {
        case 1:
          showFullMenu();
          break;
        case 2:
          empLogin();
          break;
        case 3:
          venLogin();
          // sub menu should not be this
          break;
        case 4:
          Runtime.getRuntime().halt(0);
        default:
          System.out.println("Choose either 1,2,3");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("enter a valid value");
    }
    option.nextLine();
    mainMenu();
  }

  private void subMenu1Details(final int vId) {
    try {
      System.out.println("Enter your choice:");
      int menuOption = option.nextInt();
      switch (menuOption) {
        case 1:
          acceptOrd(vId);
          break;
        case 2:
          rejectOrd(vId);
          break;
        case 3:
          mainMenu();
        default:
          System.out.println("Choose either 1,2,3");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("enter a valid value");
    }
    option.nextLine();
    subMenu1(vId);
  }
/**
 * showFullMenu method  display the menu item stored in database.
 */
  private void showFullMenu() {
    Menu[] menu = MenuFactory.showMenu();
    System.out.println("Menu_Id \t Food_Name \t Food_Type \t\t Calories \t Food_Amount");
    for (Menu m : menu) {
      System.out.println(m.getFoodId() + "\t\t" + m.getFoodName() + "\t\t" + m.getFoodType() + "\t\t\t" + m.getCalories() + "\t\t" + m.getFoodAmt());
    }
  }
  private void empLogin() {

    System.out.println("Enter Employee Name: ");
    String eName = option.next();
    System.out.println("Enter Password: ");
    String ePassword = option.next();
    Employee emp = EmployeeFactory.validateLogin(eName, ePassword);
    System.out.println(emp);
  }

  private void venLogin() {
    System.out.println("Enter Vendor UserName :");
    String vName = option.next();
    System.out.println("Enter Password :");
    String vPassword = option.next();
    Vendor ven = VendorFactory.validateVLogin(vName, vPassword);
    int vId = ven.getVendoorId();
    if (ven == null) {
      System.out.println("Please enter valid details");
      return;
    } else {
      System.out.println(ven);
      subMenu1(vId);
    }
  }
  private void showPlacedOrders(final int vId) {
    Orders[] ord = OrdersFactory.getOrders(vId);
    System.out.println("\t Orders that are placed");
    System.out.println("--------------------------------------------------");
    System.out.println("Order_Id \t Food_Id \t Ordered_Item \t Order_Status");
    for (Orders o : ord) {
      System.out.println(o.getordId() + "\t\t" + o.getfoodId() + "\t\t" + o.getordItem() + "\t\t" + o.getordMsg());
    }
  }
  /**
  * showFullMenu method  display the menu item stored in database.
  *@param vId vendor id
  */
  void acceptOrd(final int vId) {
    Orders emp = OrdersFactory.getvalidateOrders();
    if (emp != null) {
      showPlacedOrders(vId);
      System.out.println("Enter the Order ID to Accept :");
      int ordid = option.nextInt();
      int val = OrdersFactory.acceptOrders(ordid);
      System.out.println("Succes value is :" + val);
      if (val == 1) {
        System.out.println("Accepted the Order");
        return;
      }
    } else {
      System.out.println("---------No Placed Orders---------");
      return;
    }
  }
  /**
  * showFullMenu method  display the menu item stored in database.
  *@param vId vendor id
  */
  void rejectOrd(final int vId) {
    Orders emp1 = OrdersFactory.getvalidateOrders();
    if (emp1 != null) {
      showPlacedOrders(vId);
      System.out.println("Enter the Order ID to Reject :");
      int ordid = option.nextInt();
      int val = OrdersFactory.rejectOrders(ordid);
      System.out.println("Succes value is :" + val);
      if (val == 1) {
        System.out.println("Rejected the Order");
        return;
      }
    } else {
      System.out.println("---------No Placed Orders---------");
      return;
    }
  }
/**
 * main method  is the basic entry point for the application.
 * @param args used to get the user input.
 */
  public static void main(final String[] args) {
    final CliMain mainObj = new CliMain();
    mainObj.mainMenu();
  }
}
