package com.hexaware.MLP156.persistence;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;
import com.hexaware.MLP156.model.Employee;
/**
 * EmployeeDAO class used to fetch data from data base.
 * @author hexware
 */
public interface EmployeeDAO {
  /**
  * @return the all the Menu record.
  */
  @SqlQuery("SELECT * FROM Employee")
    @Mapper(EmployeeMapper.class)
    List<Employee> show();
  /**
   * @param eName username
   * @param ePwd password
   * @return employee login
   */
  @SqlQuery("SELECT * FROM Employee WHERE EMP_USERNAME = :eName AND EMP_PWD = :ePassword")
    @Mapper(EmployeeMapper.class)
    Employee validateLogin(@Bind("eName") String eName,
                          @Bind("ePassword") String ePwd);
}

