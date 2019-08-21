package com.hexaware.MLP156.persistence;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;

import com.hexaware.MLP156.model.Vendor;
/**
 * MenuDAO class used to fetch data from data base.
 * @author hexware
 */
public interface VendorDAO {
    /**
     * @return the all the Menu record.
     */
  @SqlQuery("Select * from Vendor")
    @Mapper(VendorMapper.class)
    List<Vendor> show();
    /**
     * @param vName username
     * @param vPwd password
     * @return vendor login
     */
  @SqlQuery("SELECT * FROM Vendor WHERE VEN_USERNAME = :vName AND VEN_PWD = :vPassword")
    @Mapper(VendorMapper.class)
    Vendor validateVLoggin(@Bind("vName")String vName,
                          @Bind("vPassword") String vPwd);
}
