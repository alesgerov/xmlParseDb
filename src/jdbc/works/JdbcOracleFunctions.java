package jdbc.works;

import oracle.jdbc.proxy.annotation.Pre;
import xml.parse.Employee;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class JdbcOracleFunctions implements JdbcFunctions{
    @Override
    public void showEmployees(Connection connection) {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select * from employees " +
                    "order by id";
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%s %s %s %s %s\n",resultSet.getLong("id"),resultSet.getString("first_name"),resultSet.getString("last_name"),resultSet.getDate("hire_date"),resultSet.getBigDecimal("salary"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(resultSet,preparedStatement,connection);
        }
    }



    @Override
    public void addEmployee(Connection connection, Employee employee) {

        PreparedStatement preparedStatement=null;

        try {
            //connection.setAutoCommit(false);
            String sql="insert into employees(id,first_name,last_name,salary,hire_date) " +
                    "values(?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,employee.getId());
            preparedStatement.setString(2,employee.getFirst_name());
            preparedStatement.setString(3,employee.getLast_name());
            preparedStatement.setBigDecimal(4,employee.getSalary());
            preparedStatement.setDate(5, Date.valueOf(employee.getHire_date()));
            int count=preparedStatement.executeUpdate();
            if (count==1){
                System.out.println(employee.getFirst_name()+" added");
            }else {
                System.out.println("Upps problem");
            }
            //connection.commit();
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Employee has been already satisfied");
       }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(null,preparedStatement,connection);
        }

    }

    @Override
    public void addEmployee(Employee employee) {
        this.addEmployee(JdbcUtils.open(null),employee);

    }

    @Override
    public void updateEmployee(Connection connection, Employee employee) {

    }

    @Override
    public void deleteEmployee(Connection connection, long id) {
        String sql="delete  from employees " +
                "where id=?";
        PreparedStatement preparedStatement=null;
        try {
            //connection.setAutoCommit(false);
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            int count=preparedStatement.executeUpdate();
            if (count==1){
                System.out.println("Successfully deleted");
            }else {
                System.out.println("Upps problem");
            }
            //connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(null,preparedStatement,connection);
        }
    }

    @Override
    public void deleteAllEmployees(Connection connection) {
        String sql="delete from employees";
        PreparedStatement preparedStatement=null;
        try {
            //connection.setAutoCommit(false);
            preparedStatement=connection.prepareStatement(sql);
            int count=preparedStatement.executeUpdate();

            //connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(null,preparedStatement,connection);
        }
    }
}
