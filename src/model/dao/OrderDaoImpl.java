package model.dao;

import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.sql.*;
import java.util.*;

public class OrderDaoImpl implements OrderDao{
    @Override
    public int addNewOrder(Order order) {
        String sql =
                """
                        INSERT INTO "order" (id,order_name , order_description , cus_id , ordered_at ,bio)
                        VALUES (? ,?, ?, ?, ?,?)
                        """;
        String sql1 = """
                         INSERT INTO "product_order"
                         VALUES (?, ?)
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5433/postgres",
                        "postgres",
                        "Gjk1217&9!"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                ){
            //Product_order
            for (Product product : order.getProducts()) {
                preparedStatement1.setInt(1, product.getId());
                preparedStatement1.setInt(2, order.getId());
            }
            preparedStatement.setInt(1, order.getId());
             preparedStatement.setString(2,order.getOrderName());
             preparedStatement.setString(3,order.getOrderDescription());
             preparedStatement.setInt(4,order.getCustomer().getId());
             preparedStatement.setDate(5,order.getOrderDate());
             preparedStatement.setString(6,order.getBio());
             int rowAffected = preparedStatement.executeUpdate();
             String message  = rowAffected > 0 ? "You can insert is successfully" : "You can insert data";
             System.out.println(message);
            int rowAffected1 = preparedStatement1.executeUpdate();
            String message1  = rowAffected1 > 0 ? "You can insert is successfully" : "You can insert data";
            System.out.println(message1);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    @Override
    public int updateOrder(Order order , Integer id) {
        String sql = """
                UPDATE "order" SET 
                "order_name" = ? , 
                "order_description" = ? ,
                "bio" = ?
                where id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(

                        "jdbc:postgresql://localhost:5433/postgres",
                        "postgres",
                        "Gjk1217&9!"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
                    preparedStatement.setString(1,order.getOrderName());
                    preparedStatement.setString(2,order.getOrderDescription());
                    preparedStatement.setString(3,order.getBio());
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
    public int deleteOrder(Integer id ) {
        String sql = """
            DELETE FROM  "order"
            WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5433/postgres",
                        "postgres",
                        "Gjk1217&9!"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
                preparedStatement.setInt(1,id);
                int rowAffected = preparedStatement.executeUpdate();
                String message  = rowAffected >0 ? "You can deleted is successfully" : "You cannot  to delete data";
                System.out.println(message);
                return rowAffected;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Order> getAllOrders() {
        String sql = """
                SELECT * FROM "order" o
                                  inner join public.customer c on c.id = o.cus_id
                """;
        try(
                Connection connection = DriverManager.getConnection(

                        "jdbc:postgresql://localhost:5433/postgres",
                        "postgres",
                        "Gjk1217&9!"
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next())
            {
                orders.add(Order.builder()
                                .id(resultSet.getInt("id"))
                                .orderName(resultSet.getString("order_name"))
                                .orderDescription(resultSet.getString("order_description"))
                                .orderDate(resultSet.getDate("ordered_at"))
                                .bio(resultSet.getString("bio"))
                                .customer(Customer.builder()
                                        .id(resultSet.getInt("cus_id"))
                                        .name(resultSet.getString("name"))
                                        .email(resultSet.getString("email"))
                                        .password(resultSet.getString("password"))
                                        .isDeleted(resultSet.getBoolean("is_deleted"))
                                        .createdAt(resultSet.getDate("created_date"))
                                        .build())
                        .build());
            }
            return  orders;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public Order searchOrder(Integer id) {
        String sql = """
                SELECT * FROM "order"
                WHERE id = ?
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
            Order order;
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                order = Order.builder()
                        .id(resultSet.getInt("id"))
                        .orderName(resultSet.getString("order_name"))
                        .orderDescription(resultSet.getString("order_description"))
                        .orderDate(resultSet.getDate("ordered_at"))
                        .customer(Customer.builder()
                                .id(resultSet.getInt("cus_id"))
                                .build())
                        .products(new ArrayList<>())
                        .bio(resultSet.getString("bio"))
                        .build();
                return order;
            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
