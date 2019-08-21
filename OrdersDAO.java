package com.hexaware.MLP156.persistence;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;
import com.hexaware.MLP156.model.Orders;
/**
 * OrdersDAO class used to fetch data from data base.
 * @author hexware
 */
public interface OrdersDAO {
    /**
     * @return the all the Orders record.
     */
  @SqlQuery("Select * from Orders")
    @Mapper(OrdersMapper.class)
    List<Orders> show();
    /**
     * @return the all the placed Orders record.
     * @param vId vendor Id
     */
  @SqlQuery("SELECT * from Orders o,Menu m where o.FOOD_ID=m.FOOD_ID and ORD_STATUS='PLACED' and m.VENDOR_ID=:vId")
    @Mapper(OrdersMapper.class)
    List<Orders> getAllPlacedOrders(@Bind("vId") final int vId);
    /**
     * @return the all the placed Orders record.
     */
  @SqlQuery("SELECT * from Orders o,Menu m where o.FOOD_ID=m.FOOD_ID and ORD_STATUS='PLACED'")
    @Mapper(OrdersMapper.class)
    Orders validategetOrders();
    /**
     * @return the accepted order
     * @param id to get orderid
     */
  @SqlUpdate("Update Orders set ORD_STATUS='ACCEPTED' where ORD_ID=:id")
  int acceptOrderGivenId(@Bind("id") int id);
    /**
     * @return the rejected order
     * @param id to get orderid
     */
  @SqlUpdate("Update Orders set ORD_STATUS='CANCELLED' where ORD_ID=:id")
  int rejectOrderGivenId(@Bind("id") int id);
}
