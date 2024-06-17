package model.dao;

import model.entity.Customer;
import model.service.CustomerService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{
    @Override
    public List<Customer> queryAllCustomer() {
        String sql = "select * from customer";
        try(
                Connection conn = DriverManager.getConnection(
                     "jdbc:postgresql://localhost:5433/postgres",
                     "postgres",
                     "Gjk1217&9!"
             );
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery(sql)
        )
        {
            List<Customer> customers = new ArrayList<>();
           while (resultSet.next()){
               customers.add(new Customer(
                       resultSet.getInt("id"),
                       resultSet.getString("name"),
                       resultSet.getString("email"),
                       resultSet.getString("password"),
                       resultSet.getBoolean("is_deleted"),
                       resultSet.getDate("created_date")
               ));
           }
           return customers;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int deleteCustomer(Integer id) {
        String sql = """
                DELETE FROM customer WHERE id = ?
                """;
        try(
                Connection conn = DriverManager.getConnection(

                        "jdbc:postgresql://localhost:5433/postgres",
                        "postgres",
                        "Gjk1217&9!"
                );
                PreparedStatement stmt = conn.prepareStatement(sql)
                )
        {
            stmt.setInt(1,id);
            int rowAffected = stmt.executeUpdate();
             return rowAffected;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int updateCustomer(Customer customer , Integer id) {
        String sql = """
             UPDATE "customer" SET name = ?, email =?,password =?
             WHERE id = ?
                """;
        try(
             Connection connection=  DriverManager.getConnection(

                     "jdbc:postgresql://localhost:5433/postgres",
                     "postgres",
                     "Gjk1217&9!"
             );
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        )
        {
                 preparedStatement.setString(1,customer.getName());
                 preparedStatement.setString(2,customer.getEmail());
                 preparedStatement.setString(3,customer.getPassword());
                 preparedStatement.setInt(4,id);
             int rowAffected = preparedStatement.executeUpdate();
             return rowAffected;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int insertCustomer(Customer customer) {
        String sql = """
                INSERT INTO "customer" (name , email ,password , is_deleted, created_date)
                VALUES (?, ?, ?, ?, ?)
        """;
try (
        Connection connection = DriverManager.getConnection(

                "jdbc:postgresql://localhost:5433/postgres",
                "postgres",
                "Gjk1217&9!"
        );
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
    preparedStatement.setString(1, customer.getName());
    preparedStatement.setString(2, customer.getEmail());
    preparedStatement.setString(3, customer.getPassword());
    preparedStatement.setBoolean(4, customer.getIsDeleted());
    preparedStatement.setDate(5,customer.getCreatedAt());
    int rowAffectred  = preparedStatement.executeUpdate();
    return rowAffectred;
}catch (SQLException e) {
   System.out.println(e.getMessage());
}
return 0;
}

    @Override
    public Customer searchCustomerById(Integer id) {
        String sql = """
                SELECT * FROM "customer" WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(

                        "jdbc:postgresql://localhost:5433/postgres",
                        "postgres",
                        "Gjk1217&9!"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        )
        {
             preparedStatement.setInt(1 , id);
             ResultSet resultSet  = preparedStatement.executeQuery();
             Customer customeres = new Customer();
             while (resultSet.next()) {
                 customeres = Customer.builder()
                         .id(resultSet.getInt("id"))
                         .name(resultSet.getString("name"))
                         .email(resultSet.getString("email"))
                         .password(resultSet.getString("password"))
                         .isDeleted(resultSet.getBoolean("is_deleted"))
                         .createdAt(resultSet.getDate("created_date"))
                         .build();
             }
             return customeres;
        }catch (SQLException message)
        {
            System.out.println(message.getMessage());
        }
        return null;
    }
}
