package com.hexaware.MLP156.factory;

import com.hexaware.MLP156.persistence.VendorDAO;
import com.hexaware.MLP156.persistence.DbConnection;
import java.util.List;

import com.hexaware.MLP156.model.Vendor;
/**
 * MenuFactory class used to fetch menu data from database.
 * @author hexware
 */
public class VendorFactory {
  /**
   *  Protected constructor.
   */
  protected VendorFactory() {

  }
  /**
   * Call the data base connection.
   * @return the connection object.
   */
  private static VendorDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(VendorDAO.class);
  }
  /**
   * Call the data base connection.
   * @return the array of menu object.
   */
  public static Vendor[] showMenu() {
    List<Vendor> vendor = dao().show();
    return vendor.toArray(new Vendor[vendor.size()]);
  }
  /**
   * @param eN vendor usename
   * @param eP vendor password
   * @return vendor login
   */
  public static Vendor validateVLogin(final String eN, final String eP) {
    VendorDAO vDAO = dao();
    Vendor ven = vDAO.validateVLoggin(eN, eP);
    return ven;
  }
}
