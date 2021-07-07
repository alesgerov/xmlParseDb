package jdbc.works;

import xml.parse.Employee;

import java.sql.Connection;
import java.util.List;

public interface JdbcFunctions {
    void showEmployees(Connection connection);



    void addEmployee(Connection connection, Employee  employee);
    void addEmployee(Employee  employee);

    void updateEmployee(Connection connection, Employee  employee);

    void deleteEmployee(Connection connection, long id);
    void  deleteAllEmployees(Connection connection);

}
