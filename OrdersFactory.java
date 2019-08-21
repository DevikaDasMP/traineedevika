package com.hexaware.MLP156.factory;

import com.hexaware.MLP156.model.Orders;
//import com.hexaware.MLP156.model.Vendor;
import com.hexaware.MLP156.persistence.OrdersDAO;
import com.hexaware.MLP156.persistence.DbConnection;
import java.util.List;


/**
 * OrdersFactory class used to fetch Orders data from database.
 * @author hexware
 */
public class OrdersFactory {
  /**
   *  Protected constructor.
   */
  protected OrdersFactory() {

  }
  /**
   * Call the data base connection.
   * @return the connection object.
   */
  private static OrdersDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(OrdersDAO.class);
  }
  /**
   * Call the data base connection.
   * @return the array of Orders object.
   */
  public static Orders[] showOrders() {
    List<Orders> orders = dao().show();
    return orders.toArray(new Orders[orders.size()]);
  }
  /**
   * Call the data base connection.
   * @return the array of Orders object.
   * @param vId vendor Id
   */
  public static Orders[] getOrders(final int vId) {
    List<Orders> ord = dao().getAllPlacedOrders(vId);
    return ord.toArray(new Orders[ord.size()]);
  }
  /**
   * Call the data base connection.
   * @return the array of Orders object.
   */
  public static Orders getvalidateOrders() {
    OrdersDAO oDAo = dao();
    Orders ore = oDAo.validategetOrders();
    return ore;
  }
  /**
   * Call the data base connection.
   * @return the accepted order.
   * @param id give order id
   */
  public static int acceptOrders(final int id) {
    OrdersDAO oDAO = dao();
    int val = oDAO.acceptOrderGivenId(id);
    return val;
  }
  /**
   * Call the data base connection.
   * @return the rejected order.
   * @param id give order id
   */
  public static int rejectOrders(final int id) {
    OrdersDAO oDAO = dao();
    int val = oDAO.rejectOrderGivenId(id);
    return val;
  }
}
